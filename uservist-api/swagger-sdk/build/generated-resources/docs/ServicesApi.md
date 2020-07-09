# ServicesApi

All URIs are relative to *http://localhost:9090/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**servicesCreate**](ServicesApi.md#servicesCreate) | **POST** /services | Create new service.
[**servicesDeleteById**](ServicesApi.md#servicesDeleteById) | **DELETE** /services/{id} | Delete service by ID.
[**servicesGetAll**](ServicesApi.md#servicesGetAll) | **GET** /services | Get all services.
[**servicesGetById**](ServicesApi.md#servicesGetById) | **GET** /services/{id} | Get service by ID.
[**servicesUpdateById**](ServicesApi.md#servicesUpdateById) | **PUT** /services | Update service information.



## servicesCreate

> ServiceDto servicesCreate(serviceDto)

Create new service.

Creates a new service record in the database. If user does not have a permission to create a record, then trow an error.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.ServicesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        ServicesApi apiInstance = new ServicesApi(defaultClient);
        ServiceDto serviceDto = new ServiceDto(); // ServiceDto | 
        try {
            ServiceDto result = apiInstance.servicesCreate(serviceDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ServicesApi#servicesCreate");
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
 **serviceDto** | [**ServiceDto**](ServiceDto.md)|  | [optional]

### Return type

[**ServiceDto**](ServiceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully created new service record. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |


## servicesDeleteById

> servicesDeleteById(id)

Delete service by ID.

Removes a service by it&#39;s ID if it exists.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.ServicesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        ServicesApi apiInstance = new ServicesApi(defaultClient);
        Long id = 56L; // Long | The unique identifier of service to search for.
        try {
            apiInstance.servicesDeleteById(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling ServicesApi#servicesDeleteById");
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
 **id** | **Long**| The unique identifier of service to search for. |

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
| **200** | Service deleted successfully. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |
| **404** | Such service does not exist. Could not delete an unexisting service. |  -  |


## servicesGetAll

> List&lt;ServiceDto&gt; servicesGetAll(page)

Get all services.

Returns all available services in the system. Only returns the keys fields if user is authenticated via token.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.ServicesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        ServicesApi apiInstance = new ServicesApi(defaultClient);
        Integer page = 56; // Integer | Page of users, 10 users per page.
        try {
            List<ServiceDto> result = apiInstance.servicesGetAll(page);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ServicesApi#servicesGetAll");
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
 **page** | **Integer**| Page of users, 10 users per page. |

### Return type

[**List&lt;ServiceDto&gt;**](ServiceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfull request, returning all available services in the system. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |


## servicesGetById

> ServiceDto servicesGetById(id)

Get service by ID.

Returns a service information by it&#39;s ID if this service exists, returns key property if user is admin.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.ServicesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        ServicesApi apiInstance = new ServicesApi(defaultClient);
        Long id = 56L; // Long | The unique identifier of service to search for.
        try {
            ServiceDto result = apiInstance.servicesGetById(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ServicesApi#servicesGetById");
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
 **id** | **Long**| The unique identifier of service to search for. |

### Return type

[**ServiceDto**](ServiceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfull request, return the service object. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |
| **404** | Such service does not exist. |  -  |


## servicesUpdateById

> ServiceDto servicesUpdateById(serviceDto)

Update service information.

Updates service record in the database with new information, if such record exists already, if not then error, returns with a key property if user is admin.

### Example

```java
// Import classes:
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiClient;
import com.markiian.benovskyi.uservist.api.uservist_sdk.ApiException;
import com.markiian.benovskyi.uservist.api.uservist_sdk.Configuration;
import com.markiian.benovskyi.uservist.api.uservist_sdk.models.*;
import com.markiian.benovskyi.uservist.api.uservist_sdk.api.ServicesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9090/api");

        ServicesApi apiInstance = new ServicesApi(defaultClient);
        ServiceDto serviceDto = new ServiceDto(); // ServiceDto | 
        try {
            ServiceDto result = apiInstance.servicesUpdateById(serviceDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ServicesApi#servicesUpdateById");
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
 **serviceDto** | [**ServiceDto**](ServiceDto.md)|  | [optional]

### Return type

[**ServiceDto**](ServiceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Service updated successfully, return the updated service object. |  -  |
| **401** | Missing or expired session. |  -  |
| **403** | Missing required privileges. |  -  |
| **404** | Such service does not exist. Could not update an unexisting service. |  -  |

