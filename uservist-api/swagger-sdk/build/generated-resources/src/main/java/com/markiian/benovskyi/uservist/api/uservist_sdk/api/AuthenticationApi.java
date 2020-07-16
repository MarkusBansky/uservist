package com.markiian.benovskyi.uservist.api.uservist_sdk.api;

import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;

import com.markiian.benovskyi.uservist.api.uservist_sdk.model.UserAuthenticationDto;
import com.markiian.benovskyi.uservist.api.uservist_sdk.model.UserDto;
import com.markiian.benovskyi.uservist.api.uservist_sdk.model.UserSessionTokenDto;

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
@Component("com.markiian.benovskyi.uservist.api.uservist_sdk.api.AuthenticationApi")
public class AuthenticationApi {
    private ApiClient apiClient;

    public AuthenticationApi() {
        this(new ApiClient());
    }

    @Autowired
    public AuthenticationApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Authenticate user.
     * Try to authenticate user with username, password and unique identifier generated based on their machine.
     * <p><b>200</b> - User identified successfully.
     * <p><b>401</b> - Unauthorized. User credentials are incorrect.
     * @param userAuthenticationDto  (optional)
     * @return UserSessionTokenDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UserSessionTokenDto authenticate(UserAuthenticationDto userAuthenticationDto) throws RestClientException {
        return authenticateWithHttpInfo(userAuthenticationDto).getBody();
    }

    /**
     * Authenticate user.
     * Try to authenticate user with username, password and unique identifier generated based on their machine.
     * <p><b>200</b> - User identified successfully.
     * <p><b>401</b> - Unauthorized. User credentials are incorrect.
     * @param userAuthenticationDto  (optional)
     * @return ResponseEntity&lt;UserSessionTokenDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UserSessionTokenDto> authenticateWithHttpInfo(UserAuthenticationDto userAuthenticationDto) throws RestClientException {
        Object postBody = userAuthenticationDto;
        
        String path = apiClient.expandPath("/auth/login", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<UserSessionTokenDto> returnType = new ParameterizedTypeReference<UserSessionTokenDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get authenticated user info.
     * Get current authenticated user information. Returns user information with user roles for this service.
     * <p><b>200</b> - User found and available
     * @return UserDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UserDto getCurrentUser() throws RestClientException {
        return getCurrentUserWithHttpInfo().getBody();
    }

    /**
     * Get authenticated user info.
     * Get current authenticated user information. Returns user information with user roles for this service.
     * <p><b>200</b> - User found and available
     * @return ResponseEntity&lt;UserDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UserDto> getCurrentUserWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/auth/current", Collections.<String, Object>emptyMap());

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
     * Validate user authentication.
     * Calls to validate user token and service key for a valid session.
     * <p><b>200</b> - User identified successfully.
     * <p><b>401</b> - Unauthorized. User session is incorrect.
     * @param userSessionTokenDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void validate(UserSessionTokenDto userSessionTokenDto) throws RestClientException {
        validateWithHttpInfo(userSessionTokenDto);
    }

    /**
     * Validate user authentication.
     * Calls to validate user token and service key for a valid session.
     * <p><b>200</b> - User identified successfully.
     * <p><b>401</b> - Unauthorized. User session is incorrect.
     * @param userSessionTokenDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> validateWithHttpInfo(UserSessionTokenDto userSessionTokenDto) throws RestClientException {
        Object postBody = userSessionTokenDto;
        
        String path = apiClient.expandPath("/auth/validate", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
