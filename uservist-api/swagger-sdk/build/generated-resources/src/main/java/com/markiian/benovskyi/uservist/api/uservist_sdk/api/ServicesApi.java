package com.markiian.benovskyi.uservist.api.uservist_sdk.api;

import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;

import com.markiian.benovskyi.uservist.api.uservist_sdk.model.ServiceDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-07-16T13:34:50.520141+01:00[Europe/London]")
@Component("com.markiian.benovskyi.uservist.api.uservist_sdk.api.ServicesApi")
public class ServicesApi {
    private ApiClient apiClient;

    public ServicesApi() {
        this(new ApiClient());
    }

    @Autowired
    public ServicesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create new service.
     * Creates a new service record in the database. If user does not have a permission to create a record, then trow an error.
     * <p><b>200</b> - Successfully created new service record.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param serviceDto  (optional)
     * @return ServiceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ServiceDto servicesCreate(ServiceDto serviceDto) throws RestClientException {
        return servicesCreateWithHttpInfo(serviceDto).getBody();
    }

    /**
     * Create new service.
     * Creates a new service record in the database. If user does not have a permission to create a record, then trow an error.
     * <p><b>200</b> - Successfully created new service record.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param serviceDto  (optional)
     * @return ResponseEntity&lt;ServiceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ServiceDto> servicesCreateWithHttpInfo(ServiceDto serviceDto) throws RestClientException {
        Object postBody = serviceDto;
        
        String path = apiClient.expandPath("/services", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<ServiceDto> returnType = new ParameterizedTypeReference<ServiceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Delete service by ID.
     * Removes a service by it&#39;s ID if it exists.
     * <p><b>200</b> - Service deleted successfully.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist. Could not delete an unexisting service.
     * @param id The unique identifier of service to search for. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void servicesDeleteById(Long id) throws RestClientException {
        servicesDeleteByIdWithHttpInfo(id);
    }

    /**
     * Delete service by ID.
     * Removes a service by it&#39;s ID if it exists.
     * <p><b>200</b> - Service deleted successfully.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist. Could not delete an unexisting service.
     * @param id The unique identifier of service to search for. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> servicesDeleteByIdWithHttpInfo(Long id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling servicesDeleteById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/services/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get all services.
     * Returns all available services in the system. Only returns the keys fields if user is authenticated via token.
     * <p><b>200</b> - Successfull request, returning all available services in the system.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param page Page of users, 10 users per page. (required)
     * @return List&lt;ServiceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ServiceDto> servicesGetAll(Integer page) throws RestClientException {
        return servicesGetAllWithHttpInfo(page).getBody();
    }

    /**
     * Get all services.
     * Returns all available services in the system. Only returns the keys fields if user is authenticated via token.
     * <p><b>200</b> - Successfull request, returning all available services in the system.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param page Page of users, 10 users per page. (required)
     * @return ResponseEntity&lt;List&lt;ServiceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ServiceDto>> servicesGetAllWithHttpInfo(Integer page) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'page' is set
        if (page == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'page' when calling servicesGetAll");
        }
        
        String path = apiClient.expandPath("/services", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "page", page));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<ServiceDto>> returnType = new ParameterizedTypeReference<List<ServiceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get service by ID.
     * Returns a service information by it&#39;s ID if this service exists, returns key property if user is admin.
     * <p><b>200</b> - Successfull request, return the service object.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist.
     * @param id The unique identifier of service to search for. (required)
     * @return ServiceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ServiceDto servicesGetById(Long id) throws RestClientException {
        return servicesGetByIdWithHttpInfo(id).getBody();
    }

    /**
     * Get service by ID.
     * Returns a service information by it&#39;s ID if this service exists, returns key property if user is admin.
     * <p><b>200</b> - Successfull request, return the service object.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist.
     * @param id The unique identifier of service to search for. (required)
     * @return ResponseEntity&lt;ServiceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ServiceDto> servicesGetByIdWithHttpInfo(Long id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling servicesGetById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/services/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<ServiceDto> returnType = new ParameterizedTypeReference<ServiceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update service information.
     * Updates service record in the database with new information, if such record exists already, if not then error, returns with a key property if user is admin.
     * <p><b>200</b> - Service updated successfully, return the updated service object.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist. Could not update an unexisting service.
     * @param serviceDto  (optional)
     * @return ServiceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ServiceDto servicesUpdateById(ServiceDto serviceDto) throws RestClientException {
        return servicesUpdateByIdWithHttpInfo(serviceDto).getBody();
    }

    /**
     * Update service information.
     * Updates service record in the database with new information, if such record exists already, if not then error, returns with a key property if user is admin.
     * <p><b>200</b> - Service updated successfully, return the updated service object.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist. Could not update an unexisting service.
     * @param serviceDto  (optional)
     * @return ResponseEntity&lt;ServiceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ServiceDto> servicesUpdateByIdWithHttpInfo(ServiceDto serviceDto) throws RestClientException {
        Object postBody = serviceDto;
        
        String path = apiClient.expandPath("/services", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<ServiceDto> returnType = new ParameterizedTypeReference<ServiceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
