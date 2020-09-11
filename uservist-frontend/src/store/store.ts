import { createStore, applyMiddleware } from "redux";
import reducers from "../reducers";
import {axiosStoreMiddleware} from "./axiosStore";
import logger from "redux-logger";

const store = createStore(
  reducers,
  applyMiddleware(
    axiosStoreMiddleware,
    logger
  )
);

export default store;