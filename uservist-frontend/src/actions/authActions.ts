import UserAuthenticationDto from "../models/userAuthenticationDto";
import {POST} from "./action";
import {createReducerAxiosAction} from "../utils/actionsUtils";
import {authenticateActionTypes} from "../reducers/authReducer";

const pathToAuthenticate = '/auth/login';

/**
 * Make a request to authenticate endpoint of the service.
 * @param data User authentication request data.
 */
export function authenticate(data: UserAuthenticationDto) {
  return createReducerAxiosAction<UserAuthenticationDto>(authenticateActionTypes, POST, pathToAuthenticate, data);
}