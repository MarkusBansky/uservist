import UserAuthenticationDto from "../models/userAuthenticationDto";
import {POST, ReducerAction} from "./action";
import UserAuthenticationResponseDto from "../models/userAuthenticationResponseDto";
import {createReducerAxiosAction} from "../utils/actionsUtils";
import {authenticateActionTypes} from "../reducers/authReducer";

const pathToAuthenticate = '/auth/login';

/**
 * Make a request to authenticate endpoint of the service.
 * @param data User authentication request data.
 */
export function authenticate(data: UserAuthenticationDto): ReducerAction<UserAuthenticationResponseDto> {
  return createReducerAxiosAction(authenticateActionTypes, POST, pathToAuthenticate, data);
}