import axios from "axios";
import {get} from "lodash";
import axiosMiddleware from "redux-axios-middleware";
import {USERVIST_SERVICE_API_URL} from "../utils/constants";
import UserToken from "../models/userToken";

axios.defaults.headers.common['Authorization'] = null;

const client = axios.create({ //all axios can be used, shown in axios documentation
  baseURL: USERVIST_SERVICE_API_URL,
  responseType: 'json',
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
    Authorization: undefined
  }
});

function interceptorRequest({getState, dispatch, getSourceAction}: any, req: any) {
  const needAuth = get(
    getSourceAction(req),
    'payload.request.useAuth',
  );

  if (needAuth) {
    const token: UserToken = get(getState(), 'authReducer.token');

    if (token) {
      console.log('Setting token to: ', `Bearer ${token.getToken()}`);
      req.headers.Authorization = `Bearer ${token.getToken()}`;
    }
  }

  console.log('req', req);
  return req;
}

const middlewareConfig = {
  interceptors: {
    request: [interceptorRequest],
    // response: [{
    //   success: ({getState, dispatch, getSourceAction}: any, res: any) => {
    //     console.log(res); //contains information about request object
    //     //...
    //     return Promise.resolve(res);
    //   }
    // }]
  }
};

export const axiosStoreMiddleware = axiosMiddleware(client, middlewareConfig)