import React from 'react';
import {Route, IndexRoute} from 'react-router';
import App from './App';
import Login from './components/login/LoginPage';

/**
 * Sets all the possible routes between the react components
 */
export default (
  <Route path="/" component={App}>
      <IndexRoute component={Login} />
  </Route>
);
