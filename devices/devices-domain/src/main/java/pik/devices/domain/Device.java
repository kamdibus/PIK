package pik.devices.domain;


import lombok.*;
import pik.devices.domain.dto.DeviceDTO;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    private long id;
    private String name;

    DeviceDTO dto(){
        return DeviceDTO.builder().id(id).name(name).build();
    }
}
