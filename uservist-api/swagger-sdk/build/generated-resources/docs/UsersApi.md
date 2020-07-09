# UsersApi

All URIs are relative to *http://localhost:9090/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**usersCreate**](UsersApi.md#usersCreate) | **POST** /users | Create new user.
[**usersCreatePermission**](UsersApi.md#usersCreatePermission) | **POST** /users/{userId}/permission/{serviceId} | Create new permission and assign a role to user for specific service.
[**usersDelete**](UsersApi.md#usersDelete) | **DELETE** /users/{id} | Delete user record by user ID.
[**usersGetAll**](UsersApi.md#usersGetAll) | **GET** /users | Gett all users in the system for admins only. If you are a super adming then you can get all users for current service.
[**usersGetById**](UsersApi.md#usersGetById) | **GET** /users/{id} | Get user information by user ID.
[**usersUpdate**](UsersApi.md#usersUpdate) | **PUT** /users | Update user information by user ID.



## usersCreate

> UserDto usersCreate(userDto)

Create new user.

Creates a new user if can and if the creator token has a valid admin role on the service.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.UsersApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        UsersApi apiInstance = new UsersApi(defaultClient);
        UserDto userDto = new UserDto(); // UserDto | 
        try {
            UserDto result = apiInstance.usersCreate(userDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#usersCreate");
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
 **userDto** | [**UserDto**](UserDto.md)|  | [optional]

### Return type

[**UserDto**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully created a new user account. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |


## usersCreatePermission

> usersCreatePermission(userId, serviceId, inlineObject)

Create new permission and assign a role to user for specific service.

Creates a new user service role that permits user to access specific service depending on their role priveledges.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.UsersApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        UsersApi apiInstance = new UsersApi(defaultClient);
        Long userId = 56L; // Long | The unique identifier of user to search for.
        Long serviceId = 56L; // Long | The unique identifier of service to search for.
        InlineObject inlineObject = new InlineObject(); // InlineObject | 
        try {
            apiInstance.usersCreatePermission(userId, serviceId, inlineObject);
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#usersCreatePermission");
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
 **userId** | **Long**| The unique identifier of user to search for. |
 **serviceId** | **Long**| The unique identifier of service to search for. |
 **inlineObject** | [**InlineObject**](InlineObject.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully created user permision for the service. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |
| **404** | User or Service are not found. |  -  |


## usersDelete

> usersDelete(id)

Delete user record by user ID.

Delete user by user ID. Throws error message if user does not have enough permissions.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.UsersApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        UsersApi apiInstance = new UsersApi(defaultClient);
        Long id = 56L; // Long | The unique identifier of user to search for.
        try {
            apiInstance.usersDelete(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#usersDelete");
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
 **id** | **Long**| The unique identifier of user to search for. |

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
| **200** | User record has been deleted successfully. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |
| **404** | Such service does not exist. Could not delete an unexisting user. |  -  |


## usersGetAll

> List&lt;UserDto&gt; usersGetAll(service, page)

Gett all users in the system for admins only. If you are a super adming then you can get all users for current service.

Returns a full list of users available in the system. If user that makes request has enough permissions.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.UsersApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        UsersApi apiInstance = new UsersApi(defaultClient);
        String service = "service_example"; // String | The key of the service
        Integer page = 56; // Integer | Page of users, 10 users per page.
        try {
            List<UserDto> result = apiInstance.usersGetAll(service, page);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#usersGetAll");
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
 **service** | **String**| The key of the service |
 **page** | **Integer**| Page of users, 10 users per page. |

### Return type

[**List&lt;UserDto&gt;**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully returns a list of users. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |


## usersGetById

> UserDto usersGetById(id)

Get user information by user ID.

Get user by user ID. If user has enough permissions then object is returned successfully.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.UsersApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        UsersApi apiInstance = new UsersApi(defaultClient);
        Long id = 56L; // Long | The unique identifier of user to search for.
        try {
            UserDto result = apiInstance.usersGetById(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#usersGetById");
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
 **id** | **Long**| The unique identifier of user to search for. |

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
| **200** | Returns user information object. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |
| **404** | Such service does not exist. Could not get an unexisting user. |  -  |


## usersUpdate

> UserDto usersUpdate(userDto)

Update user information by user ID.

Update user by user ID. If user has enough permissions then user is updated successfully.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.UsersApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        UsersApi apiInstance = new UsersApi(defaultClient);
        UserDto userDto = new UserDto(); // UserDto | 
        try {
            UserDto result = apiInstance.usersUpdate(userDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#usersUpdate");
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
 **userDto** | [**UserDto**](UserDto.md)|  | [optional]

### Return type

[**UserDto**](UserDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns updated user information object. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |
| **404** | Such service does not exist. Could not update an unexisting user. |  -  |

