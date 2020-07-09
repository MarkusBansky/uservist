

# ServiceDto

Object representing each Service registered in the system. Each service can have unique users and unique user logins.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Long** | Unique identifier. |  [optional]
**name** | **String** | The short name of this service to display on UI. | 
**description** | **String** | String describing this service and it&#39;s purpose. | 
**key** | **String** | String key of SHA256, that is unique for each service. It is used to identify the service authentication request alongside user token. | 
**createdAt** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**updatedAt** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]



