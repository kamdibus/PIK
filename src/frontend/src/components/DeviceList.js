import React from 'react';

class DeviceList extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const deviceList = this.props.devices.map((device) =>
    <Device id={device.id}
              name={device.name} />
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
  }

	render() {
		return (
			<tr>
				<td>{this.props.id}</td>
				<td>{this.props.name}</td>
			</tr>
		)
	}
}

export default DeviceList;

