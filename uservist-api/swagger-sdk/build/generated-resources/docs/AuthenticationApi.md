# AuthenticationApi

All URIs are relative to *http://localhost:9090/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authenticate**](AuthenticationApi.md#authenticate) | **POST** /auth/login | Authenticate user.
[**getCurrentUser**](AuthenticationApi.md#getCurrentUser) | **GET** /auth/current | Get authenticated user info.
[**validate**](AuthenticationApi.md#validate) | **POST** /auth/validate | Validate user authentication.



## authenticate

> UserSessionTokenDto authenticate(userAuthenticationDto)

Authenticate user.

Try to authenticate user with username, password and unique identifier generated based on their machine.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.AuthenticationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
        UserAuthenticationDto userAuthenticationDto = new UserAuthenticationDto(); // UserAuthenticationDto | 
        try {
            UserSessionTokenDto result = apiInstance.authenticate(userAuthenticationDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthenticationApi#authenticate");
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
 **userAuthenticationDto** | [**UserAuthenticationDto**](UserAuthenticationDto.md)|  | [optional]

### Return type

[**UserSessionTokenDto**](UserSessionTokenDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | User identified successfully. |  -  |
| **401** | Unauthorized. User credentials are incorrect. |  -  |


## getCurrentUser

> UserDto getCurrentUser()

Get authenticated user info.

Get current authenticated user information. Returns user information with user roles for this service.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.AuthenticationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
        try {
            UserDto result = apiInstance.getCurrentUser();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthenticationApi#getCurrentUser");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**UserDto**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | User found and available |  -  |


## validate

> validate(userSessionTokenDto)

Validate user authentication.

Calls to validate user token and service key for a valid session.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.AuthenticationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
        UserSessionTokenDto userSessionTokenDto = new UserSessionTokenDto(); // UserSessionTokenDto | 
        try {
            apiInstance.validate(userSessionTokenDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthenticationApi#validate");
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
 **userSessionTokenDto** | [**UserSessionTokenDto**](UserSessionTokenDto.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | User identified successfully. |  -  |
| **401** | Unauthorized. User session is incorrect. |  -  |

