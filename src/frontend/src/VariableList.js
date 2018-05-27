import React from 'react';

import AddVariableForm from './AddVariableForm';
import Modal from './Modal';
import Chart from './Chart';

class VariableList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {show: false};

    this.hideModal = this.hideModal.bind(this);
    this.loadContent = this.loadContent.bind(this);
  }

  onDelete = (variableId) => {
    fetch('http://localhost:8080/variables/' + variableId, {
    method: 'delete'
    })
    .then(response => response.json())
         .then(() => {
          this.loadContent();
          });
  }

  onCreate = (variableName) => {
    	  fetch('http://localhost:8080/variables', {
              method: 'post',
              headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': 'http://localhost:8080'
              },
              body: JSON.stringify({id: 1, name: `${variableName}`, deviceDTO: {id: `${this.props.deviceId}`, name: `${this.props.deviceName}`}})
            }).then(res=>res.json())
              .then(() => {
              this.hideModal();
              this.loadContent();
              });
  }

  loadContent = () => {
    this.props.loadContent();
  }

  showModal = () => {
    this.setState({ show: true });
  }

  hideModal = () => {
    this.setState({ show: false });
  }

  render() {
    const deviceId = this.props.deviceId;

    //Szukam zmiennych przypisanych do danego urządzenia
    var variables = this.props.variables.filter(function (variable) {
      return variable.deviceDTO.id === deviceId;
    });

    const variableList = variables.map((variable) =>
    <Variable id={variable.id} name={variable.name}  onDelete={this.onDelete} />
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

      <Modal show={this.state.show} handleClose={this.hideModal} >
        <AddVariableForm onCreate={this.onCreate}/>
      </Modal>
      <button type='button' onClick={this.showModal}>Add variable</button>

    </ul>
    );
  }
}

class Variable extends React.Component{
  constructor(props) {
    super(props);
    this.state = {show: false};
  }

  handleDelete = () => {
      this.props.onDelete(this.props.id);
  }

    showModal = () => {
        this.setState({ show: true });
    }

    hideModal = () => {
      this.setState({ show: false });
    }

    loadContent = () => {
        fetch('http://localhost:8080/devices')
        .then(resp => resp.json())
        .then(resp => {
        	this.setState({devices: resp.content});
        })
      }

  render() {
    return (
      <tr>
        <td>{this.props.id}</td>
        <td>{this.props.name}</td>
        <td><button onClick={this.showModal}>Show chart</button></td>
        <td><button onClick={this.handleDelete}>Delete</button></td>

        <Modal show={this.state.show} handleClose={this.hideModal} >
            <h1>Variable {this.props.id}: {this.props.name}</h1>
            <Chart show={this.state.show} />
        </Modal>

      </tr>
    )
  }
}

export default VariableList;
