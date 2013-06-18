/**
 * Copyright 2012 Muzima Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.muzima.api.service;

import com.google.inject.ImplementedBy;
import com.muzima.api.model.Cohort;
import com.muzima.api.model.CohortData;
import com.muzima.api.model.CohortDefinition;
import com.muzima.api.model.CohortMember;
import com.muzima.api.service.impl.CohortServiceImpl;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Service handling all operation to the @{Cohort} actor/model
 */
@ImplementedBy(CohortServiceImpl.class)
public interface CohortService extends MuzimaInterface {

    /**
     * Download a single cohort record from the cohort rest resource and convert them into Cohort object.
     *
     * @param uuid the uuid of the cohort.
     * @throws IOException when search api unable to process the resource.
     * @should download cohort with matching uuid.
     */
    Cohort downloadCohortByUuid(final String uuid) throws IOException;

    /**
     * Download all cohorts with name similar to the partial name passed in the parameter.
     *
     * @param name the partial name of the cohort to be downloaded. When empty, will return all cohorts available.
     * @throws IOException when search api unable to process the resource.
     * @should download all cohorts with partially matched name.
     * @should download all cohorts when name is empty.
     */
    List<Cohort> downloadCohortsByName(final String name) throws IOException;

    /**
     * Save the current cohort object to the local lucene repository.
     *
     * @param cohort the cohort to be saved.
     * @throws IOException when search api unable to process the resource.
     */
    void saveCohort(final Cohort cohort) throws IOException;

    /**
     * Save the current cohort objects to the local lucene repository.
     *
     * @param cohorts the cohorts to be saved.
     * @throws IOException when search api unable to process the resource.
     */
    void saveCohorts(final List<Cohort> cohorts) throws IOException;

    /**
     * Update the current cohort object to the local lucene repository.
     *
     * @param cohort the cohort to be updated.
     * @throws IOException when search api unable to process the resource.
     */
    void updateCohort(final Cohort cohort) throws IOException;

    /**
     * Update the current cohorts object to the local lucene repository.
     *
     * @param cohorts the cohorts to be updated.
     * @throws IOException when search api unable to process the resource.
     */
    void updateCohorts(final List<Cohort> cohorts) throws IOException;

    /**
     * Get a single cohort record from the repository using the uuid.
     *
     * @param uuid the cohort uuid.
     * @return cohort with matching uuid or null when no cohort match the uuid.
     * @throws IOException when search api unable to process the resource.
     * @should return cohort with matching uuid.
     * @should return null when no cohort match the uuid.
     */
    Cohort getCohortByUuid(final String uuid) throws IOException;

    /**
     * Get list of cohorts based on the name of the cohort. If empty string is passed, it will search for all cohorts.
     *
     * @param name the partial name of the cohort.
     * @return list of all cohorts with matching uuid or empty list when no cohort match the name.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return list of all cohorts with matching name.
     * @should return empty list when no cohort match the name.
     */
    List<Cohort> getCohortsByName(final String name) throws IOException, ParseException;

    /**
     * @return all registered cohort or empty list when no cohort is registered.
     * @throws ParseException when query parser from lucene unable to parse the query string.
     * @throws IOException    when search api unable to process the resource.
     * @should return all registered cohorts.
     * @should return empty list when no cohort is registered.
     */
    List<Cohort> getAllCohorts() throws IOException, ParseException;

    /**
     * Delete a single cohort record from the repository.
     *
     * @param cohort the cohort to be deleted.
     * @throws IOException when search api unable to process the resource.
     * @should delete the cohort from lucene repository.
     */
    void deleteCohort(final Cohort cohort) throws IOException;

    /**
     * Download a single cohort definition record from the cohort definition rest resource and convert them into
     * <code>CohortDefinition</code> object.
     *
     * @param cohortDefinitionUuid the uuid of the cohort definition.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download cohort definition with matching uuid.
     */
    CohortDefinition downloadCohortDefinitionByUuid(final String cohortDefinitionUuid) throws IOException;

    /**
     * Download all cohort definitions with name similar to the partial name passed in the parameter.
     *
     * @param name the partial name of the cohort definition to be downloaded. When empty, will return all cohort
     *             definitions available.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should download all cohort definitions with partially matched name.
     * @should download all cohort definitions when name is empty.
     */
    List<CohortDefinition> downloadCohortDefinitionsByName(final String name) throws IOException;

    /**
     * Save the current cohort definition object to the local lucene repository.
     *
     * @param cohortDefinition the cohort definition to be saved.
     * @throws java.io.IOException when search api unable to process the resource.
     */
    void saveCohortDefinition(final CohortDefinition cohortDefinition) throws IOException;

    /**
     * Save the current cohort definition objects to the local lucene repository.
     *
     * @param cohortDefinitions the cohort definitions to be saved.
     * @throws java.io.IOException when search api unable to process the resource.
     */
    void saveCohortDefinitions(final List<CohortDefinition> cohortDefinitions) throws IOException;

    /**
     * Update the current cohort definition object to the local lucene repository.
     *
     * @param cohortDefinition the cohort definition to be updated.
     * @throws java.io.IOException when search api unable to process the resource.
     */
    void updateCohortDefinition(final CohortDefinition cohortDefinition) throws IOException;

    /**
     * Update the current cohort definition objects to the local lucene repository.
     *
     * @param cohortDefinitions the cohort definitions to be updated.
     * @throws java.io.IOException when search api unable to process the resource.
     */
    void updateCohortDefinitions(final List<CohortDefinition> cohortDefinitions) throws IOException;

    /**
     * Get a single cohort definition record from the repository using the uuid.
     *
     * @param cohortDefinitionUuid the cohort definition uuid.
     * @return cohort definition with matching uuid or null when no cohort definition match the uuid.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return cohort definition with matching uuid.
     * @should return null when no cohort definition match the uuid.
     */
    CohortDefinition getCohortDefinitionByUuid(final String cohortDefinitionUuid) throws IOException;

    /**
     * Get list of cohort definitions based on the name of the cohort definition. If empty string is passed, it will
     * search for all cohort definitions.
     *
     * @param name the partial name of the cohort definition.
     * @return list of all cohort definitions with matching uuid or empty list when no cohort definition match the name.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return list of all cohort definitions with matching name.
     * @should return empty list when no cohort definition match the name.
     */
    List<CohortDefinition> getCohortDefinitionsByName(final String name) throws IOException, ParseException;

    /**
     * Get all cohort definitions saved in the local lucene repository.
     *
     * @return all registered cohort definition or empty list when no cohort definition is registered.
     * @throws org.apache.lucene.queryParser.ParseException
     *                             when query parser from lucene unable to parse the query string.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should return all registered cohort definitions.
     * @should return empty list when no cohort definition is registered.
     */
    List<CohortDefinition> getAllCohortDefinitions() throws IOException, ParseException;

    /**
     * Delete a single cohort definition record from the repository.
     *
     * @param cohort the cohort definition to be deleted.
     * @throws java.io.IOException when search api unable to process the resource.
     * @should delete the cohort definition from lucene repository.
     */
    void deleteCohortDefinition(final CohortDefinition cohort) throws IOException;

    /**
     * Download data for the cohort or cohort definition identified by the uuid. The flag for dynamic will determine
     * whether the API should download the data from the reporting resource or the static cohort resource.
     * <p/>
     * This method call will return a cohort data object which will hold the cohort information, the member information,
     * and the patients information. The member will create a mapping between the patient and the cohort.
     *
     * @param uuid    the uuid of the cohort or the cohort definition.
     * @param dynamic flag whether to use reporting module or static cohort resource.
     * @return the cohort data based on the uuid.
     * @throws java.io.IOException when search api unable to process the resource.
     */
    CohortData downloadCohortData(final String uuid, final boolean dynamic) throws IOException;

    /**
     * Save the cohort member object to the local lucene directory.
     *
     * @param cohortMember the member object to be saved.
     * @throws IOException when search api unable to process the resource.
     */
    void saveCohortMember(final CohortMember cohortMember) throws IOException;

    /**
     * Save the cohort member objects to the local lucene directory.
     *
     * @param cohortMembers the member objects to be saved.
     * @throws IOException when search api unable to process the resource.
     */
    void saveCohortMembers(final List<CohortMember> cohortMembers) throws IOException;

    /**
     * Update the cohort member object to the local lucene directory.
     *
     * @param cohortMember the member object to be updated.
     * @throws IOException when search api unable to process the resource.
     */
    void updateCohortMember(final CohortMember cohortMember) throws IOException;

    /**
     * Update the cohort member objects to the local lucene directory.
     *
     * @param cohortMembers the member objects to be updated.
     * @throws IOException when search api unable to process the resource.
     */
    void updateCohortMembers(final List<CohortMember> cohortMembers) throws IOException;

    /**
     * Get all members under the current cohort identified by the cohort's uuid which already saved in the local
     * repository.
     *
     * @param cohortUuid the cohort's uuid.
     * @return list of all patients' uuid under current cohort uuid or empty list when no patient are in the cohort.
     * @throws IOException when search api unable to process the resource.
     * @should return list of all members for the cohort.
     * @should return empty list when no member are in the cohort.
     */
    List<CohortMember> getCohortMembers(final String cohortUuid) throws IOException;

    /**
     * Delete all members for the current cohort identified by the cohort's uuid.
     *
     * @param cohortUuid the cohort's uuid.
     * @throws IOException when search api unable to process the resource.
     * @should delete all members for the cohort from the local repository.
     */
    void deleteCohortMembers(final String cohortUuid) throws IOException;
}