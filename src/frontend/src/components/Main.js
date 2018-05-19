require('normalize.css/normalize.css');
require('styles/App.css');

import React from 'react';
import AddDeviceForm from './AddDeviceForm';
import DeviceList from './DeviceList';
import Modal from './Modal';

class AppComponent extends React.Component {
	constructor() {
		super();
    this.state = {devices: [], show: false};
  }

  componentDidMount() {
  		 fetch('http://localhost:8080/devices')
  		.then(resp => resp.json())
  		.then(resp => {
  			this.setState({devices: resp.content});
  		})
  }


  showModal = () => {
    this.setState({ show: true });
  }

  hideModal = () => {
    this.setState({ show: false });
  }

  render() {
    return (
      <main>
        <DeviceList devices={this.state.devices} />
        <Modal show={this.state.show} handleClose={this.hideModal} >
        <AddDeviceForm />
        </Modal>
        <button type='button' onClick={this.showModal}>Add device</button>
      </main>
    )
  }
}

export default AppComponent;

