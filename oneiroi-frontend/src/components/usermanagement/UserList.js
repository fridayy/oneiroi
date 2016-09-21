/**
 * Created by bnjm on 9/17/16.
 */
import React, {Component} from 'react';
import UserEntry from './UserEntry';

class UserList extends Component {
    render() {
        var createUserEntry = function (user) {
            return (
                <UserEntry
                    user={user}
                />
            )
        };

        return(
            <div>
                {this.props.users.map(createUserEntry, this)}
            </div>
        )
    }
}

export default UserList;