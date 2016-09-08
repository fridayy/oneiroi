import React, {Component} from 'react';
import FontAwesome from 'react-fontawesome';
import Navigation from '../navigation/Navigation'
/**
 * Stateless Header component
 * const indicates stateless components.
 */
class Header extends Component {
    render() {
        return (
            <div className="App-header">
                <FontAwesome
                    name="circle-o-notch"
                    size="4x"
                    spin
                />
                <h2>oneiroi react application</h2>
                <Navigation/>
            </div>
        );
    }
}

export default Header;