package pik.devices.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pik.devices.domain.dto.DeviceDTO;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    private long id;

    private String name;

    DeviceDTO dto(){
        return DeviceDTO.builder().id(id).name(name).build();
    }
}
