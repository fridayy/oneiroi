/**
 * Created by bnjm on 9/11/16.
 */
import React, {Component} from 'react';
import {Button, Label} from 'react-bootstrap';
import FontAwesome from 'react-fontawesome';

class TodoItem extends Component {

    render() {
        var bsStatus = "danger";
        var labelText = "Not Done"
        if (this.props.todo.isDone) {
            bsStatus = "success";
            labelText = "Done";
        }

        return(
            <tr key={this.props.todo.id} onClick={() => this.props.onUpdate(this.props.todo)}>
                <td>{this.props.todo.title}</td>
                <td>{this.props.todo.description}</td>
                <td>{this.props.todo.created}</td>
                <td><Label bsStyle={bsStatus}>{labelText}</Label></td>
                    <td>
                        <Button bsSize="xsmall" onClick={() => this.props.onDelete(this.props.todo.id)}>
                            <FontAwesome
                                name="times"
                            />
                        </Button>
                    </td>
            </tr>
        )
    }
}
export default TodoItem;