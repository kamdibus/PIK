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
        <DeviceList  />
      </main>
    )
  }
}

export default AppComponent;

