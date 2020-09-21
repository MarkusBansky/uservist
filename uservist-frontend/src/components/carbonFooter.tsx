import React from "react";
import {Column, Row} from "carbon-components-react";
import "../styles/footer.scss";

export interface CarbonFooterProperties {
  className?: string;
}

export default class CarbonFooter extends React.Component<CarbonFooterProperties, {}> {
  render() {
    return (
      <div className={'footer ' + this.props.className}>
        <div className="bx--grid">
          <Row>
            <Column>
              <p>
                <span className={'logo-text'}>
                  Developed by Markiian Benovskyi © 2019 - {(new Date().getFullYear())}
                </span>
              </p>
            </Column>
          </Row>
        </div>
      </div>
    )
  }
}