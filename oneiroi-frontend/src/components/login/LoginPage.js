/**
 * Created by bnjm on 9/8/16.
 */
import React, {Component} from 'react';
import LoginForm from './LoginForm';
import SHA256 from 'crypto-js/sha256';

class LoginPage extends Component {
    constructor() {
        super();
        this.state = {
            user: {username: '', password: ''}
        };
        this.setUserState = this.setUserState.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    setUserState(e) {
        var field = e.target.name;
        var val = e.target.value;
        var items = this.state.user;
        items[field] = val;
        this.setState({user: items});

        return this.setState({user: this.state.user})
    }

    handleSubmit(e) {
        e.preventDefault();
        alert(JSON.stringify(
            "username: " + this.state.user.username + " password: " + SHA256(this.state.user.password)));
    }

    render() {
        return (
            <LoginForm
                user={this.state.user}
                onChange={this.setUserState}
                onClick={this.handleSubmit}
            />
        );
    }
}

export default LoginPage;