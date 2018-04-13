package hello.devices.domain

import hello.devices.dto.DeviceDTO
import groovy.transform.CompileStatic

@CompileStatic
trait SampleDevices {
    DeviceDTO kettle = createDeviceDTO("Czajnik")
    DeviceDTO router = createDeviceDTO("Router")

    static private DeviceDTO createDeviceDTO(String name){
        return DeviceDTO.builder()
                .name(name)
                .build();
    }
}