import React, {Component} from 'react';

let LineChart = require("react-chartjs").Line;

let chartData = {
    labels: [],
    datasets: [{
        label: '',
        data: [],
        backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)'
        ],
        borderColor: [
            'rgba(255,99,132,1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
        ],
        borderWidth: 1
    }]
};

let chartOptions = {
    scales: {
        yAxes: [{
            ticks: {
                beginAtZero: true
            }
        }]
    }
};

function timeConverter(UNIX_timestamp) {
    const a = new Date(UNIX_timestamp);
    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    const year = a.getFullYear();
    const month = months[a.getMonth()];
    const date = a.getDate();
    const hour = a.getHours();
    const min = a.getMinutes();
    const sec = a.getSeconds();
    return date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec;
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

class Chart extends Component {
    constructor(props) {
        super(props);
        this.state = {value: '40', valuesCount: 40};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange = (event) => {
        this.setState({value: event.target.value});
    };

    handleSubmit = (event) => {
        if (isNumeric(this.state.value) && this.state.value > 0) {
            this.setState({valuesCount: this.state.value});
            event.preventDefault();
        } else {
            alert("The amount of values should be positive integer");
            event.preventDefault();
        }
    };

    render() {
        if (!this.props.show) {
            return <div></div>
        }

        chartData.labels = [];
        chartData.datasets[0].data = [];

        let j;
        let maxIter;
        if (this.props.values.length < this.state.valuesCount) {
            j = 0;
            maxIter = this.props.values.length;
        } else {
            j = this.props.values.length - this.state.valuesCount;
            maxIter = this.state.valuesCount;
        }

        for (let i = 0; i < maxIter; i++) {
            chartData.datasets[0].data[i] = this.props.values[j].value;
            const time = timeConverter(this.props.values[j].timestamp);
            chartData.labels.push(time);
            j++;
            console.log(j);
        }

        return (
            <div>
                <LineChart data={chartData} options={chartOptions} width="800" height="400"/>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        Amount of values:
                        <input type="text" value={this.state.value} onChange={this.handleChange}/>
                    </label>
                    <input type="submit" value="Change"/>
                </form>
            </div>
        );
    }
}

export default Chart;

