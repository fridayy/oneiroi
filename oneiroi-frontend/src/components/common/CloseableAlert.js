/**
 * Created by bnjm on 9/12/16.
 */
import React, {Component} from 'react';
import {Alert, Button} from 'react-bootstrap';

class CloseableAlert extends Component {
    constructor(props) {
        super(props);
        this.handleAlertDismiss = this.handleAlertDismiss.bind(this);
        this.handleAlertShow = this.handleAlertShow.bind(this);
        this.state = {alertVisible: true}
    }

    handleAlertDismiss() {
        this.setState({alertVisible: false});
    }

    handleAlertShow() {
        this.setState({alertVisible: true});
    }

    render() {
        if (this.props.visible && this.state.alertVisible) {
            return (
                <Alert bsStyle={this.props.alertType} onDismiss={this.handleAlertDismiss}>
                    <h4>{this.props.title}</h4>
                    <p>{this.props.text}</p>
                    <p>
                        <Button onClick={this.handleAlertDismiss}>Hide Alert</Button>
                    </p>
                </Alert>
            )
        } else {
            return null;
        }
    }
}

export default CloseableAlert;