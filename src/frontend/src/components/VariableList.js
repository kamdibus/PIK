import React from 'react';

class VariableList extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const variableList = this.props.variables.map((variable) =>
    <Variable id={variable.id}
                name={variable.name}  />
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
