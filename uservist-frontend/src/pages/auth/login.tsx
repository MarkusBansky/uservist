import React from "react";
import { connect } from "react-redux";
import {authenticate} from "../../actions/authActions";
import UserAuthenticationDto from "../../models/userAuthenticationDto";
import {
  Header,
  HeaderContainer, HeaderMenu,
  HeaderMenuButton, HeaderMenuItem,
  HeaderName,
  HeaderNavigation, HeaderSideNavItems, SideNav, SideNavItems,
  SkipToContent
} from "carbon-components-react";

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
      <div>
        <HeaderContainer
          render={({ isSideNavExpanded, onClickSideNavExpand }) => (
            <Header aria-label="IBM Platform Name">
              <SkipToContent />
              <HeaderMenuButton
                aria-label="Open menu"
                onClick={onClickSideNavExpand}
                isActive={isSideNavExpanded}
              />
              <HeaderName href="#" prefix="IBM">
                [Platform]
              </HeaderName>
              <HeaderNavigation aria-label="IBM [Platform]">
                <HeaderMenuItem isCurrentPage href="#">
                  Link 1
                </HeaderMenuItem>
                <HeaderMenuItem href="#">Link 2</HeaderMenuItem>
                <HeaderMenuItem href="#">Link 3</HeaderMenuItem>
                <HeaderMenu aria-label="Link 4" menuLinkName="Link 4">
                  <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                </HeaderMenu>
              </HeaderNavigation>
              <SideNav
                aria-label="Side navigation"
                expanded={isSideNavExpanded}
                isPersistent={false}>
                <SideNavItems>
                  <HeaderSideNavItems>
                    <HeaderMenuItem href="#">Link 1</HeaderMenuItem>
                    <HeaderMenuItem href="#">Link 2</HeaderMenuItem>
                    <HeaderMenuItem href="#">Link 3</HeaderMenuItem>
                    <HeaderMenu aria-label="Link 4" menuLinkName="Link 4">
                      <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                    </HeaderMenu>
                  </HeaderSideNavItems>
                </SideNavItems>
              </SideNav>
            </Header>
          )}
        />
      </div>
    )
  }
}

const mapDispatchToProps = { authenticate };

const loginPage = connect(null, mapDispatchToProps)(LoginPage);

export default loginPage;
