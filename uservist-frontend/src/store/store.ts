import { createStore, applyMiddleware } from "redux";
import reducers from "../reducers";
import {axiosStoreMiddleware} from "./axiosStore";

const store = createStore(
  reducers,
  applyMiddleware(
    axiosStoreMiddleware
  )
);

export default store;