require('normalize.css/normalize.css');
require('styles/App.css');

import React from 'react';
import AddDeviceFormular from './AddDeviceFormular';
import DeviceList from './DeviceList';
import Modal from './Modal';

class AppComponent extends React.Component {
	constructor() {
		super();
    this.state = {devices: [], show: false};
  }

  componentDidMount() {
  		 fetch('http://localhost:8080/device/all')
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
        <AddDeviceFormular />
        </Modal>
        <button type='button' onClick={this.showModal}>Add device</button>
      </main>
    )
  }
}

export default AppComponent;

