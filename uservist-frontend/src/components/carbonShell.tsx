import React from "react";
import CarbonHeader from "./carbonHeader";
import CarbonFooter from "./carbonFooter";
import {CarbonPageHeaderControl} from "./carbonPageHeader";

export interface CarbonShellProperties {
  className: string;
  withHeader: boolean;
  headerText?: string;
  headerControls?: CarbonPageHeaderControl[];
}

export default class CarbonShell extends React.Component<CarbonShellProperties, any> {

  renderHeader() {
    const {withHeader, headerText, headerControls} = this.props;
    if (!withHeader || !headerText) {
      return null;
    }

    return null;
    // return <CarbonHeader text={headerText} controls={headerControls}/>
  }

  render() {
    return (
      <div className={'container ' + this.props.className}>
        <CarbonHeader />
        <div className="bx--grid">
          {this.renderHeader()}
          {this.props.children}
        </div>
        <CarbonFooter />
      </div>
    )
  }
}