

# UserDto

Defines user object, user can have diffirent permissions for diffirent roles. If user does not have a role then they do not have access to that service.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Long** | User&#39;s unique identifier. |  [optional]
**firstName** | **String** | First name. | 
**lastName** | **String** | Last name. | 
**username** | **String** | The internal shorted nickname of the user. | 
**password** | **String** | Encrypted users pasword hash. |  [optional]
**email** | **String** | Email. |  [optional]
**serviceRoles** | [**List&lt;ServiceRoleDto&gt;**](ServiceRoleDto.md) | User roles for services. |  [optional]
**createdAt** | [**OffsetDateTime**](OffsetDateTime.md) | The date and time this user was created at. |  [optional]
**updatedAt** | [**OffsetDateTime**](OffsetDateTime.md) | The date and time when this record has been updated. |  [optional]



