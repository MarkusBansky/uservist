# InvitationsApi

All URIs are relative to *http://localhost:9090/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**acceptInvitation**](InvitationsApi.md#acceptInvitation) | **GET** /invitations | Accept invitation.
[**createInvitation**](InvitationsApi.md#createInvitation) | **POST** /invitations | Create invitation.



## acceptInvitation

> acceptInvitation(token)

Accept invitation.

User accepts the invitation and acquires role and connection for the specific service.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.InvitationsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        InvitationsApi apiInstance = new InvitationsApi(defaultClient);
        String token = "token_example"; // String | Invitation token.
        try {
            apiInstance.acceptInvitation(token);
        } catch (ApiException e) {
            System.err.println("Exception when calling InvitationsApi#acceptInvitation");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| Invitation token. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully aquired service role. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |


## createInvitation

> UserServiceInvitationLinkDto createInvitation(userServiceInvitationDto)

Create invitation.

Create an invitation for some user to be added to the service, user would receive a specific role when they approve the invitation. Invitation is sent by email.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.InvitationsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        InvitationsApi apiInstance = new InvitationsApi(defaultClient);
        UserServiceInvitationDto userServiceInvitationDto = new UserServiceInvitationDto(); // UserServiceInvitationDto | 
        try {
            UserServiceInvitationLinkDto result = apiInstance.createInvitation(userServiceInvitationDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling InvitationsApi#createInvitation");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userServiceInvitationDto** | [**UserServiceInvitationDto**](UserServiceInvitationDto.md)|  | [optional]

### Return type

[**UserServiceInvitationLinkDto**](UserServiceInvitationLinkDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfull invitation sent. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |

