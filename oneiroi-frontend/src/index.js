import React from 'react';
import ReactDOM from 'react-dom';
import {Router, browserHistory} from 'react-router';
import routes from './routes';
import './index.css';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';

/**
 * JavaScript main entry point
 */
ReactDOM.render(
    <Router history={browserHistory} routes={routes}/>,
    document.getElementById('root')
);
