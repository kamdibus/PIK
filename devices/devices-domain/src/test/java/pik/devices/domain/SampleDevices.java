package pik.devices.domain;


import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;

public interface SampleDevices {
    DeviceDTO kettle = createDeviceDTO((long) 100, "Czajnik");
    DeviceDTO washer = createDeviceDTO((long) 101, "Pralka");
    VariableDTO temperature = createVariableDTO(null, "Temperatura", kettle.getId(), "oC");
    VariableDTO current = createVariableDTO(null, "Prąd", kettle.getId(), "A");

    static DeviceDTO createDeviceDTO(Long id, String name){
        return DeviceDTO.builder()
                .id(id)
                .name(name)
                .build();
    }

    static VariableDTO createVariableDTO(String id, String name, long devId, String unit) {
        return VariableDTO.builder()
                .id(id)
                .name(name)
                .deviceId(devId)
                .unit(unit)
                .build();
    }
}
