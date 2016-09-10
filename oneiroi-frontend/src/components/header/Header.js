import React, {Component} from 'react';
import FontAwesome from 'react-fontawesome';
import {PageHeader} from 'react-bootstrap';
/**
 * Stateless Header component
 * const indicates stateless components.
 */
class Header extends Component {
    render() {
        return (
            <PageHeader>
                <FontAwesome
                    name="circle-o-notch"
                    size="4x"
                    spin
                />
                <h2>ONEIROI</h2>
            </PageHeader>
        );
    }
}

export default Header;