/**
 * Created by bnjm on 9/17/16.
 */
import React, {Component} from 'react';

class UserEntry extends Component {
    render() {
        var lastLogin = new Date(this.props.user.basic.lastLogin);
        return(
            <div>
                <p>Name: {this.props.user.basic.username}</p>
                <p>Password (hashed): {this.props.user.basic.password}</p>
                <p>Last login: {lastLogin.toUTCString()}</p>
            </div>
        )
    }
}
export default UserEntry;