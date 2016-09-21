/**
 * Created by bnjm on 9/11/16.
 */
import React, {Component} from 'react';
import TodoList from './TodoList';
import TodoAddModal from './TodoAddModal';
import TodoStatistic from './TodoStatistic';
import {Grid, Row, Col, Panel} from 'react-bootstrap';
import CloseableAlert from '../common/CloseableAlert';
import './todo.css';
import {browserHistory} from 'react-router'
import Navigation from '../navigation/Navigation';

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
                open: '',
                percentageDone: ''
            },
            added: false,
            alert: false
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
        fetch("/api/v1/todo", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem("token")
            },
            body: JSON.stringify(this.state.todo)
        }).then(this.handleErrors)
            .then(response => response.json())
            .then(data => {
                todosBeforeAdd.push(data);
                this.setState({todos: todosBeforeAdd});
                this.setState({added: true});
                this.queryStatisticsFromApi();
            })
            .catch(err => this.setState({alert: true}));
    }

    queryTodosFromApi() {
        fetch("/api/v1/todos", {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem("token")
            }
        }).then(this.handleErrors)
            .then(response => response.json())
            .then(data => this.setState({todos: data}))
            .catch(err => this.redirect());
    }

    deleteEntry(id) {
        fetch("/api/v1/todo/" + id, {
            method: 'DELETE',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem("token")
            }
        }).then(this.handleErrors)
            .then(response => {
                this.queryTodosFromApi();
                this.queryStatisticsFromApi();
            }).catch(err => this.redirect());
    }

    updateEntryStatus(todo) {
        todo.isDone = !todo.isDone;
        fetch("/api/v1/todo/", {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem("token")
            },
            body: JSON.stringify(todo)
        }).then(this.handleErrors)
            .then(response => {
                this.queryTodosFromApi();
                this.queryStatisticsFromApi();
            }).catch(err => this.redirect());
    }

    queryStatisticsFromApi() {
        fetch("/api/v1/todos/stats", {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem("token")
            }
        })
            .then(this.handleErrors)
            .then(response => response.json())
            .then(data => this.setState({todoStats: data}))
            .catch(err => this.redirect());
    }

    handleErrors(response) {
        if (!response.ok) {
            throw new Error("error!")
        }
        return response
    }

    redirect() {
        browserHistory.push('/login')
    }

    componentWillMount() {
        this.queryTodosFromApi();
        this.queryStatisticsFromApi();
    }


    render() {
        return (
            <div>
                <Navigation/>
            <Grid className="todoContainer">
                <Row className="show-grid">
                    <Col xs={12} md={8}>
                        <CloseableAlert visible={this.state.added}
                                        alertType="success"
                                        title="Successfully Added!"
                                        text="You successfully added an item!"/>
                        <CloseableAlert visible={this.state.alert}
                                        alertType="danger"
                                        title="Could not add Todo!"
                                        text="Sorry :("/>
                        <h4>Tasks</h4>
                            <TodoList
                                todos={this.state.todos}
                                todo={this.state.todo}
                                onDelete={this.deleteEntry}
                                onUpdate={this.updateEntryStatus}
                            />
                    </Col>
                    <Col className="todoStats" xs={6} md={4}>
                        <TodoStatistic
                            todoStats={this.state.todoStats}
                        />
                        <TodoAddModal
                            todo={this.state.todo}
                            onChange={this.setTodoState}
                            onClick={this.handleSubmit}
                        />
                    </Col>
                </Row>
            </Grid>
            </div>
        )
    }
}

export default TodoPage;