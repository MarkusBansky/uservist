import {
  ReducerAction,
  ReducerActionMethod,
  ReducerActionPayload,
  ReducerActionTypes
} from "../actions/action";

/**
 * Create user action types for HTTP Axios action from a basic name.
 * @param name A solid name with no special characters nor spaces.
 */
export function createReducerActionTypes(name: string): ReducerActionTypes {
  return new ReducerActionTypes(name.toUpperCase());
}

/**
 * Function used to create reducer action type for a plain action.
 * @param name Returns the exact same name
 */
export function createReducerActionType(name: string): string {
  return name.toUpperCase();
}

/**
 * Constructs an object for axios request function that is later passed to do axios request.
 * @param types A list of types for this action.
 * @param method Request method for HTTP.
 * @param url Endpoint url.
 * @param data Optional data to be sent via request.
 * @param useAuth Whether to use bearer token authentication.
 */
export function createReducerAxiosAction<A>(
  types: ReducerActionTypes,
  method: ReducerActionMethod,
  url: string,
  data?: Object,
  useAuth?: boolean
): ReducerAction<ReducerActionPayload<A>> {
  return ({
    types: types.typesArray(),
    payload: {
      request: {
        url,
        method,
        data,
        useAuth
      }
    }
  });
}

/**
 * Create a basic plain reducer action with optional data.
 * @param type Type of action to be executed.
 * @param data Optional data object.
 */
export function createReducerAction(
  type: string,
  data?: Object
) {
  return ({
    type,
    data
  })
}