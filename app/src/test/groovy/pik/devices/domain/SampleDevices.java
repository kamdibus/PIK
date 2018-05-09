package pik.devices.domain;

import pik.devices.dto.DeviceDTO;
import pik.devices.dto.VariableDTO;

public interface SampleDevices {
    DeviceDTO kettle = createDeviceDTO((long) 1,"Czajnik");
    DeviceDTO washer = createDeviceDTO((long) 2, "Pralka");
    VariableDTO temperature = createVariableDTO((long) 3, "Temperatura", kettle);
    VariableDTO current = createVariableDTO((long) 4, "Prąd", kettle);

    static DeviceDTO createDeviceDTO(Long id, String name){
        return DeviceDTO.builder()
                .id(id)
                .name(name)
                .build();
    }

    static VariableDTO createVariableDTO(Long id, String name, DeviceDTO deviceDTO){
        return VariableDTO.builder()
                .id(id)
                .name(name)
                .deviceDTO(deviceDTO)
                .build();
    }
}
