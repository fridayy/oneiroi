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
                    key={todo.id}
                    todo={todo}
                    onDelete={this.props.onDelete}
                    onUpdate={this.props.onUpdate}
                />
            )
        };

        return (
            <div>
                <Panel>
                    {this.props.todos.filter(function (item, i) {
                        return !item.isDone;
                    }).map(createTodoEntryRow, this)}
                </Panel>
                <h4>Closed Tasks</h4>
                <Panel>
                    {this.props.todos.filter(function (item, i) {
                        return item.isDone;
                    }).map(createTodoEntryRow, this)}
                    </Panel>
            </div>
        )
    }
}
export default TodoList;