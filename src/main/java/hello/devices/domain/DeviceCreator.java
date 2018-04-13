package hello.devices.domain;

import hello.devices.dto.DeviceDTO;

import static java.util.Objects.requireNonNull;

class DeviceCreator {
    Device from(DeviceDTO deviceDTO){
        requireNonNull(deviceDTO);
        return Device.builder()
                .name(deviceDTO.getName())
                .build();
    }
}
