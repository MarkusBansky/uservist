/*
 * Uservist Service API
 * Defines the API enpoints and objects for authentication service. Describes the REST service architecture and models.
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: admin@markiian-benovskyi.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.markiian.benovskyi.uservist.api.uservist_sdk.api;

import com.markiian.benovskyi.uservist.api.uservist_sdk.model.ServiceDto;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ServicesApi
 */
@Ignore
public class ServicesApiTest {

    private final ServicesApi api = new ServicesApi();

    
    /**
     * Create new service.
     *
     * Creates a new service record in the database. If user does not have a permission to create a record, then trow an error.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void servicesCreateTest() {
        ServiceDto serviceDto = null;
        ServiceDto response = api.servicesCreate(serviceDto);

        // TODO: test validations
    }
    
    /**
     * Delete service by ID.
     *
     * Removes a service by it&#39;s ID if it exists.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void servicesDeleteByIdTest() {
        Long id = null;
        api.servicesDeleteById(id);

        // TODO: test validations
    }
    
    /**
     * Get all services.
     *
     * Returns all available services in the system. Only returns the keys fields if user is authenticated via token.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void servicesGetAllTest() {
        Integer page = null;
        List<ServiceDto> response = api.servicesGetAll(page);

        // TODO: test validations
    }
    
    /**
     * Get service by ID.
     *
     * Returns a service information by it&#39;s ID if this service exists, returns key property if user is admin.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void servicesGetByIdTest() {
        Long id = null;
        ServiceDto response = api.servicesGetById(id);

        // TODO: test validations
    }
    
    /**
     * Update service information.
     *
     * Updates service record in the database with new information, if such record exists already, if not then error, returns with a key property if user is admin.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void servicesUpdateByIdTest() {
        ServiceDto serviceDto = null;
        ServiceDto response = api.servicesUpdateById(serviceDto);

        // TODO: test validations
    }
    
}
