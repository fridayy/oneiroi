/**
 * Created by bnjm on 9/9/16.
 */
import React, {Component} from 'react';
import TextInputValidation from '../common/TextInputValidation';

class LoginForm extends Component {
    render() {
        return(
            <form>
                <TextInputValidation label="" type="text" name="username" placeholder="Username" minLength={5} onChange={this.props.onChange} value={this.props.user.username}/>
                <TextInputValidation label="" type="password" name="password" placeholder="Password" minLength={5} onChange={this.props.onChange} value={this.props.user.password}/>
                <input type="submit" value="Submit" onClick={this.props.onClick}/>
            </form>
        )
    }
}

export default LoginForm;