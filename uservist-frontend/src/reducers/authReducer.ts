import produce from "immer";
import {createReducerActionTypes} from "../utils/actionsUtils";
import {ReducerAction} from "../actions/action";
import UserAuthenticationResponseDto from "../models/userAuthenticationResponseDto";
import RequestError from "../models/requestError";
import {AxiosResponse} from "axios";
import moment from "moment";

// reducer action type constructed
type AuthReducerActionType = UserAuthenticationResponseDto | undefined;

// action types
export const authenticateActionTypes = createReducerActionTypes('authenticateActionTypes');

// reducer interface
export interface AuthReducer {
  token?: string;
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
      break;
    case authenticateActionTypes.SUCCESS:
      console.log("Received response from login: ", action.payload.data);
      draft.token = action.payload.data?.token;
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
  }
});