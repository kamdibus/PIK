import React from 'react';

import VariableList from './VariableList';
import AddDeviceForm from './AddDeviceForm';
import Modal from './Modal';
import url from './url'

class DeviceList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {devices: []};

        this.hideModal = this.hideModal.bind(this);
        this.loadContent = this.loadContent.bind(this);
    }

    loadContent() {
        fetch(url + '/device')
            .then(resp => resp.json())
            .then(resp => this.setState({devices: resp}))
            .catch(() => alert('Error, cannot display list of devices.'));
    }

    componentDidMount() {
        this.loadContent();
    }

    showModal = () => {
        this.setState({show1: true});
    };

    hideModal = () => {
        this.setState({show1: false});
    };

    onDelete = (deviceId) => {
        fetch(url + '/device/' + deviceId, {
            method: 'delete'
        })
            .then(() => this.loadContent())
            .catch(() => alert('Error, cannot delete device.'));
    };

    onCreate = (deviceName) => {
        fetch(url + '/device', {
            method: 'post',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: 10000, name: `${deviceName}`})
        }).then(res => res.json())
            .then(() => {
                this.hideModal();
                this.loadContent();
            }).catch(() => alert('Error, cannot add device.'));
    };

    onUpdate = (deviceId, deviceName) => {
        fetch(url + '/device', {
            method: 'put',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: `${deviceId}`, name: `${deviceName}`})
        }).then(res => res.json())
            .then(() => {
                this.hideModal();
                this.loadContent();
            }).catch(() => alert('Error, cannot update device.'));
    };

    render() {
        const deviceList = this.state.devices.map((device) =>
            <Device id={device.id} name={device.name} onDelete={this.onDelete} onUpdate={this.onUpdate}/>
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
                <Modal show={this.state.show1} handleClose={this.hideModal}>
                    <AddDeviceForm onCreate={this.onCreate} text='Add'/>
                </Modal>
                <button type='button' onClick={this.showModal}>Add device</button>
            </div>
        );
    }
}

class Device extends React.Component {
    constructor(props) {
        super(props);
        this.state = {variables: [], show1: false, show2: false};
    }

    handleDelete = () => {
        this.props.onDelete(this.props.id);
    };

    handleUpdate = (newDeviceName) => {
        this.props.onUpdate(this.props.id, newDeviceName);
        this.hideModal();
    };

    loadContent = () => {
        fetch(url + '/device/variable')
            .then(resp => resp.json())
            .then(resp => this.setState({variables: resp}))
            .catch(() => alert('Error, cannot display list of variables.'));
    };

    showVariables = () => {
        this.setState({show1: true});
        this.loadContent();
    };

    showUpdateModal = () => {
        this.setState({show2: true});
    };

    hideModal = () => {
        this.setState({show1: false, show2: false});
    };

    render() {
        return (
            <tr>
                <td>{this.props.id}</td>
                <td>{this.props.name}</td>
                <td>
                    <button onClick={this.showVariables}>Show variables</button>
                </td>
                <td>
                    <button onClick={this.handleDelete}>Delete</button>
                </td>
                <td>
                    <button onClick={this.showUpdateModal}>Update</button>
                </td>

                <Modal show={this.state.show1} handleClose={this.hideModal}>
                    <h1>Device {this.props.id}: {this.props.name}</h1>
                    <VariableList variables={this.state.variables} deviceId={this.props.id} deviceName={this.props.name}
                                  loadContent={this.loadContent}/>
                </Modal>

                <Modal show={this.state.show2} handleClose={this.hideModal}>
                    <AddDeviceForm onCreate={this.handleUpdate} text='Update'/>
                </Modal>
            </tr>
        )
    }
}

export default DeviceList;

