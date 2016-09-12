import React, {Component, PropTypes} from 'react';
import './App.css';
import Header from './components/header/Header';
import Navigation from './components/navigation/Navigation';

/**
 * This class is rendered everytime and serves as the application layout
 * Created by bnjm on 9/8/16.
 */
class App extends Component {
    render() {
        return (
            <div className="App">
                <Header/>
                <Navigation/>
                {this.props.children}
            </div>
        );
    }
}

App.propTypes = {
    children: PropTypes.object.isRequired
};

export default App;
