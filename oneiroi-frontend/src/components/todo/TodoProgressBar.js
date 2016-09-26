/**
 * Created by bnjm on 9/11/16.
 */
import React, {Component} from 'react';
import {ProgressBar} from 'react-bootstrap';
class TodoProgressBar extends Component {
    render() {
        return(
            <ProgressBar
                active
                now={(Number.parseInt(this.props.todoStats.percentageDone, 10))}
                label={`${this.props.todoStats.percentageDone}% done`}
            />
        )
    }
}

export default TodoProgressBar;