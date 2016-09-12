/**
 * Created by bnjm on 9/11/16.
 */
import React, {Component} from 'react';
import {Table} from 'react-bootstrap';
import TodoItem from './TodoItem';
import './Todo.css';

class TodoList extends Component {

    render() {
        var createTodoEntryRow = function (todo) {
            return (
                <TodoItem
                    todo={todo}
                    onDelete={this.props.onDelete}
                    onUpdate={this.props.onUpdate}
                />
            )
        };

        return (
            <div>
                <Table hover condensed striped>
                    <thead>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Created</th>
                    <th>Status</th>
                    <th>Actions</th>
                    </thead>
                    <tbody>
                    {this.props.todos.map(createTodoEntryRow, this)}
                    </tbody>
                </Table>
            </div>
        )
    }
}
export default TodoList;