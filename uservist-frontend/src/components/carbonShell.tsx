import React from "react";
import CarbonHeader from "./carbonHeader";
import CarbonFooter from "./carbonFooter";
import CarbonPageHeader, {CarbonPageHeaderControl} from "./carbonPageHeader";
import "../styles/page.scss";

export interface CarbonShellProperties {
  className: string;
  withHeader: boolean;
  withFooter: boolean;
  headerText?: string;
  headerControls?: CarbonPageHeaderControl[];
}

export default class CarbonShell extends React.Component<CarbonShellProperties, any> {

  renderHeader() {
    const {withHeader, headerText, headerControls} = this.props;
    if (!withHeader || !headerText) {
      return null;
    }

    return <CarbonPageHeader text={headerText} controls={headerControls}/>;
  }

  renderFooter() {
    const {withFooter} = this.props;
    if (!withFooter) {
      return null;
    }

    return <CarbonFooter />;
  }

  render() {
    return (
      <div className={'page container'}>
        <CarbonHeader />
        <div className="bx--grid">
          {this.renderHeader()}
          <div className={this.props.className}>
            {this.props.children}
          </div>
          {this.renderFooter()}
        </div>
      </div>
    )
  }
}