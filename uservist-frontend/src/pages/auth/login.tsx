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
import UserToken from "../../models/userToken";
import historyStore from "../../store/historyStore";
import {HOME_PATH} from "../../utils/paths";

const sha256 = require('sha256');

interface LoginPageProps {
  error?: RequestError;
  warning?: string;
  token?: UserToken;

  authenticate: (data: UserAuthenticationDto) => void;
}

class LoginPage extends React.Component<LoginPageProps, any> {
  componentDidUpdate(prevProps: Readonly<LoginPageProps>, prevState: Readonly<any>, snapshot?: any) {
    if ((!prevProps.token && this.props.token) || (prevProps.token?.getToken() !== this.props.token?.getToken())) {
      let to = (this.props as any).match.params.redirectTo || HOME_PATH;
      historyStore.push(to);
    }
  }

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
                           kind={'error'} className={'full-width'}
                           subtitle={<span>{error.message}</span>}
                           timeout={0}
                           title={error.error}
        />
      </div>
    )
  }

  renderWarning() {
    const {warning} = this.props;
    if (!warning) return null;

    return (
      <div>
        <ToastNotification lowContrast hideCloseButton caption={null}
                           kind={'warning'} className={'full-width'}
                           subtitle={<span>{warning}</span>}
                           timeout={0}
                           title={'Warning'}
        />
      </div>
    )
  }

  render() {

    return (
      <CarbonShell className={'login-page'} withHeader={false} withFooter={false}>
        <LoginForm onSubmit={this.handleLoginFormSubmit} />
        {this.renderError()}
        {this.renderWarning()}
      </CarbonShell>
    )
  }
}

const mapDispatchToProps = { authenticate };
const mapStateToProps = ({ authReducer }: {authReducer: AuthReducer}) => ({
  error: authReducer.error,
  warning: authReducer.message,
  token: authReducer.token
});

const loginPage = connect(mapStateToProps, mapDispatchToProps)(LoginPage);

export default loginPage;
