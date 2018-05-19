import React from 'react';

import VariableList from './VariableList';

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
    this.state = {variables:  [{"name" : "dupa", "id" : "1"},{"name" : "chuj", "id" : "2"}]};
  }

  handleDelete() {

  }

  handleShowVariables() {
    alert("Dupa");
  }

	render() {
		return (
			<tr>
				<td>{this.props.id}</td>
				<td>{this.props.name}</td>
				<td><button onClick={this.handleShowVariables}>Show variables</button></td>
				<td><button onClick={this.handleDelete}>Delete</button></td>
				<VariableList variables={this.state.variables}  />
			</tr>
		)
	}
}

export default DeviceList;

