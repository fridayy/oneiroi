/**
 * Created by bnjm on 9/11/16.
 */
import React, {Component} from 'react';
import TextInputValidation from '../common/TextInputValidation';

class TodoAddForm extends Component {
    render() {
        return (
            <form className="addForm">
                <TextInputValidation label="" type="text" name="title" placeholder="Title" minLength={5}
                                     onChange={this.props.onChange} value={this.props.todo.title}/>
                <TextInputValidation label="" type="text" name="description" placeholder="Description" minLength={5}
                                     onChange={this.props.onChange} value={this.props.todo.description}/>
                <input type="submit" value="Submit" onClick={this.props.onClick}/>
            </form>
        )
    }
}

export default TodoAddForm;