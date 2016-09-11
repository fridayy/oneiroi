import React from 'react';
import {Route, IndexRoute} from 'react-router';
import App from './App';
import HomePage from './components/home/HomePage'
import LoginPage from './components/login/LoginPage';
import TodoPage from './components/todo/TodoPage';

/**
 * Sets all the possible routes between the react components
 */
export default (
    <Route path="/" component={App}>
        <IndexRoute component={HomePage}/>
        <Route path="login" component={LoginPage}/>
        <Route path="todos" component={TodoPage} />
    </Route>
);
