package pik.devices.domain;


import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;

public interface SampleDevices {
    DeviceDTO kettle = createDeviceDTO((long) 1,"Czajnik");
    DeviceDTO washer = createDeviceDTO((long) 2, "Pralka");
    VariableDTO temperature = createVariableDTO(null, "Temperatura", kettle, "oC");
    VariableDTO current = createVariableDTO( null, "Prąd", kettle, "A");

    static DeviceDTO createDeviceDTO(Long id, String name){
        return DeviceDTO.builder()
                .id(id)
                .name(name)
                .build();
    }

    static VariableDTO createVariableDTO(String id, String name, DeviceDTO deviceDTO, String unit){
        return VariableDTO.builder()
                .id(id)
                .name(name)
                .deviceId(deviceDTO.getId())
                .unit(unit)
                .build();
    }
}
