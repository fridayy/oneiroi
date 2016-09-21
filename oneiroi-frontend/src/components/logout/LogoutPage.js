/**
 * Created by bnjm on 9/17/16.
 */
import React, {Component} from 'react';

class LogoutPage extends Component {

    logout() {
        fetch("/api/v1/user/logout", {
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem("token")
            }
        })
            .then(response => localStorage.removeItem("token"))
            .catch(err => console.log(err.toString()))
    }

    render() {
        this.logout();
        return(
            <h4>Successfully logged out.</h4>
        )
    }
}
export default LogoutPage;