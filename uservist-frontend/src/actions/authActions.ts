import UserAuthenticationDto from "../models/userAuthenticationDto";
import {GET, POST} from "./action";
import {createReducerAction, createReducerAxiosAction} from "../utils/actionsUtils";
import {
  authenticateActionTypes, clearTokenManuallyActionType,
  getCurrentUserActionTypes,
  setAuthWarningActionType,
  setTokenManuallyActionType
} from "../reducers/authReducer";

const pathToAuthenticate = '/auth/login';
const pathToCurrentUser = '/auth/current';

/**
 * Make a request to authenticate endpoint of the service.
 * @param data User authentication request data.
 */
export function authenticate(data: UserAuthenticationDto) {
  return createReducerAxiosAction<UserAuthenticationDto>(authenticateActionTypes, POST, pathToAuthenticate, data);
}

/**
 * Maker request to get current user information by token.
 */
export function getCurrentUserInformation() {
  return createReducerAxiosAction<any>(getCurrentUserActionTypes, GET, pathToCurrentUser, undefined, true);
}

/**
 * Mutate reducer state by setting token manually to the parameter value.
 * @param token Token parameter value.
 */
export function setTokenManually(token: string) {
  return createReducerAction(setTokenManuallyActionType, {token});
}

/**
 * Mutate reducer to change authorization warning message.
 * @param message The message.
 */
export function setAuthWarning(message: string) {
  return createReducerAction(setAuthWarningActionType, {message});
}

/**
 * Used to clear user token on purpose.
 */
export function clearTokenManually() {
  return createReducerAction(clearTokenManuallyActionType);
}