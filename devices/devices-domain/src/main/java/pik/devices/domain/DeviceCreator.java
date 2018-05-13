package pik.devices.domain;

import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;

import static java.util.Objects.requireNonNull;

class DeviceCreator {
    Device from(DeviceDTO deviceDTO){
        requireNonNull(deviceDTO);
        return Device.builder()
                .id(deviceDTO.getId())
                .name(deviceDTO.getName())
                .build();
    }

    Variable from(VariableDTO variableDTO){
        requireNonNull(variableDTO);
        return Variable.builder()
                .id(variableDTO.getId())
                .name(variableDTO.getName())
                .device(from(variableDTO.getDeviceDTO()))
                .build();
    }
}
