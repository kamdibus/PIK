import React from 'react';

function ListItem(props) {
    return <li>{props.value}</li>;
}

function DeviceList(props) {
    const devices = props.devices;
    const listItems = devices.map((device) =>
    <ListItem key={device.id}
              value={device.name} />
    );
    return (
        <ul>
        {listItems}
        </ul>
    );
}

export default DeviceList;
