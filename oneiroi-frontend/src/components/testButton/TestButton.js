import React, {Component} from 'react';

/**
 * This is a simple stateful React component using an onClick event to
 * increment a number on a button.
 *
 * In order to use event handler functions they have to be bound to this object within
 * the constructor!
 */
class TestButton extends Component {
    constructor() {
        super();
        this.state = {counter: 0};
        this.increment = this.increment.bind(this); //Actual binding, this is important!
    }

    increment() {
        this.setState({counter: this.state.counter + 1})
    }

    render() {
        return (
            <button onClick={this.increment}>{this.state.counter}</button>
        );
    }
}
export default TestButton;