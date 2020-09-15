import produce from "immer";
import {createReducerActionType, createReducerActionTypes} from "../utils/actionsUtils";
import {ReducerAction} from "../actions/action";
import UserAuthenticationResponseDto from "../models/userAuthenticationResponseDto";
import RequestError from "../models/requestError";
import {AxiosResponse} from "axios";
import moment from "moment";
import UserToken from "../models/userToken";
import Cookies from "js-cookie";

// reducer action type constructed
type AuthReducerActionType = UserAuthenticationResponseDto | undefined;

// action types
export const authenticateActionTypes = createReducerActionTypes('authenticateActionTypes');
export const getCurrentUserActionTypes = createReducerActionTypes('getCurrentUserActionTypes');
export const setTokenManuallyActionType = createReducerActionType('setTokenManuallyActionType');
export const setAuthWarningActionType = createReducerActionType('setAuthWarningActionType');


// reducer interface
export interface AuthReducer {
  token?: UserToken;
  message?: string;
  error?: RequestError;
  loading: boolean;
}

const defaultState: AuthReducer = {
  loading: true
};

// reducer itself
export const authReducer = (state = defaultState, action: ReducerAction<AxiosResponse<AuthReducerActionType>>) =>
  produce(state, (draft: AuthReducer) => {
  switch(action.type) {
    case authenticateActionTypes.REQUEST:
      draft.loading = true;
      draft.error = undefined;
      draft.message = undefined;
      break;
    case authenticateActionTypes.SUCCESS:
      if (action.payload.data) {
        draft.token = new UserToken(action.payload.data.token);
        Cookies.set('token', action.payload.data.token, { sameSite: 'strict' });
      }
      draft.loading = false;
      break;
    case authenticateActionTypes.FAILURE:
      draft.loading = false;
      draft.token = undefined;
      draft.error = action.error?.response?.data;

      if (!draft.error) {
        draft.error = {
          status: action.error?.response?.status || 1,
          message: action.error?.message || 'Unknown error',
          error: action.error?.message || 'Unknown error',
          timestamp: moment().unix(),
          path: action.error?.config.url || ''
        };
      }
      break;

    case setTokenManuallyActionType:
      draft.token = new UserToken((action as any).data.token);
      break;

    case setAuthWarningActionType:
      draft.message = (action as any).data.message;
      break;
  }
});