require('normalize.css/normalize.css');
require('styles/App.css');

import React from 'react';

import DeviceList from './DeviceList';

class AppComponent extends React.Component {
	constructor() {
		super();
    this.state = {show: false};
  }

  render() {
    return (
      <main>
        <DeviceList devices={this.state.devices} />
      </main>
    )
  }
}

export default AppComponent;

