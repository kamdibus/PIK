import React from 'react';

import AddVariableForm from './AddVariableForm';
import Modal from './Modal';

class VariableList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {show: false};
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
      return variable.deviceDTO.id == deviceId;
    });

    const variableList = variables.map((variable) =>
    <Variable id={variable.id} name={variable.name}  />
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
        <AddVariableForm deviceId={this.props.deviceId} deviceName={this.props.deviceName}/>
      </Modal>
      <button type='button' onClick={this.showModal}>Add variable</button>

    </ul>
    );
  }
}

class Variable extends React.Component{
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <tr>
        <td>{this.props.id}</td>
        <td>{this.props.name}</td>
        <td><button>Show chart</button></td>
        <td><button>Delete</button></td>
      </tr>
    )
  }
}

export default VariableList;
