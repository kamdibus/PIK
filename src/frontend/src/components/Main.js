require('normalize.css/normalize.css');
require('styles/App.css');

import React from 'react';
import AddDeviceFormular from './AddDeviceFormular';
import DeviceList from './DeviceList';

class AppComponent extends React.Component {
	constructor() {
		super();
    	this.state = {devices: []};
	}
	
 	 componentDidMount() {
		 fetch('http://localhost:8080/device/all')
		.then(resp => resp.json())
		.then(resp => {
			this.setState({devices: resp.content});
		})
	}

	render() {
		return (
      		<div className="main">
        	<DeviceList devices={this.state.devices} />
        	<AddDeviceFormular />
			</div>
		);
	}
}

export default AppComponent;

