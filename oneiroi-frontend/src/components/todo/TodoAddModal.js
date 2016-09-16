/**
 * Created by bnjm on 9/11/16.
 */
import React, {Component} from 'react';
import {Button, Modal} from 'react-bootstrap';
import TodoAddForm from './TodoAddForm';

class TodoAddModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showModal: false
        };
        this.close = this.close.bind(this);
        this.open = this.open.bind(this);
    }

    close() {
        this.setState({showModal: false});
    }

    open() {
        this.setState({showModal: true});
    }


    render() {
        return (
            <div>
                <Button onClick={this.open}>Add Task</Button>
                <Modal show={this.state.showModal} onHide={this.close}>
                    <Modal.Header closeButton>
                        <Modal.Title>Add Task</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <TodoAddForm
                            todo={this.props.todo}
                            onChange={this.props.onChange}
                            onClick={this.props.onClick}/>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button onClick={this.close}>Close</Button>
                    </Modal.Footer>
                </Modal>
            </div>
        )
    }
}

export default TodoAddModal;