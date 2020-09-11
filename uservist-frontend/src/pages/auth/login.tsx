import React from "react";
import { connect } from "react-redux";
import {authenticate} from "../../actions/authActions";
import UserAuthenticationDto from "../../models/userAuthenticationDto";
import CarbonShell from "../../components/carbonShell";
import {
  deviceDetect,
} from "react-device-detect";
import LoginForm from "./loginForm";
import "../../styles/login.scss";
import {USERVIST_SERVICE_KEY} from "../../utils/constants";
import {AuthReducer} from "../../reducers/authReducer";
import RequestError from "../../models/requestError";
import {ToastNotification} from "carbon-components-react";

const sha256 = require('sha256');

interface LoginPageProps {
  error?: RequestError;

  authenticate: (data: UserAuthenticationDto) => void;
}

class LoginPage extends React.Component<LoginPageProps, any> {
  handleLoginFormSubmit = (data: any) => {
    let dummy = new UserAuthenticationDto({
      username: data.username,
      password: sha256(data.password),
      browser: JSON.stringify(deviceDetect()),
      ipAddress: 'localhost',
      service: USERVIST_SERVICE_KEY
    });
    this.props.authenticate(dummy);
  }

  renderError() {
    const {error} = this.props;
    if (!error) return null;

    return (
      <div>
        <ToastNotification lowContrast hideCloseButton caption={null}
                           className={'full-width'}
                           subtitle={<span>{error.status} - {error.message}</span>}
                           timeout={0}
                           title={'Error'}
        />
      </div>
    )
  }

  render() {

    return (
      <CarbonShell className={'login-page'} withHeader={false} withFooter={false}>
        <LoginForm onSubmit={this.handleLoginFormSubmit} />
        {this.renderError()}
      </CarbonShell>
    )
  }
}

const mapDispatchToProps = { authenticate };
const mapStateToProps = ({ authReducer }: {authReducer: AuthReducer}) => ({
  error: authReducer.error
});

const loginPage = connect(mapStateToProps, mapDispatchToProps)(LoginPage);

export default loginPage;
