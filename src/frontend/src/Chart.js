import React, { Component } from 'react';

var LineChart = require("react-chartjs").Line;

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

    var chartOptions = {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    };

class Chart extends Component {
  constructor(props) {
    super(props);
  }

  render() {
        if (!this.props.show) {
            return <div></div>
        }

       const valuesCount = 40;
       let j = this.props.values.length-valuesCount;
       for (var i = 0; i < valuesCount ; i++){
            chartData.datasets[0].data[i] = this.props.values[j].value;
            chartData.labels.push(this.props.values[j].timestamp);
            j++;
       }

        return (
        <div>
            <LineChart data={chartData} options={chartOptions} width="600" height="250"/>
        </div>
    );
  } 
}


export default Chart;

