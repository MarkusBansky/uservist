import {ReducerAction, ReducerActionMethod, ReducerActionTypes} from "../actions/action";

/**
 * Create user action types for HTTP Axios action from a basic name.
 * @param name A solid name with no special characters nor spaces.
 */
export function createReducerActionTypes(name: string): ReducerActionTypes {
  return new ReducerActionTypes(name);
}

/**
 * Constructs an object for axios request function that is later passed to do axios request.
 * @param types A list of types for this action.
 * @param method Request method for HTTP.
 * @param url Endpoint url.
 * @param data Optional data to be sent via request.
 */
export function createReducerAxiosAction(
  types: ReducerActionTypes,
  method: ReducerActionMethod,
  url: string, data?: Object
): ReducerAction<any> {
  return ({
    type: types.typesArray(),
    payload: {
      request: {
        url,
        method,
        data
      }
    }
  });
}