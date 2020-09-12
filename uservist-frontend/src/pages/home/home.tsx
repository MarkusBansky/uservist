import React from "react";
import { connect } from "react-redux";
import CarbonShell from "../../components/carbonShell";
import "../../styles/login.scss";

interface HomePageProps {}

class HomePage extends React.Component<HomePageProps, any> {
  render() {
    return (
      <CarbonShell className={'home-page'} withHeader={false} withFooter={false}>
      </CarbonShell>
    )
  }
}

const mapDispatchToProps = {};
const mapStateToProps = ({ authReducer }: any) => ({});

const loginPage = connect(mapStateToProps, mapDispatchToProps)(HomePage);
export default loginPage;
