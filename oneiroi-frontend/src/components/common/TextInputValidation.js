/**
 * Created by bnjm on 9/9/16.
 */
import React, {Component, PropTypes} from 'react';
import {FormGroup, FormControl, ControlLabel} from 'react-bootstrap';

/**
 * This is a text input used in forms with validation
 */
class TextInputValidation extends Component {

    render() {
        return(
                <FormGroup>
                    <ControlLabel>{this.props.label}</ControlLabel>
                    <FormControl
                        type="text"
                        name={this.props.name}
                        value={this.props.value}
                        placeholder={this.props.placeholder}
                        onChange={this.props.onChange}
                    />
                </FormGroup>
        )
    }
}

TextInputValidation.propTypes = {
    placeholder: PropTypes.string,
    label: PropTypes.string,
    minLength: PropTypes.number.isRequired
}

export default TextInputValidation;