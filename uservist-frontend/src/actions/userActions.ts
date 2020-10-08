import {GET} from "./action";
import {createReducerAxiosAction} from "../utils/actionsUtils";
import {
  getCurrentUserActionTypes
} from "../reducers/authReducer";
import "../utils/stringFormat";

const pathToUserById = '/api/v1/users/{0}';


/**
 * Maker request to get current user information by token.
 */
export function getUserById(id: number) {
  return createReducerAxiosAction<any>(
    getCurrentUserActionTypes,
    GET,
    pathToUserById.format(id.toString()),
    undefined,
    true
  );
}