import React, {Component, PropTypes} from 'react';
import './App.css';
import {Row, Col, Grid} from 'react-bootstrap';
import Header from './components/header/Header';
import Navigation from './components/navigation/Navigation';
import TestButton from './components/testButton/TestButton';
/**
 * This class is rendered everytime and serves as the application layout
 * Created by bnjm on 9/8/16.
 */
class App extends Component {
    render() {
        return (
            <div className="App">
                <Header/>
                <Navigation/>
                <Grid>
                    <Row className="show-grid">
                        <Col xs={6} md={4}></Col>
                        <Col xs={6} md={4}>
                            {this.props.children}</Col>
                        <Col xsHidden md={4}></Col>
                    </Row>
                </Grid>
                <TestButton/> //TODO: remove this
            </div>
        );
    }
}

App.propTypes = {
    children: PropTypes.object.isRequired
};

export default App;
