/**
 * Created by bnjm on 9/17/16.
 */
import React, {Component} from 'react';
import {Grid, Row, Col, Panel} from 'react-bootstrap';
import UserList from './UserList';
import {browserHistory} from 'react-router';
import Navigation from '../navigation/Navigation';

class UserManagementPage extends Component {
    constructor() {
        super();
        this.state = {
            users: []
        }
    }

    handleErrors(response) {
        if (!response.ok) {
            throw new Error("error!")
        }
        return response
    }

    queryUsers() {
        fetch("/api/v1/usermanagement/users", {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem("token")
            }
        })
            .then(this.handleErrors)
            .then(response => response.json())
            .then(data => this.setState({users: data}))
            .catch(err => browserHistory.push("/login"));
    }

    componentWillMount() {
        this.queryUsers()
    }


    render() {
        return (
            <div>
                <Navigation/>
            <Grid className="userContainer">
                <Row className="show-grid">
                    <Col xs={12} md={8}>
                        <h4>Currently logged in users</h4>
                        <Panel>
                            <UserList users={this.state.users}/>
                        </Panel>
                    </Col>
                    <Col className="userStatsContainer" xs={6} md={4}>
                        <h4>Statistics</h4>
                        <Panel>

                        </Panel>
                    </Col>
                </Row>
            </Grid>
            </div>
        )
    }
}

export default UserManagementPage;