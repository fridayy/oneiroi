/**
 * Created by bnjm on 9/11/16.
 */
import React, {Component} from 'react';
import TodoList from './TodoList';
import TodoAddModal from './TodoAddModal';
import TodoProgressBar from './TodoProgressBar';
import {Grid, Row, Col, Panel, Label} from 'react-bootstrap';
import CloseableAlert from '../common/CloseableAlert';
import './Todo.css';

class TodoPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            todos: [],
            todo: {
                title: '',
                description: '',
                isDone: false,
                created: ''
            },
            todoStats: {
                totalTodos: '',
                markedDone: '',
                percentageDone: ''
            },
            added: false
        };
        this.setTodoState = this.setTodoState.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.deleteEntry = this.deleteEntry.bind(this);
        this.updateEntryStatus = this.updateEntryStatus.bind(this);
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
                todosBeforeAdd.push(data);
                this.setState({todos: todosBeforeAdd});
                this.setState({added: true});
            })
            .catch(err => console.error(err.toString()));
    }

    queryTodosFromApi() {
        fetch("http://localhost:8080/api/v1/todos")
            .then(response => response.json())
            .then(data => this.setState({todos: data}))
            .catch(err => console.error(err.toString()));
    }

    deleteEntry(id) {
        fetch("http://localhost:8080/api/v1/todo/" + id, {
            method: 'DELETE'
        }).then(response => {
            this.queryTodosFromApi();
            this.queryStatisticsFromApi();
        }).catch(err => console.error(err.toString()));
    }

    updateEntryStatus(todo) {
        todo.isDone = !todo.isDone;
        fetch("http://localhost:8080/api/v1/todo/", {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(todo)
        }).then(response => {
            this.queryTodosFromApi();
            this.queryStatisticsFromApi();
        }).catch(err => console.error(err.toString()));
    }

    queryStatisticsFromApi() {
        fetch("http://localhost:8080/api/v1/todos/stats")
            .then(response => response.json())
            .then(data => this.setState({todoStats: data}))
            .catch(err => console.error(err.toString()));
    }

    componentDidMount() {
        this.queryTodosFromApi();
        this.queryStatisticsFromApi();
    }


    render() {
        return (
            <Grid className="todoContainer">
                <Row className="show-grid">
                    <Col xs={12} md={8}>
                        <CloseableAlert visible={this.state.added}
                                        alertType="success"
                                        title="Successfully Added!"
                                        text="You successfully added an item!"/>
                        <h4>Tasks</h4>
                        <Panel>
                            <TodoList
                                todos={this.state.todos}
                                todo={this.state.todo}
                                onDelete={this.deleteEntry}
                                onUpdate={this.updateEntryStatus}
                            />
                        </Panel>
                    </Col>
                    <Col className="todoStats" xs={6} md={4}>
                        <h4>Statistics</h4>
                        <Panel>
                            <p>Open: <Label bsStyle="danger">{this.state.todoStats.totalTodos}</Label></p>
                            <p>Closed: <Label bsStyle="success">{this.state.todoStats.markedDone}</Label></p>
                            <p>Progress:</p><TodoProgressBar todoStats={this.state.todoStats}/>
                        </Panel>
                        <TodoAddModal
                            todo={this.state.todo}
                            onChange={this.setTodoState}
                            onClick={this.handleSubmit}
                        />
                    </Col>
                </Row>
            </Grid>
        )
    }
}

export default TodoPage;