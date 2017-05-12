package com.muzima.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

/**
 * Manages network calls include checking Http Connection establishment.
 */
public class NetworkUtils {
    private static final Logger logger = LoggerFactory.getLogger(NetworkUtils.class.getCanonicalName());

    /**
     * Cheks if the chosen address on reachable for client http calls.i.e.
     * Checks if connection is available before begining transactions.
     *
     * @param address -String
     * @param proxy -Proxy Object
     * @param timeout -int timeout
     * @return boolean -isAddressReachable
     */

    public static boolean isAddressReachable(String address, Proxy proxy, Integer timeout) {
        boolean serverAvailable = false;
        try {
            HttpURLConnection connection = null;
            if(proxy != null ){
                connection = openConnection(address, proxy);
            } else {
                connection = openConnection(address);
            }
            connection.setConnectTimeout(timeout);
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                serverAvailable = true;
            }
        } catch (IOException e) {
            if(proxy == null ){
                logger.error("Unable to create connection to address" + address );
            } else {
                logger.error("Unable to create connection to address : " + address + " Proxy : " + proxy );
            }
        }
        return serverAvailable;
    }

    /**
     * TODO:Add brief method definition.
     * @param address - String
     * @param timeout - Integer
     * @return boolean - isAddressReachable
     */
    public static boolean isAddressReachable(String address, Integer timeout){
        return isAddressReachable(address,null, timeout);
    }

    /**
     * 
     * @param address - String
     * @return boolean 
     */
    
    public static boolean isAddressReachable(final String address){
        return NetworkUtils.isAddressReachable(address, Constants.CONNECTION_TIMEOUT);
    }

    /**
     * 
     * @param address - String
     * @return HttpURLConnection 
     * @throws IOException
     */
    public static HttpURLConnection openConnection(String address)  throws IOException {
        URL url = new URL(address);
        return (HttpURLConnection)url.openConnection();

    }

    /**
     * 
     * @param address - String 
     * @param proxy - Proxy Object
     * @return  HttpURLConnection Object
     * @throws IOException
     */
    
    public static HttpURLConnection openConnection(String address, Proxy proxy)  throws IOException {
        URL url = new URL(address);
        return (HttpURLConnection)url.openConnection(proxy);
    }
}
