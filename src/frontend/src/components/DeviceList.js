import React from 'react';

import VariableList from './VariableList';
import Modal from './Modal';

class DeviceList extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const deviceList = this.props.devices.map((device) =>
    <Device id={device.id}
              name={device.name}  />
    );
    return (
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
    );
    }
}

class Device extends React.Component{
 constructor(props) {
    super(props);
    this.state = {variables:  [], show: false};
  }

  handleDelete() {

  }

  showModal = () => {
    this.setState({ show: true });
      		 fetch('http://localhost:8080/devices')
      		.then(resp => resp.json())
      		.then(resp => {
      			this.setState({variables: resp.content});
      		})
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
        <VariableList variables={this.state.variables}  />
        </Modal>
			</tr>
		)
	}
}

export default DeviceList;

