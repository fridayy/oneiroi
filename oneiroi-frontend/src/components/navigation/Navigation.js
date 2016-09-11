import React from 'react';
import './navigation.css';
import {Link} from 'react-router';

const Navigation = () => {
    return (
        <div>
            <Link to="/">Home</Link>
            {" | "}
            <Link to="login">Login</Link>
            {" | "}
            <Link to="todos">Todos</Link>
        </div>
    )
}

export default Navigation;