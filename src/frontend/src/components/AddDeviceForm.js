import React from 'react';

class AddDeviceForm extends React.Component {
  constructor(props) {
    super(props);
		this.state = {value: ''};

		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleChange = (event) => {
    this.setState({value: event.target.value});
  }

	handleSubmit = (event) => {
    fetch('http://localhost:8080/devices', {
      method: 'post',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:8080'
      },
      body: JSON.stringify({id: 7, name: `${this.state.value}`})
    }).then(res=>res.json())
      .then(res => console.log(res));
	}

	render() {
		return (
			<form onSubmit={this.handleSubmit}>
				<label>
					Device:
					 <input type="text" value={this.state.value} onChange={this.handleChange} />
				</label>
			<input type="submit" value="Add" />
			</form>
		);
	}
}


export default AddDeviceForm;

