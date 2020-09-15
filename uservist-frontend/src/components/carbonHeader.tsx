import React from "react";
import {
  Header, HeaderContainer, HeaderGlobalAction, HeaderGlobalBar, HeaderMenu,
  HeaderMenuButton,
  HeaderMenuItem,
  HeaderName,
  HeaderNavigation,
  SkipToContent
} from "carbon-components-react";
import { Login24 } from "@carbon/icons-react";
import {connect} from "react-redux";
import {HOME_PATH, LOGIN_PATH} from "../utils/paths";
import UserDto from "../models/userDto";
import historyStore from "../store/historyStore";
import {clearTokenManually} from "../actions/authActions";

interface CarbonHeaderProps {
  user?: UserDto;
  clearTokenManually: () => void;
}

class CarbonHeader extends React.Component<CarbonHeaderProps, any> {
  clearUser = () => {
    historyStore.push(LOGIN_PATH);
  }

  renderLoggedInMenu() {
    return (
      <>
        <HeaderNavigation aria-label="Uservist [Platform]">
          <HeaderMenuItem isCurrentPage href={HOME_PATH}>
            Home
          </HeaderMenuItem>
          <HeaderMenuItem href="#">Docs</HeaderMenuItem>
          <HeaderMenu aria-label="Services" menuLinkName="Services">
            <HeaderMenuItem href="#">View Services</HeaderMenuItem>
            <HeaderMenuItem href="#">Create Service</HeaderMenuItem>
          </HeaderMenu>
        </HeaderNavigation>
        <HeaderGlobalBar>
          <HeaderGlobalAction aria-label="Log out" onClick={() => this.clearUser()}>
            <Login24 aria-label="Log out" />
          </HeaderGlobalAction>
        </HeaderGlobalBar>
      </>
    )
  }

  renderDefaultMenu() {
    return (
      <>
        <HeaderNavigation aria-label="Uservist [Platform]"/>
        <HeaderGlobalBar>
          <HeaderGlobalAction aria-label="Log in" onClick={() => historyStore.push(LOGIN_PATH)}>
            <Login24 aria-label="Log in" />
          </HeaderGlobalAction>
        </HeaderGlobalBar>
      </>
    )
  }

  render() {
    const {user} = this.props;

    return (
      <HeaderContainer
        render={({ isSideNavExpanded, onClickSideNavExpand }) => (
          <Header aria-label="IBM Platform Name">
            <SkipToContent />
            <HeaderMenuButton
              aria-label="Open menu"
              onClick={onClickSideNavExpand}
              isActive={isSideNavExpanded}
            />
            <HeaderName href={HOME_PATH} prefix="Uservist">
              Platform
            </HeaderName>
            {user ? this.renderLoggedInMenu() : this.renderDefaultMenu()}
          </Header>
        )}
      />
    )
  }
}

const mapStateToProps = (reducers: any) => ({
  user: reducers.authReducer.user
});
const mapDispatchToProps = {
  clearTokenManually
}
const carbonHeader = connect(mapStateToProps, mapDispatchToProps)(CarbonHeader);

export default carbonHeader;