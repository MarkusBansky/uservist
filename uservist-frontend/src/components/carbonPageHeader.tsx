import React, {ReactNode} from "react";
import _ from "lodash";
import {Column, Row} from "carbon-components-react";

export interface CarbonPageHeaderControl {
  width: 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12;
  item: ReactNode;
}

export interface CarbonPageHeaderProperties {
  text: string;
  controls?: CarbonPageHeaderControl[];
}

/**
 * **HEADER** element for the *Page*.
 * Has a **text** parameter that is required.
 * Can have additional controls of type *PageHeaderControl*.
 */
export default class CarbonPageHeader extends React.Component<CarbonPageHeaderProperties, {}> {
  renderControls() {
    const {controls} = this.props;

    if (controls === undefined) return '';

    return (
      <Row>
        {
          _.map(controls, (c, i) => {
            return <Column key={i} lg={c.width}>
              {c.item}
            </Column>
          })
        }
      </Row>
    )
  }

  render() {
    return <>
      <Row className='page-header'>
        <Column>
          <span className='expressive-heading-06'>{this.props.text}</span>
        </Column>
      </Row>
      {this.renderControls()}
    </>
  }
}