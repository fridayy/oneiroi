import React from 'react';
import {Route, IndexRoute} from 'react-router';
import App from './App';
import LoginPage from './components/login/LoginPage';
import TodoPage from './components/todo/TodoPage';
import LogoutPage from './components/logout/LogoutPage';
import UserManagementPage from './components/usermanagement/UserManagementPage';


/**
 * Sets all the possible routes between the react components
 */

export default (
    <Route path="/" component={App}>
        <IndexRoute component={TodoPage}/>
        <Route path="login" component={LoginPage}/>
        <Route path="todos" component={TodoPage}/>
        <Route path="logout" component={LogoutPage}/>
        <Route path="usermanagement" component={UserManagementPage}/>
    </Route>
);
