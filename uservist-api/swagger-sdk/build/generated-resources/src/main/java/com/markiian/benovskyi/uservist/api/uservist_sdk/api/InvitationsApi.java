package com.markiian.benovskyi.uservist.api.uservist_sdk.api;

import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;

import com.markiian.benovskyi.uservist.api.uservist_sdk.model.UserServiceInvitationDto;
import com.markiian.benovskyi.uservist.api.uservist_sdk.model.UserServiceInvitationLinkDto;

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
@Component("com.markiian.benovskyi.uservist.api.uservist_sdk.api.InvitationsApi")
public class InvitationsApi {
    private ApiClient apiClient;

    public InvitationsApi() {
        this(new ApiClient());
    }

    @Autowired
    public InvitationsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Accept invitation.
     * User accepts the invitation and acquires role and connection for the specific service.
     * <p><b>200</b> - Successfully aquired service role.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param token Invitation token. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void acceptInvitation(String token) throws RestClientException {
        acceptInvitationWithHttpInfo(token);
    }

    /**
     * Accept invitation.
     * User accepts the invitation and acquires role and connection for the specific service.
     * <p><b>200</b> - Successfully aquired service role.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param token Invitation token. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> acceptInvitationWithHttpInfo(String token) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'token' is set
        if (token == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'token' when calling acceptInvitation");
        }
        
        String path = apiClient.expandPath("/invitations", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "token", token));

        final String[] accepts = { };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Create invitation.
     * Create an invitation for some user to be added to the service, user would receive a specific role when they approve the invitation. Invitation is sent by email.
     * <p><b>200</b> - Successfull invitation sent.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param userServiceInvitationDto  (optional)
     * @return UserServiceInvitationLinkDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UserServiceInvitationLinkDto createInvitation(UserServiceInvitationDto userServiceInvitationDto) throws RestClientException {
        return createInvitationWithHttpInfo(userServiceInvitationDto).getBody();
    }

    /**
     * Create invitation.
     * Create an invitation for some user to be added to the service, user would receive a specific role when they approve the invitation. Invitation is sent by email.
     * <p><b>200</b> - Successfull invitation sent.
     * <p><b>401</b> - Missing or expired session.
     * <p><b>403</b> - Missing required privileges.
     * @param userServiceInvitationDto  (optional)
     * @return ResponseEntity&lt;UserServiceInvitationLinkDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UserServiceInvitationLinkDto> createInvitationWithHttpInfo(UserServiceInvitationDto userServiceInvitationDto) throws RestClientException {
        Object postBody = userServiceInvitationDto;
        
        String path = apiClient.expandPath("/invitations", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<UserServiceInvitationLinkDto> returnType = new ParameterizedTypeReference<UserServiceInvitationLinkDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
