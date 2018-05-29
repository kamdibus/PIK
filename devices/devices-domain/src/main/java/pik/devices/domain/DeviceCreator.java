package pik.devices.domain;

import org.springframework.beans.factory.annotation.Autowired;
import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;

import static java.util.Objects.requireNonNull;

class DeviceCreator {

    private DeviceRepository deviceRepository;

    public DeviceCreator(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    Device from(DeviceDTO deviceDTO){
        requireNonNull(deviceDTO);
        return Device.builder()
                .id(deviceDTO.getId())
                .name(deviceDTO.getName())
                .build();
    }

    Variable from(VariableDTO variableDTO){
        requireNonNull(variableDTO);

        Device device = deviceRepository.findOneOrThrow(variableDTO.getDeviceId());

        return Variable.builder()
                .id(variableDTO.getId())
                .name(variableDTO.getName())
                .device(device)
                .unit(variableDTO.getUnit())
                .build();
    }
}
