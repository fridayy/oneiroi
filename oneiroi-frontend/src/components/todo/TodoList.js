/**
 * Created by bnjm on 9/11/16.
 */
import React, {Component} from 'react';
import {Panel} from 'react-bootstrap';
import TodoItem from './TodoItem';
import './todo.css'

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
            <Panel>
                {this.props.todos.map(createTodoEntryRow, this)}
            </Panel>
        )
    }
}
export default TodoList;