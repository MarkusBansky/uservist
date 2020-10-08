import React from "react";
import { connect } from "react-redux";
import CarbonShell from "../../components/carbonShell";
import "../../styles/login.scss";
import UserDto from "../../models/userDto";
import {getUserById} from "../../actions/userActions";
import {Form, FormGroup, Link, TextInput} from "carbon-components-react";

interface HomePageProps {
  /**
   * User object received via token, can be validated for 401 in a case
   * there was an error in the token.
   */
  user?: UserDto;

  getUserById: (id: number) => void;
}

class HomePage extends React.Component<HomePageProps, any> {
  componentDidMount() {
    const {user} = this.props;

    if (!user) return;

    this.props.getUserById(user.id);
  }

  renderForm() {
    const {user} = this.props;
    if (!user) return null;

    return (
      <Form>
        <hr/>

        <p className="body-short-01">
          You are logged into <strong>Uservist</strong> service.
        </p>

        <Link href={'#'}>Edit personal information</Link>

        <p className="body-long-01">
          Here you can modify your user's personal information along with security information and
          the services that your user has access to.
        </p>

        <FormGroup legendText="Personal Information">
          <div style={{marginBottom: '2rem'}}>
            <TextInput
              helperText="Enter user's forename (~20 character count maximum)"
              id="firstName"
              invalidText="Invalid first name."
              labelText="First name"
              defaultValue={user.firstName}
            />
          </div>
          <div style={{marginBottom: '2rem'}}>
            <TextInput
              helperText="Enter user's surname (~20 character count maximum)"
              id="lastName"
              invalidText="Invalid last name."
              labelText="Last name"
              defaultValue={user.lastName}
            />
          </div>
        </FormGroup>
        <FormGroup legendText="Credentials">
          <div style={{marginBottom: '2rem'}}>
            <TextInput
              helperText="This username is used to access services"
              id="username"
              labelText="Username"
              defaultValue={user.username}
              readOnly
            />
          </div>
          <div style={{marginBottom: '2rem'}}>
            <TextInput
              helperText="Email is used for authorization only"
              id="email"
              labelText="Email"
              defaultValue={user.email}
              readOnly
            />
          </div>
        </FormGroup>
      </Form>
    );
  }

  render() {
    return (
      <CarbonShell className={'home-page'} withHeader={false} withFooter={false}>
        <div className="bx--grid">
          <div className="bx--row">
            <div className="bx--col">
              <h1>Your user</h1>
            </div>
          </div>
          <div className="bx--row">
            <div className="bx--col-sm bx--col-md-6 bx--col-lg-4">
              {this.renderForm()}
            </div>
          </div>
        </div>
      </CarbonShell>
    )
  }
}

const mapDispatchToProps = {
  getUserById
};
const mapStateToProps = ({ authReducer }: any) => ({
  user: authReducer.user
});

const loginPage = connect(mapStateToProps, mapDispatchToProps)(HomePage);
export default loginPage;
