import produce from "immer";
import {createReducerActionType, createReducerActionTypes} from "../utils/actionsUtils";
import {ReducerAction} from "../actions/action";
import UserAuthenticationResponseDto from "../models/userAuthenticationResponseDto";
import ReducerMessage from "../models/reducerMessage";
import {AxiosResponse} from "axios";
import moment from "moment";
import UserToken from "../models/userToken";
import Cookies from "js-cookie";
import UserDto from "../models/userDto";
import userDto from "../models/userDto";

// reducer action type constructed
type AuthReducerActionType = UserAuthenticationResponseDto | undefined;

// action types
export const authenticateActionTypes = createReducerActionTypes('authenticateActionTypes');
export const getCurrentUserActionTypes = createReducerActionTypes('getCurrentUserActionTypes');
export const setTokenManuallyActionType = createReducerActionType('setTokenManuallyActionType');
export const clearTokenManuallyActionType = createReducerActionType('clearTokenManuallyActionType');
export const setAuthWarningActionType = createReducerActionType('setAuthWarningActionType');


// reducer interface
export interface AuthReducer {
  token?: UserToken;
  user?: UserDto;

  // general properties
  message?: ReducerMessage;
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
      draft.message = {
        type: "error",
        ...action.error?.response?.data as any
      };

      if (!draft.message) {
        draft.message = {
          type: "error",
          status: action.error?.response?.status || 1,
          message: action.error?.message || 'Unknown error',
          timestamp: moment().unix(),
        };
      }
      break;

    case setTokenManuallyActionType:
      draft.token = new UserToken((action as any).data.token);
      break;

    case clearTokenManuallyActionType:
      draft.token = undefined;
      break;

    case setAuthWarningActionType:
      draft.message = {
        type: "warning",
        status: 0,
        message: (action as any).data.message,
        timestamp: moment().unix()
      }
      break;

    case getCurrentUserActionTypes.REQUEST:
      draft.loading = true;
      draft.message = undefined;
      break;
    case getCurrentUserActionTypes.SUCCESS:
      draft.loading = false;
      draft.user = new userDto(action.payload.data);
      break;
    case getCurrentUserActionTypes.FAILURE:
      draft.loading = false;
      draft.user = undefined;
      draft.message = {
        type: "error",
        ...action.error?.response?.data as any
      };

      if (!draft.message) {
        draft.message = {
          type: "error",
          status: action.error?.response?.status || 1,
          message: action.error?.message || 'Unknown error',
          timestamp: moment().unix(),
        };
      }
      break;
  }
});