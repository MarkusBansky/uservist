import axios from "axios";
import axiosMiddleware from "redux-axios-middleware";
import {USERVIST_SERVICE_API_URL} from "../utils/constants";

const client = axios.create({ //all axios can be used, shown in axios documentation
  baseURL: USERVIST_SERVICE_API_URL,
  responseType: 'json'
});

const middlewareConfig = {
  interceptors: {
    response: [{
      success: ({getState, dispatch, getSourceAction}: any, res: any) => {
        console.log(res); //contains information about request object
        //...
        return Promise.resolve(res);
      }
    }
    ]
  }
};

export const axiosStoreMiddleware = axiosMiddleware(client, middlewareConfig)