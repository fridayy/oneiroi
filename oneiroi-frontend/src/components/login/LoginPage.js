/**
 * Created by bnjm on 9/8/16.
 */
import React, {Component} from 'react';
import LoginForm from './LoginForm';
import SHA256 from 'crypto-js/sha256';
import {Grid, Row, Col, Panel} from 'react-bootstrap';
import {browserHistory} from 'react-router'
import CloseableAlert from '../common/CloseableAlert';

class LoginPage extends Component {
    constructor() {
        super();
        this.state = {
            user: {
                basic: {username: '', password: ''}
            },
            alert: false,
            loggedIn: true
        };
        this.setUserState = this.setUserState.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        console.log("Login component constructor called.")
    }

    setUserState(e) {
        var field = e.target.name;
        var val = e.target.value;
        var items = this.state.user.basic;
        if (field === "password") {
            items[field] = SHA256(val).toString();
        } else {
            items[field] = val;
        }
        this.setState({user: items});

        return this.setState({user: this.state.user})
    }

    handleSubmit(e) {
        e.preventDefault();
        this.sendLoginRequest()
    }

    sendLoginRequest() {
        fetch("/api/v1/user/login", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.state.user)
        }).then(this.handleErrors)
            .then(response => response.json())
            .then(data => {
                this.addAuthToken(data);
            })
            .catch(err => this.setState({alert: true}));
    }

    handleErrors(response) {
        if (!response.ok) {
            throw new Error("error!")
        }
        return response
    }

    addAuthToken(data) {
        localStorage.setItem("token", data.header + "." + data.payload + "." + data.signature);
        this.setState({loggedIn: true});
        browserHistory.push('/todos');
    }

    render() {
        return (
            <Grid>
                <Row className="show-grid">
                    <Col xs={6} md={4}></Col>
                    <Col xs={6} md={4}>
                        <Panel>
                        <CloseableAlert visible={this.state.alert}
                                        alertType="danger"
                                        title="Ooops"
                                        text="Wrong credentials..."/>
                        <LoginForm
                            user={this.state.user}
                            onChange={this.setUserState}
                            onClick={this.handleSubmit}/>
                        </Panel>
                    </Col>
                    <Col xsHidden md={4}></Col>
                </Row>
            </Grid>);
    }
}

export default LoginPage;