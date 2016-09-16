/**
 * Created by bnjm on 9/17/16.
 */
import React, {Component} from 'react';
import TodoProgressBar from './TodoProgressBar';
import {Panel, Label} from 'react-bootstrap';
import {Doughnut} from 'react-chartjs-2';

class TodoStatistic extends Component {
    render() {

        var data = function (data) {
            var chartData = {
            labels: [
                "Done",
                "In Progress"
            ],
            datasets: [
                {
                    data: [data.markedDone, data.open],
                    backgroundColor: [
                        "#43A047",
                        "#FF6384"
                    ],
                    hoverBackgroundColor: [
                        "#43A047",
                        "#FF6384"
                    ]
                }]
            }
            return chartData
        };

        return (
            <div>
                <h4>Statistics</h4>
                <Panel>
                    <p>Open: <Label bsStyle="danger">{this.props.todoStats.open}</Label></p>
                    <p>Closed: <Label bsStyle="success">{this.props.todoStats.markedDone}</Label></p>
                    <p>Progress:</p>
                    <Doughnut data={data(this.props.todoStats)}/>
                    <br/>
                    <TodoProgressBar todoStats={this.props.todoStats}/>
                </Panel>
            </div>
        )
    }
}

export default TodoStatistic;