import React from 'react';

class AddDeviceForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: this.props.value};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange = (event) => {
        this.setState({value: event.target.value});
    };

    handleSubmit = (event) => {
        if (this.state.value) {
            if (this.state.value.length > 20) {
                alert("Device name cannot be longer than 20 charts.");
                event.preventDefault();
            } else {
                this.props.onCreate(this.state.value);
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
                    Device:
                    <input type="text" value={this.state.value} onChange={this.handleChange}/>
                </label>
                <input type="submit" value={this.props.text}/>
            </form>
        );
    }
}

export default AddDeviceForm;

