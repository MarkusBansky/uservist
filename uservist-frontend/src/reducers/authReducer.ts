import produce from "immer";
import {createReducerActionTypes} from "../utils/actionsUtils";
import {ReducerAction} from "../actions/action";
import UserAuthenticationResponseDto from "../models/userAuthenticationResponseDto";

// reducer action type constructed
type AuthReducerActionType = UserAuthenticationResponseDto | undefined;

// action types
export const authenticateActionTypes = createReducerActionTypes('authenticateActionTypes');

// reducer interface
export interface AuthReducer {
  token?: string;
  loading: boolean;
}

const defaultState: AuthReducer = {
  loading: true
};

// reducer itself
export const authReducer = (state = defaultState, action: ReducerAction<AuthReducerActionType>) =>
  produce((draft: AuthReducer, action: ReducerAction<AuthReducerActionType>) => {
  switch(action.type) {
    case authenticateActionTypes.REQUEST:
      draft.loading = true;
      break;
    case authenticateActionTypes.SUCCESS:
      console.log("Received response from login: ", action.payload?.data);
      draft.token = action.payload?.data?.token;
      draft.loading = false;
      break;
    case authenticateActionTypes.FAILURE:
      console.error("ERROR trying to authenticate: ", action.error);
      draft.loading = false;
      draft.token = undefined;
      break;
  }
});