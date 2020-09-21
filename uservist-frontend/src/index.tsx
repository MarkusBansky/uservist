import React from 'react';
import ReactDOM from 'react-dom';
import * as serviceWorker from './utils/serviceWorker';
import { Provider as ReduxProvider } from "react-redux";
import { Router, Route } from "react-router-dom";
import "./styles/index.scss";
import store from "./store/store";
import {requiresAuthentication} from "./components/authenticatedComponent";
import {HOME_PATH, LOGIN_PATH} from "./utils/paths";
import LoginPage from "./pages/auth/login";
import HomePage from "./pages/home/home";
import history from "./store/historyStore";

ReactDOM.render(
  <ReduxProvider store={store}>
    <Router history={history}>
      <Route path={HOME_PATH} exact component={requiresAuthentication(HomePage)} />
      <Route path={LOGIN_PATH} exact component={LoginPage} />
    </Router>
  </ReduxProvider>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
