import React from "react";
import {
  Header, HeaderContainer, HeaderMenu,
  HeaderMenuButton,
  HeaderMenuItem,
  HeaderName,
  HeaderNavigation,
  SkipToContent
} from "carbon-components-react";
import {connect} from "react-redux";

class CarbonHeader extends React.Component<any, any> {
  render() {
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
            <HeaderName href="#" prefix="Uservist">
              Platform
            </HeaderName>
            <HeaderNavigation aria-label="Uservist [Platform]">
              <HeaderMenuItem isCurrentPage href="#">
                Home
              </HeaderMenuItem>
              <HeaderMenuItem href="#">Docs</HeaderMenuItem>
              <HeaderMenu aria-label="Services" menuLinkName="Services">
                <HeaderMenuItem href="#">View Services</HeaderMenuItem>
                <HeaderMenuItem href="#">Create Service</HeaderMenuItem>
              </HeaderMenu>
            </HeaderNavigation>
          </Header>
        )}
      />
    )
  }
}

const mapDispatchToProps = {};

const carbonHeader = connect(null, mapDispatchToProps)(CarbonHeader);

export default carbonHeader;