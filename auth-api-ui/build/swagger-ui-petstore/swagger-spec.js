window.swaggerSpec={
  "openapi" : "3.0.0",
  "info" : {
    "title" : "Authentication API",
    "description" : "Defines the API enpoints and objects for authentication service. Describes the REST service architecture and models.",
    "contact" : {
      "name" : "Markiian Benovskyi",
      "url" : "http://markiian-benovskyi.com",
      "email" : "connect@markiian-benovskyi.com"
    },
    "version" : "0.0.1"
  },
  "servers" : [ {
    "url" : "http://localhost:9090/v1",
    "description" : "Local server for authentication service."
  } ],
  "tags" : [ {
    "name" : "Services",
    "description" : "Service represents a single connected SaaS that uses this authentication to autorize users, it has a unique key and uses user token for authentication."
  }, {
    "name" : "Users",
    "description" : "Each user can have access to specific services with a specific role, then it can authenticate and receive a token."
  } ],
  "components" : {
    "schemas" : {
      "GeneralError" : {
        "description" : "Object that is returned in case of any error happening on the server side. The code is HTTP code.",
        "type" : "object",
        "required" : [ "code", "message" ],
        "properties" : {
          "code" : {
            "description" : "The HTTP response code.",
            "type" : "integer",
            "format" : "int32"
          },
          "message" : {
            "description" : "Message describing the error.",
            "type" : "string"
          }
        }
      },
      "UserServiceRole" : {
        "description" : "Describes user's roles for services.",
        "type" : "object",
        "properties" : {
          "id" : {
            "description" : "Unique identifier.",
            "type" : "integer",
            "format" : "int64"
          },
          "user" : {
            "$ref" : "#/components/schemas/User"
          },
          "service" : {
            "$ref" : "#/components/schemas/Service"
          },
          "role" : {
            "type" : "string",
            "enum" : [ "USER", "MODER", "ADMIN" ]
          },
          "createdAt" : {
            "type" : "string",
            "format" : "date-time"
          }
        }
      },
      "User" : {
        "description" : "Defines user object, user can have diffirent permissions for diffirent roles. If user does not have a role then they do not have access to that service.",
        "type" : "object",
        "required" : [ "firstName", "lastName", "username", "passwordHash" ],
        "properties" : {
          "id" : {
            "description" : "User's unique identifier.",
            "type" : "integer",
            "format" : "int64"
          },
          "firstName" : {
            "description" : "First name.",
            "type" : "string"
          },
          "lastName" : {
            "description" : "Last name.",
            "type" : "string"
          },
          "username" : {
            "description" : "The internal shorted nickname of the user.",
            "type" : "string"
          },
          "passwordHash" : {
            "description" : "Encrypted users pasword hash.",
            "type" : "string"
          },
          "roles" : {
            "description" : "Users active roles in services.",
            "type" : "array",
            "items" : {
              "$ref" : "#/components/schemas/UserServiceRole"
            }
          },
          "createdAt" : {
            "description" : "The date and time this user was created at.",
            "type" : "string",
            "format" : "date-time"
          },
          "updatedAt" : {
            "description" : "The date and time when this record has been updated.",
            "type" : "string",
            "format" : "date-time"
          }
        }
      },
      "Service" : {
        "description" : "Object representing each Service registered in the system. Each service can have unique users and unique user logins.",
        "type" : "object",
        "required" : [ "name", "description", "key" ],
        "properties" : {
          "id" : {
            "description" : "Unique identifier.",
            "type" : "integer",
            "format" : "int64"
          },
          "name" : {
            "description" : "The short name of this service to display on UI.",
            "type" : "string"
          },
          "description" : {
            "description" : "String describing this service and it's purpose.",
            "type" : "string"
          },
          "key" : {
            "description" : "String key of SHA256, that is unique for each service. It is used to identify the service authentication request alongside user token.",
            "type" : "string"
          },
          "createdAt" : {
            "type" : "string",
            "format" : "date-time"
          },
          "updatedAt" : {
            "type" : "string",
            "format" : "date-time"
          }
        }
      }
    }
  },
  "paths" : {
    "/services" : {
      "get" : {
        "summary" : "Get all services.",
        "operationId" : "servicesGetAll",
        "tags" : [ "Services" ],
        "description" : "Returns all available services in the system. Only returns the keys fields if user is authenticated via token.",
        "responses" : {
          "200" : {
            "description" : "Successfull request, returning all available services in the system.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "type" : "array",
                  "items" : {
                    "$ref" : "#/components/schemas/Service"
                  }
                }
              }
            }
          },
          "401" : {
            "description" : "Missing or expired session.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/GeneralError"
                }
              }
            }
          },
          "403" : {
            "description" : "Missing required privileges.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/GeneralError"
                }
              }
            }
          }
        }
      }
    },
    "/services/{id}" : {
      "get" : {
        "summary" : "Get service by ID.",
        "operationId" : "servicesGetById",
        "tags" : [ "Services" ],
        "description" : "Returns a service information by it's ID if this service exists, returns key property if user is admin.",
        "parameters" : [ {
          "name" : "id",
          "in" : "path",
          "required" : true,
          "description" : "The unique identifier of service to search for.",
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Successfull request, return the service object.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Service"
                }
              }
            }
          },
          "401" : {
            "description" : "Missing or expired session.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/GeneralError"
                }
              }
            }
          },
          "403" : {
            "description" : "Missing required privileges.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/GeneralError"
                }
              }
            }
          },
          "404" : {
            "description" : "Such service does not exist.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/GeneralError"
                }
              }
            }
          }
        }
      }
    },
    "/users" : {
      "post" : {
        "summary" : "Create new user.",
        "operationId" : "usersCreate",
        "tags" : [ "Users" ],
        "description" : "Creates a new user if can and if the creator token has a valid admin role on the service.",
        "requestBody" : {
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/User"
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "Successfully created a new user account.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/User"
                }
              }
            }
          },
          "401" : {
            "description" : "Missing or expired session.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/GeneralError"
                }
              }
            }
          },
          "403" : {
            "description" : "Missing required privileges.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/GeneralError"
                }
              }
            }
          },
          "404" : {
            "description" : "Such service does not exist.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/GeneralError"
                }
              }
            }
          }
        }
      }
    }
  }
}