import React, {Component} from 'react';
import FontAwesome from 'react-fontawesome';
import {PageHeader} from 'react-bootstrap';
import {browserHistory} from 'react-router';
import './header.css';

class Header extends Component {
    render() {
        return (
            <PageHeader onClick={() => browserHistory.push('/todos')}>
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