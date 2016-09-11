/**
 * Created by bnjm on 9/11/16.
 */
import React, {Component} from 'react';
import TodoAddModal from './TodoAddModal';

class TodoList extends Component {

    render() {
        var createTodoEntryRow = function (todo) {
            var status = "Not Done";
            if (todo.isDone) {
                status = "Done";
            }

            return (
                <tr key={todo.id}>
                    <td>{todo.title}</td>
                    <td>{todo.description}</td>
                    <td>{status}</td>
                </tr>
            )
        }

        return (
            <div>
                <table className="table">
                    <thead>
                    <th>Title</th>
                    </thead>
                    <tbody>
                        {this.props.todos.map(createTodoEntryRow, this)}
                    </tbody>
                </table>
                <TodoAddModal
                    todo={this.props.todo}
                    onChange={this.props.onChange}
                    onClick={this.props.onClick}
                />
            </div>
        )
    }
}
export default TodoList;