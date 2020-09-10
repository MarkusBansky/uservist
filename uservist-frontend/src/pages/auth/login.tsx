import React from "react";
import { connect } from "react-redux";
import {authenticate} from "../../actions/authActions";
import UserAuthenticationDto from "../../models/userAuthenticationDto";
import CarbonShell from "../../components/carbonShell";

interface LoginPageProps {
  authenticate: (data: UserAuthenticationDto) => void;
}

class LoginPage extends React.Component<LoginPageProps, any> {
  componentDidMount() {
    let dummy = new UserAuthenticationDto({});
    this.props.authenticate(dummy);
  }

  render() {
    return (
      <CarbonShell className={'login-page'} withHeader={false}>

      </CarbonShell>
    )
  }
}

const mapDispatchToProps = { authenticate };

const loginPage = connect(null, mapDispatchToProps)(LoginPage);

export default loginPage;
