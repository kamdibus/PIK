package pik.devices.domain;

import groovy.transform.CompileStatic;
import pik.devices.dto.DeviceDTO;
import pik.devices.dto.VariableDTO;

@CompileStatic
public interface SampleDevices {
    DeviceDTO kettle = createDeviceDTO((long) 1,"Czajnik");
    DeviceDTO washer = createDeviceDTO((long) 2, "Pralka");
    VariableDTO temperature = VariableDTO.builder().id((long) 3).name("Temperatura").deviceDTO(kettle).build();
    VariableDTO current = VariableDTO.builder().id((long) 4).name("Prąd").deviceDTO(kettle).build();

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
