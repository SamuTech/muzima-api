package com.mclinic.api;

import java.io.File;
import java.net.URL;

import com.mclinic.api.model.Cohort;
import com.mclinic.api.model.Patient;
import com.mclinic.api.model.algorithm.CohortAlgorithm;
import com.mclinic.api.model.algorithm.CohortMemberAlgorithm;
import com.mclinic.api.model.algorithm.PatientAlgorithm;
import com.mclinic.api.model.resolver.CohortMemberResolver;
import com.mclinic.api.model.resolver.CohortResolver;
import com.mclinic.api.model.resolver.PatientResolver;
import com.mclinic.api.module.MuzimaModule;
import com.mclinic.api.service.AdministrativeService;
import com.mclinic.search.api.Context;
import com.mclinic.search.api.RestAssuredService;
import com.mclinic.search.api.logger.Logger;
import com.mclinic.search.api.resource.Resource;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdministrativeServiceTest {

    private Logger log;

    private RestAssuredService service;

    @Before

    /**
     * @verifies index data from the rest resource
     * @
     * @see AdministrativeService#initializeRepository(File)
     */
    @SuppressWarnings("unchecked")
    @Test
    public void initializeDB_shouldRegisterAllItems() throws Exception {
        URL j2l = AdministrativeServiceTest.class.getResource("j2l");
        URL lucene = AdministrativeServiceTest.class.getResource("lucene");

        MuzimaModule module = new MuzimaModule(lucene.getPath(), "uuid");
        module.setServer("http://localhost:8080/openmrs/");
        module.setUsername("admin");
        module.setPassword("test");
        Context.initialize(module);

        try {
            Context.registerObject(Cohort.class);
            Context.registerObject(Patient.class);

            Context.registerAlgorithm(PatientAlgorithm.class);
            Context.registerAlgorithm(CohortAlgorithm.class);
            Context.registerAlgorithm(CohortMemberAlgorithm.class);

            Context.registerResolver(PatientResolver.class);
            Context.registerResolver(CohortResolver.class);
            Context.registerResolver(CohortMemberResolver.class);
            Context.registerResources(new File(j2l.getPath()));
        } catch (Exception e) {
            log.error("AdministrativeServiceTest", "Error initializing");
        }

        Resource resource = Context.getResource("Patient");
        Assert.assertNotNull(resource);

        resource = Context.getResource("Cohort");
        Assert.assertNotNull(resource);

        resource = Context.getResource("Cohort Member");
        Assert.assertNotNull(resource);

        service = Context.getService();
    }

    @Test
    public void loadPatients_shouldLoadPatientsFromJsonFiles() {
        URL patientJsons = AdministrativeServiceTest.class.getResource("json/patient");
        try {
            service.loadObjects("*", Context.getResource("Patient"), new File(patientJsons.getPath()));
            Assert.assertNotNull(service.getObjects(null, Patient.class));
        } catch (Exception e) {
            log.error("AdministrativeServiceTest", "Error loading patients from json");
        }
    }

    @Test
    public void loadCohorts_shouldLoadCohortsFromJsonFiles() {
        URL cohortJsons = AdministrativeServiceTest.class.getResource("json/cohort");

        try {
            service.loadObjects("*", Context.getResource("Cohort"), new File(cohortJsons.getPath()));
            Assert.assertNotNull(service.getObjects(null, Cohort.class));
        } catch (Exception e) {
            log.error("AdministrativeServiceTest", "Error loading cohorts from json");
        }
    }

    @Test
    public void loadCohortMembers_shouldLoadCohortMembersFromJsonFiles() {
        URL cohortMemberJsons = AdministrativeServiceTest.class.getResource("json/cohort_member");
        try {
            service.loadObjects("*", Context.getResource("Cohort Member"), new File(cohortMemberJsons.getPath()));
            Assert.assertNotNull(service.getObjects(null, Patient.class));
        } catch (Exception e) {
            log.error("AdministrativeServiceTest", "Error loading cohort members from json");
        }
    }
}