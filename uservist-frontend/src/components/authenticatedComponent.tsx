import React from "react";
import {withRouter} from "react-router";
import {connect} from "react-redux";
import Cookies from "js-cookie";
import UserToken from "../models/userToken";
import {LOGIN_PATH} from "../utils/paths";
import {getCurrentUserInformation, setAuthWarning, setTokenManually} from "../actions/authActions";

/**
 * AuthenticatedComponent class is connected to reducers and
 * gathers these arguments from them.
 * These are required to obtain user authenticated token and
 * change reducer state.
 */
interface AuthenticatedComponentProps {
  /**
   * User auth token obtained from auth service and stored in reducer.
   * Also this token is being saved into cookies and can be recovered.
   */
  token: UserToken;

  /**
   * A reducer method used to set token from a string.
   * Usually this method is used to restore token from cookies.
   * @param token String restored from cookies.
   */
  setTokenManually: (token: string) => any,

  /**
   * Reducer method used to set a warning message to be displayed to user on the front end.
   * @param message A string to be displayed on the login page as a warning to user.
   */
  setAuthWarning: (message: string) => any

  /**
   * Reducer action which requests current user information.
   */
  getCurrentUserInformation: () => any;
}

/**
 * This function is used to wrap a page into a restricted view.
 * Only those users that are registered can access the page wrapped by this component.
 * In future it would be beneficial to add a tole parameter to determine which authenticated user can access selected page.
 * @param Component A Page component to be wrapped for logged in users only.
 */
export function requiresAuthentication(Component: any) {

  /**
   * A local authenticated component is wrapped defined inside this function and returned upon call.
   * User's page is rendered inside the component if user is authenticated and has privileges.
   */
  class AuthenticatedComponent extends React.Component<AuthenticatedComponentProps, {}> {
    /**
     * When the main component gets mounted (aka Page is loaded)
     * this method is called and it checks whether user has a valid token and tries to recover it from cookies.
     * If there is no valid token, then user gets a warning message and gets redirected to the login page.
     * The main component is not rendered.
     */
    componentDidMount() {
      const { token } = this.props;

      // Run this check only if the token from reducer is wrong
      if (!token || !token.getToken() || token.isExpired()) {
        // Try to recover the token from the cookies
        if (Cookies.get('token')) {
          this.props.setTokenManually(Cookies.get('token') as string);
        }
        // if token is not found, display warning message and redirect to login page.
        else {
          console.log('A valid user token could not be found, redirecting user to login page...');

          if (token && token.isExpired()) {
            this.props.setAuthWarning('Your login session has expired.');
          } else {
            this.props.setAuthWarning('You need to be logged in to access that page!');
          }

          const anyProps = this.props as any;
          anyProps.history.push(encodeURI(`${LOGIN_PATH}?redirectTo=${anyProps.location.pathname}`));
        }
      }
    }

    /**
     * Get user token from properties.
     * If user's token is set and valid then user can be trusted as authenticated.
     */
    render() {
      const { token, getCurrentUserInformation } = this.props;

      if (token && token.getToken() && !token.isExpired()) {
        getCurrentUserInformation();

        return (<Component {...this.props} />)
      }

      return null;
    }
  }

  /**
   * Map account reducer token parameter to local state token.
   * @param reducers Combined Reducers.
   */
  const mapState = (reducers: any) => ({
    token: reducers.authReducer.token
  });

  /**
   * Map two main methods to set token from string and to set user warning message.
   */
  const mapDispatch = ({
    setTokenManually, setAuthWarning, getCurrentUserInformation
  });

  /**
   * This call returns a wrapped component and connected with the router and props/dispatch functions.
   */
  return withRouter((connect(mapState, mapDispatch) as any)(AuthenticatedComponent));
}