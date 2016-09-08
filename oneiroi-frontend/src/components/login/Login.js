/**
 * Created by bnjm on 9/8/16.
 */
import React, {Component} from 'react';
import {FormGroup, FormControl, ControlLabel} from 'react-bootstrap';

class Login extends Component {
    render() {
        return (
            <form>
                <FormGroup>
                    <ControlLabel>Login</ControlLabel>
                    <FormControl
                        type="text"
                        value="hi"
                        placeholder="enter text..."
                    />
                </FormGroup>
            </form>
        );
    }
}

export default Login;