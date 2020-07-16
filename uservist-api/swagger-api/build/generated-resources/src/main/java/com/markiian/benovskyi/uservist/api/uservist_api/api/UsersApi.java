/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.markiian.benovskyi.uservist.api.uservist_api.api;

import com.markiian.benovskyi.uservist.api.uservist_api.model.GeneralErrorDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.InlineObject;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-16T13:34:50.527602+01:00[Europe/London]")

@Validated
@Api(value = "users", description = "the users API")
public interface UsersApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /users : Create new user.
     * Creates a new user if can and if the creator token has a valid admin role on the service.
     *
     * @param userDto  (optional)
     * @return Successfully created a new user account. (status code 200)
     *         or Missing or expired session. (status code 401)
     *         or Missing required privileges. (status code 403)
     */
    @ApiOperation(value = "Create new user.", nickname = "usersCreate", notes = "Creates a new user if can and if the creator token has a valid admin role on the service.", response = UserDto.class, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successfully created a new user account.", response = UserDto.class),
        @ApiResponse(code = 401, message = "Missing or expired session."),
        @ApiResponse(code = 403, message = "Missing required privileges.") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<UserDto> usersCreate(@ApiParam(value = ""  )  @Valid @RequestBody(required = false) UserDto userDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"password\" : \"password\", \"serviceRoles\" : [ { \"service\" : { \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : 0, \"key\" : \"key\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" } }, { \"service\" : { \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : 0, \"key\" : \"key\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" } } ], \"id\" : 0, \"email\" : \"email\", \"username\" : \"username\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /users/{userId}/permission/{serviceId} : Create new permission and assign a role to user for specific service.
     * Creates a new user service role that permits user to access specific service depending on their role priveledges.
     *
     * @param userId The unique identifier of user to search for. (required)
     * @param serviceId The unique identifier of service to search for. (required)
     * @param inlineObject  (optional)
     * @return Successfully created user permision for the service. (status code 200)
     *         or Missing or expired session. (status code 401)
     *         or Missing required privileges. (status code 403)
     *         or User or Service are not found. (status code 404)
     */
    @ApiOperation(value = "Create new permission and assign a role to user for specific service.", nickname = "usersCreatePermission", notes = "Creates a new user service role that permits user to access specific service depending on their role priveledges.", tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successfully created user permision for the service."),
        @ApiResponse(code = 401, message = "Missing or expired session.", response = GeneralErrorDto.class),
        @ApiResponse(code = 403, message = "Missing required privileges."),
        @ApiResponse(code = 404, message = "User or Service are not found.") })
    @RequestMapping(value = "/users/{userId}/permission/{serviceId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> usersCreatePermission(@ApiParam(value = "The unique identifier of user to search for.",required=true) @PathVariable("userId") Long userId,@ApiParam(value = "The unique identifier of service to search for.",required=true) @PathVariable("serviceId") Long serviceId,@ApiParam(value = ""  )  @Valid @RequestBody(required = false) InlineObject inlineObject) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /users/{id} : Delete user record by user ID.
     * Delete user by user ID. Throws error message if user does not have enough permissions.
     *
     * @param id The unique identifier of user to search for. (required)
     * @return User record has been deleted successfully. (status code 200)
     *         or Missing or expired session. (status code 401)
     *         or Missing required privileges. (status code 403)
     *         or Such service does not exist. Could not delete an unexisting user. (status code 404)
     */
    @ApiOperation(value = "Delete user record by user ID.", nickname = "usersDelete", notes = "Delete user by user ID. Throws error message if user does not have enough permissions.", tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "User record has been deleted successfully."),
        @ApiResponse(code = 401, message = "Missing or expired session."),
        @ApiResponse(code = 403, message = "Missing required privileges."),
        @ApiResponse(code = 404, message = "Such service does not exist. Could not delete an unexisting user.") })
    @RequestMapping(value = "/users/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> usersDelete(@ApiParam(value = "The unique identifier of user to search for.",required=true) @PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /users : Gett all users in the system for admins only. If you are a super adming then you can get all users for current service.
     * Returns a full list of users available in the system. If user that makes request has enough permissions.
     *
     * @param service The key of the service (required)
     * @param page Page of users, 10 users per page. (required)
     * @return Successfully returns a list of users. (status code 200)
     *         or Missing or expired session. (status code 401)
     *         or Missing required privileges. (status code 403)
     */
    @ApiOperation(value = "Gett all users in the system for admins only. If you are a super adming then you can get all users for current service.", nickname = "usersGetAll", notes = "Returns a full list of users available in the system. If user that makes request has enough permissions.", response = UserDto.class, responseContainer = "List", tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successfully returns a list of users.", response = UserDto.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Missing or expired session."),
        @ApiResponse(code = 403, message = "Missing required privileges.") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<UserDto>> usersGetAll(@NotNull @ApiParam(value = "The key of the service", required = true) @Valid @RequestParam(value = "service", required = true) String service,@NotNull @ApiParam(value = "Page of users, 10 users per page.", required = true) @Valid @RequestParam(value = "page", required = true) Integer page) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"password\" : \"password\", \"serviceRoles\" : [ { \"service\" : { \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : 0, \"key\" : \"key\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" } }, { \"service\" : { \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : 0, \"key\" : \"key\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" } } ], \"id\" : 0, \"email\" : \"email\", \"username\" : \"username\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /users/{id} : Get user information by user ID.
     * Get user by user ID. If user has enough permissions then object is returned successfully.
     *
     * @param id The unique identifier of user to search for. (required)
     * @return Returns user information object. (status code 200)
     *         or Missing or expired session. (status code 401)
     *         or Missing required privileges. (status code 403)
     *         or Such service does not exist. Could not get an unexisting user. (status code 404)
     */
    @ApiOperation(value = "Get user information by user ID.", nickname = "usersGetById", notes = "Get user by user ID. If user has enough permissions then object is returned successfully.", response = UserDto.class, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns user information object.", response = UserDto.class),
        @ApiResponse(code = 401, message = "Missing or expired session."),
        @ApiResponse(code = 403, message = "Missing required privileges."),
        @ApiResponse(code = 404, message = "Such service does not exist. Could not get an unexisting user.") })
    @RequestMapping(value = "/users/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<UserDto> usersGetById(@ApiParam(value = "The unique identifier of user to search for.",required=true) @PathVariable("id") Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"password\" : \"password\", \"serviceRoles\" : [ { \"service\" : { \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : 0, \"key\" : \"key\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" } }, { \"service\" : { \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : 0, \"key\" : \"key\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" } } ], \"id\" : 0, \"email\" : \"email\", \"username\" : \"username\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /users : Update user information by user ID.
     * Update user by user ID. If user has enough permissions then user is updated successfully.
     *
     * @param userDto  (optional)
     * @return Returns updated user information object. (status code 200)
     *         or Missing or expired session. (status code 401)
     *         or Missing required privileges. (status code 403)
     *         or Such service does not exist. Could not update an unexisting user. (status code 404)
     */
    @ApiOperation(value = "Update user information by user ID.", nickname = "usersUpdate", notes = "Update user by user ID. If user has enough permissions then user is updated successfully.", response = UserDto.class, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns updated user information object.", response = UserDto.class),
        @ApiResponse(code = 401, message = "Missing or expired session."),
        @ApiResponse(code = 403, message = "Missing required privileges."),
        @ApiResponse(code = 404, message = "Such service does not exist. Could not update an unexisting user.") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<UserDto> usersUpdate(@ApiParam(value = ""  )  @Valid @RequestBody(required = false) UserDto userDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"password\" : \"password\", \"serviceRoles\" : [ { \"service\" : { \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : 0, \"key\" : \"key\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" } }, { \"service\" : { \"createdAt\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : 0, \"key\" : \"key\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" } } ], \"id\" : 0, \"email\" : \"email\", \"username\" : \"username\", \"updatedAt\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
