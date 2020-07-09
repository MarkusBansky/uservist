

# UserAuthenticationDto

Body authentication request used to call the authenticate endpoint to log in user and create session.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**username** | **String** | The user identifier as a unique username. |  [optional]
**password** | **String** | User&#39;s password hashed with SHA256. |  [optional]
**hardwareId** | **String** | A unique user identifier for this specific machine. |  [optional]
**key** | **String** | Service key identifier. |  [optional]



