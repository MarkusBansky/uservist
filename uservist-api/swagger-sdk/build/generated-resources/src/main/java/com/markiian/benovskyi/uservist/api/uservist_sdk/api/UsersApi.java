package com.markiian.benovskyi.uservist.api.uservist_sdk.api;

import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;

import com.markiian.benovskyi.uservist.api.uservist_sdk.model.GeneralErrorDto;
import com.markiian.benovskyi.uservist.api.uservist_sdk.model.InlineObject;
import com.markiian.benovskyi.uservist.api.uservist_sdk.model.UserDto;

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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-07-09T13:06:45.626587+01:00[Europe/London]")
@Component("com.markiian.benovskyi.uservist.api.uservist_sdk.api.UsersApi")
public class UsersApi {
    private ApiClient apiClient;

    public UsersApi() {
        this(new ApiClient());
    }

    @Autowired
    public UsersApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create new user.
     * Creates a new user if can and if the creator token has a valid admin role on the service.
     * <p><b>200</b> - Successfully created a new user account.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param userDto  (optional)
     * @return UserDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UserDto usersCreate(UserDto userDto) throws RestClientException {
        return usersCreateWithHttpInfo(userDto).getBody();
    }

    /**
     * Create new user.
     * Creates a new user if can and if the creator token has a valid admin role on the service.
     * <p><b>200</b> - Successfully created a new user account.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param userDto  (optional)
     * @return ResponseEntity&lt;UserDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UserDto> usersCreateWithHttpInfo(UserDto userDto) throws RestClientException {
        Object postBody = userDto;
        
        String path = apiClient.expandPath("/users", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<UserDto> returnType = new ParameterizedTypeReference<UserDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Create new permission and assign a role to user for specific service.
     * Creates a new user service role that permits user to access specific service depending on their role priveledges.
     * <p><b>200</b> - Successfully created user permision for the service.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - User or Service are not found.
     * @param userId The unique identifier of user to search for. (required)
     * @param serviceId The unique identifier of service to search for. (required)
     * @param inlineObject  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void usersCreatePermission(Long userId, Long serviceId, InlineObject inlineObject) throws RestClientException {
        usersCreatePermissionWithHttpInfo(userId, serviceId, inlineObject);
    }

    /**
     * Create new permission and assign a role to user for specific service.
     * Creates a new user service role that permits user to access specific service depending on their role priveledges.
     * <p><b>200</b> - Successfully created user permision for the service.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - User or Service are not found.
     * @param userId The unique identifier of user to search for. (required)
     * @param serviceId The unique identifier of service to search for. (required)
     * @param inlineObject  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> usersCreatePermissionWithHttpInfo(Long userId, Long serviceId, InlineObject inlineObject) throws RestClientException {
        Object postBody = inlineObject;
        
        // verify the required parameter 'userId' is set
        if (userId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userId' when calling usersCreatePermission");
        }
        
        // verify the required parameter 'serviceId' is set
        if (serviceId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'serviceId' when calling usersCreatePermission");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userId", userId);
        uriVariables.put("serviceId", serviceId);
        String path = apiClient.expandPath("/users/{userId}/permission/{serviceId}", uriVariables);

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Delete user record by user ID.
     * Delete user by user ID. Throws error message if user does not have enough permissions.
     * <p><b>200</b> - User record has been deleted successfully.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist. Could not delete an unexisting user.
     * @param id The unique identifier of user to search for. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void usersDelete(Long id) throws RestClientException {
        usersDeleteWithHttpInfo(id);
    }

    /**
     * Delete user record by user ID.
     * Delete user by user ID. Throws error message if user does not have enough permissions.
     * <p><b>200</b> - User record has been deleted successfully.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist. Could not delete an unexisting user.
     * @param id The unique identifier of user to search for. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> usersDeleteWithHttpInfo(Long id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling usersDelete");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/users/{id}", uriVariables);

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
     * Gett all users in the system for admins only. If you are a super adming then you can get all users for current service.
     * Returns a full list of users available in the system. If user that makes request has enough permissions.
     * <p><b>200</b> - Successfully returns a list of users.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param service The key of the service (required)
     * @param page Page of users, 10 users per page. (required)
     * @return List&lt;UserDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<UserDto> usersGetAll(String service, Integer page) throws RestClientException {
        return usersGetAllWithHttpInfo(service, page).getBody();
    }

    /**
     * Gett all users in the system for admins only. If you are a super adming then you can get all users for current service.
     * Returns a full list of users available in the system. If user that makes request has enough permissions.
     * <p><b>200</b> - Successfully returns a list of users.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param service The key of the service (required)
     * @param page Page of users, 10 users per page. (required)
     * @return ResponseEntity&lt;List&lt;UserDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UserDto>> usersGetAllWithHttpInfo(String service, Integer page) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'service' is set
        if (service == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'service' when calling usersGetAll");
        }
        
        // verify the required parameter 'page' is set
        if (page == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'page' when calling usersGetAll");
        }
        
        String path = apiClient.expandPath("/users", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "service", service));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "page", page));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<UserDto>> returnType = new ParameterizedTypeReference<List<UserDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get user information by user ID.
     * Get user by user ID. If user has enough permissions then object is returned successfully.
     * <p><b>200</b> - Returns user information object.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist. Could not get an unexisting user.
     * @param id The unique identifier of user to search for. (required)
     * @return UserDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UserDto usersGetById(Long id) throws RestClientException {
        return usersGetByIdWithHttpInfo(id).getBody();
    }

    /**
     * Get user information by user ID.
     * Get user by user ID. If user has enough permissions then object is returned successfully.
     * <p><b>200</b> - Returns user information object.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist. Could not get an unexisting user.
     * @param id The unique identifier of user to search for. (required)
     * @return ResponseEntity&lt;UserDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UserDto> usersGetByIdWithHttpInfo(Long id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling usersGetById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/users/{id}", uriVariables);

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

        ParameterizedTypeReference<UserDto> returnType = new ParameterizedTypeReference<UserDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update user information by user ID.
     * Update user by user ID. If user has enough permissions then user is updated successfully.
     * <p><b>200</b> - Returns updated user information object.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist. Could not update an unexisting user.
     * @param userDto  (optional)
     * @return UserDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UserDto usersUpdate(UserDto userDto) throws RestClientException {
        return usersUpdateWithHttpInfo(userDto).getBody();
    }

    /**
     * Update user information by user ID.
     * Update user by user ID. If user has enough permissions then user is updated successfully.
     * <p><b>200</b> - Returns updated user information object.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * <p><b>404</b> - Such service does not exist. Could not update an unexisting user.
     * @param userDto  (optional)
     * @return ResponseEntity&lt;UserDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UserDto> usersUpdateWithHttpInfo(UserDto userDto) throws RestClientException {
        Object postBody = userDto;
        
        String path = apiClient.expandPath("/users", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<UserDto> returnType = new ParameterizedTypeReference<UserDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
