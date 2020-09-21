import React from "react";
import { connect } from "react-redux";
import {authenticate, clearTokenManually} from "../../actions/authActions";
import UserAuthenticationDto from "../../models/userAuthenticationDto";
import CarbonShell from "../../components/carbonShell";
import {
  deviceDetect,
} from "react-device-detect";
import LoginForm from "./loginForm";
import "../../styles/login.scss";
import {USERVIST_SERVICE_KEY} from "../../utils/constants";
import {AuthReducer} from "../../reducers/authReducer";
import ReducerMessage from "../../models/reducerMessage";
import {ToastNotification} from "carbon-components-react";
import UserToken from "../../models/userToken";
import historyStore from "../../store/historyStore";
import {HOME_PATH} from "../../utils/paths";
import {toFirstUpperLetter} from "../../utils/textUtils";
import Cookies from "js-cookie";

const sha256 = require('sha256');

interface LoginPageProps {
  message?: ReducerMessage;
  token?: UserToken;

  authenticate: (data: UserAuthenticationDto) => void;
  clearTokenManually: () => void;
}

class LoginPage extends React.Component<LoginPageProps, any> {
  componentDidMount() {
    Cookies.remove("token");
    this.props.clearTokenManually();
  }

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

  renderUserMessage() {
    const {message} = this.props;
    if (!message) return null;

    return (
      <div>
        <ToastNotification lowContrast hideCloseButton caption={null}
                           kind={message.type} className={'full-width'}
                           subtitle={<span>{message.message}</span>}
                           timeout={0}
                           title={toFirstUpperLetter(message.type)}
        />
      </div>
    )
  }

  render() {

    return (
      <CarbonShell className={'login-page'} withHeader={false} withFooter={false}>
        <LoginForm onSubmit={this.handleLoginFormSubmit} />
        {this.renderUserMessage()}
      </CarbonShell>
    )
  }
}

const mapDispatchToProps = { authenticate, clearTokenManually };
const mapStateToProps = ({ authReducer }: {authReducer: AuthReducer}) => ({
  message: authReducer.message,
  token: authReducer.token
});

const loginPage = connect(mapStateToProps, mapDispatchToProps)(LoginPage);

export default loginPage;
