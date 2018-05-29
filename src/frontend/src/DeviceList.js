import React from 'react';

import VariableList from './VariableList';
import AddDeviceForm from './AddDeviceForm';
import Modal from './Modal';

class DeviceList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {devices: []};

    this.hideModal = this.hideModal.bind(this);
    this.loadContent = this.loadContent.bind(this);
  }

  loadContent() {
      fetch('http://localhost:8080/device')
    .then(resp => resp.json())
    .then(resp => {
        this.setState({devices: resp});
    })
  }

  componentDidMount() {
    this.loadContent();
  }

  showModal = () => {
    this.setState({ show: true });
  }

  hideModal = () => {
    this.setState({ show: false });
  }

  onDelete = (deviceId) => {
      fetch('http://localhost:8080/device/' + deviceId, {
        method: 'delete'
      })
      .then(response => response.json())
      .then(() => {
      this.loadContent();
      });
  }

  onCreate = (deviceName) => {
      fetch('http://localhost:8080/device', {
    method: 'post',
    headers: {
    'Accept': 'application/json, text/plain, */*',
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': 'http://localhost:8080'
     },
     body: JSON.stringify({id: 10000, name: `${deviceName}`})
     }).then(res=>res.json())
     .then(() => {this.hideModal();
     this.loadContent();
     });
  }

  render() {
    const deviceList = this.state.devices.map((device) =>
    <Device id={device.id} name={device.name} onDelete = {this.onDelete} />
    );
    return (
      <div>
          <ul>
          <table>
          <tbody>
              <tr>
                  <th>Id</th>
                  <th>Device name</th>
              </tr>
              {deviceList}
          </tbody>
          </table>
          </ul>
          <Modal show={this.state.show} handleClose={this.hideModal} >
          <AddDeviceForm onCreate={this.onCreate} />
          </Modal>
          <button type='button' onClick={this.showModal}>Add device</button>
      </div>
    );
    }
}

class Device extends React.Component{
  constructor(props) {
    super(props);
    this.state = {variables:  [], show: false};
  }

  handleDelete = () => {
      this.props.onDelete(this.props.id);
  }

  loadContent = () => {
      fetch('http://localhost:8080/device/variable')
          		.then(resp => resp.json())
          		.then(resp => {
                    this.setState({variables: resp});
          		})
  }

  showModal = () => {
      this.setState({ show: true });
      this.loadContent();
  }

  hideModal = () => {
    this.setState({ show: false });
  }

	render() {
		return (
		<tr>
		    <td>{this.props.id}</td>
		    <td>{this.props.name}</td>
		    <td><button onClick={this.showModal}>Show variables</button></td>
		    <td><button onClick={this.handleDelete}>Delete</button></td>

            <Modal show={this.state.show} handleClose={this.hideModal} >
                <h1>Device {this.props.id}: {this.props.name}</h1>
                <VariableList variables={this.state.variables} deviceId={this.props.id} deviceName={this.props.name}
                loadContent =  {this.loadContent}/>
            </Modal>
        </tr>
		)
	}
}

export default DeviceList;

