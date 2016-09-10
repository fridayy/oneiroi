/**
 * Created by bnjm on 9/9/16.
 */
import React, {Component} from 'react';
import TextInputValidation from '../common/TextInputValidation';

class LoginForm extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return(
            <form>
                <TextInputValidation label="" name="username" placeholder="Username" minLength={5} onChange={this.props.onChange} value={this.props.user.username}/>
                <TextInputValidation label="" name="password" placeholder="Password" minLength={5} onChange={this.props.onChange} value={this.props.user.password}/>
                <input type="submit" value="Submit" onClick={this.props.onClick}/>
            </form>
        )
    }
}

export default LoginForm;