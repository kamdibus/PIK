import React from 'react';

class AddVariableForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {name: this.props.name, unit: this.props.unit};

        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleUnitChange = this.handleUnitChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleNameChange = (event) => {
        this.setState({name: event.target.value});
    };

    handleUnitChange = (event) => {
        this.setState({unit: event.target.value});
    };

    handleSubmit = (event) => {
        if (this.state.name && this.state.unit) {
            if (this.state.name.length > 20) {
                alert("Variable name cannot be longer than 20 charts.");
                event.preventDefault();
            } else if (this.state.unit.length > 10) {
                alert("Unit cannot be longer than 10 charts.");
                event.preventDefault();
            }
            else {
                this.props.onCreate(this.state.name, this.state.unit);
                event.preventDefault();
            }
        } else {
            alert("TextBox cannot be empty.");
            event.preventDefault();
        }
    };

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    Variable:
                    <input type="text" value={this.state.name} onChange={this.handleNameChange}/>
                </label>
                <label>
                    Unit:
                    <input type="text" value={this.state.unit} onChange={this.handleUnitChange}/>
                </label>
                <input type="submit" value={this.props.text}/>
            </form>
        );
    }
}


export default AddVariableForm;

