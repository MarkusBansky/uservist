

# SessionDto

Describes a unique user logged session object. This is unique to user, service, hardwareId.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Long** | Unique identifier. |  [optional]
**user** | [**UserDto**](UserDto.md) |  | 
**service** | [**ServiceDto**](ServiceDto.md) |  | 
**hardwareId** | **String** | Unique hardware identifier. | 
**token** | **String** | Generated token. |  [optional]
**createdAt** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**updatedAt** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]



