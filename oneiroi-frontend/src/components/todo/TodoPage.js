/**
 * Created by bnjm on 9/11/16.
 */
import React, {Component} from 'react';
import TodoList from './TodoList';

class TodoPage extends Component {
    constructor(props) {
        super(props);
        this.state = {todos: [],
            todo: {
                title: '',
                description: ''
            }
        };
        this.setTodoState = this.setTodoState.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    setTodoState(e) {
        var field = e.target.name;
        var val = e.target.value;
        var items = this.state.todo;
        items[field] = val;
        this.setState({todo: items});

        return this.setState({todo: this.state.todo})
    }


    handleSubmit(e) {
        e.preventDefault();
        var todosBeforeAdd = this.state.todos;
        fetch("http://localhost:8080/api/v1/todo", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.state.todo)
        }).then(response => response.json())
            .then(data => {
                todosBeforeAdd.push(data)
                this.setState({todos: todosBeforeAdd})
            })
            .catch(err => console.error(err.toString()));
    }

    queryTodosFromApi() {
        fetch("http://localhost:8080/api/v1/todos")
            .then(response => response.json())
            .then(data => this.setState({todos: data}))
            .catch(err => console.error(err.toString()));
    }


    componentDidMount() {
        this.queryTodosFromApi();
    }


    render() {
        return (
            <TodoList
            todos={this.state.todos}
            todo={this.state.todo}
            onClick={this.handleSubmit}
            onChange={this.setTodoState}/>
        )
    }
}

export default TodoPage;