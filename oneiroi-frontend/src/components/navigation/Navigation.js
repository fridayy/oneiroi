import React, {Component} from 'react';
import './navigation.css';
import {Link} from 'react-router';

class Navigation extends Component {
    render() {
            return(
                <div className="navigation">
                    <Link to="logout">Logout</Link>
                    {" | "}
                    <Link to="todos">Todos</Link>
                    {" | "}
                    <Link to="usermanagement">User Management</Link>
                </div>
            )
    }
}

export default Navigation;