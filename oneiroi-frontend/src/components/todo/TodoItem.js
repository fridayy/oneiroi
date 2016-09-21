/**
 * Created by bnjm on 9/11/16.
 */
import React, {Component} from 'react';
import {Button, OverlayTrigger, Tooltip, Panel} from 'react-bootstrap';
import FontAwesome from 'react-fontawesome';
import './todo.css';

class TodoItem extends Component {

    render() {
        var bsStatus = "danger";
        if (this.props.todo.isDone) {
            bsStatus = "success";
        }

        var created = new Date(this.props.todo.created);
        const tooltip = <Tooltip id="tooltip"><strong>Click</strong> to change status.</Tooltip>;
        const footer = <div>
                        <small className="date" onClick={() => this.props.onUpdate(this.props.todo)}>Created: {created.toUTCString()}</small>
                        </div>;


        const header = <div>{this.props.todo.title}
            <Button className="close" onClick={() => this.props.onDelete(this.props.todo.id)}>
                <FontAwesome
                    name="times"
                />
            </Button>
        </div>;

        return (
            <Panel header={header} key={this.props.todo.id} bsStyle={bsStatus} footer={footer}>
                <OverlayTrigger placement="top" overlay={tooltip}>
                    <p className="description"
                       onClick={() => this.props.onUpdate(this.props.todo)}>{this.props.todo.description}</p>
                </OverlayTrigger>
            </Panel>
        )
    }
}
export default TodoItem;