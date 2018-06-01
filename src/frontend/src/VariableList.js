import React from 'react';

import AddVariableForm from './AddVariableForm';
import Modal from './Modal';
import Chart from './Chart';
import url from './url'

class VariableList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {show: false};

        this.hideModal = this.hideModal.bind(this);
        this.loadContent = this.loadContent.bind(this);
    }

    onDelete = (variableId) => {
        fetch(url + '/device/variable/' + variableId, {
            method: 'delete'
        })
            .then(() => this.loadContent())
            .catch(() => alert('Error, cannot delete variable.'));
    };

    onCreate = (variableName) => {
        fetch(url + '/device/variable', {
            method: 'post',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: 1, name: `${variableName}`, deviceId: `${this.props.deviceId}`})
        }).then(res => res.json())
            .then(() => {
                this.hideModal();
                this.loadContent();
            })
            .catch(() => alert('Error, cannot add variable.'));
    };

    onUpdate = (variableId, variableName) => {
        fetch(url + '/device/variable', {
            method: 'put',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: `${variableId}`, name: `${variableName}`, deviceId: `${this.props.deviceId}`})
        }).then(res => res.json())
            .then(() => {
                this.loadContent();
            })
            .catch(() => alert('Error, cannot add variable.'));
    };

    loadContent = () => {
        this.props.loadContent();
    };

    showModal = () => {
        this.setState({show: true});
    };

    hideModal = () => {
        this.setState({show: false});
    };

    render() {
        const deviceId = this.props.deviceId;

        //Szukam zmiennych przypisanych do danego urządzenia
        let variables = this.props.variables.filter(function (variable) {
            return variable.deviceId === deviceId;
        });

        const variableList = variables.map((variable) =>
            <Variable id={variable.id} name={variable.name} onDelete={this.onDelete} onUpdate={this.onUpdate}/>
        );

        return (
            <ul>
                <table>
                    <tbody>
                    <tr>
                        <th>Id</th>
                        <th>Variable name</th>
                    </tr>
                    {variableList}
                    </tbody>
                </table>

                <Modal show={this.state.show} handleClose={this.hideModal}>
                    <AddVariableForm onCreate={this.onCreate} text='Add'/>
                </Modal>
                <button type='button' onClick={this.showModal}>Add variable</button>

            </ul>
        );
    }
}

class Variable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {values: [], show1: false, show2: false };
    }

    handleDelete = () => {
        this.props.onDelete(this.props.id);
    };

    handleUpdate = (newVariableName) => {
        this.props.onUpdate(this.props.id, newVariableName);
        this.hideModal();
    };

    showChart = () => {
        this.loadContent();
    };

    showUpdateModal = () => {
        this.setState({show2: true});
    };

    hideModal = () => {
        this.setState({show1: false, show2: false});
    };

    loadContent = () => {
        fetch(url + '/values/' + this.props.id)
            .then(resp => resp.json())
            .then(resp => {
                this.setState({values: resp});
                this.setState({show1: true});
            }).catch(() => alert('Error, cannot display chart.'));
    };

    render() {

        return (
            <tr>
                <td>{this.props.id}</td>
                <td>{this.props.name}</td>
                <td>
                    <button onClick={this.showChart}>Show chart</button>
                </td>
                <td>
                    <button onClick={this.handleDelete}>Delete</button>
                </td>
                <td>
                    <button onClick={this.showUpdateModal}>Update</button>
                </td>

                <Modal show={this.state.show1} handleClose={this.hideModal}>
                    <h1>Variable {this.props.id}: {this.props.name}</h1>
                    <Chart show={this.state.show1} values={this.state.values}/>
                </Modal>

                <Modal show={this.state.show2} handleClose={this.hideModal}>
                    <AddVariableForm value={this.props.name} onCreate={this.handleUpdate} text='Update'/>
                </Modal>
            </tr>
        )
    }
}

export default VariableList;
