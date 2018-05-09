package pik.devices.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pik.devices.dto.DeviceDTO;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Table(name = "DEVICES")
@AllArgsConstructor
@NoArgsConstructor
class Device {

    @Id @GeneratedValue
    @Column(name = "DEVICE_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "DEVICE_NAME", nullable = false)
    private String name;

    DeviceDTO dto(){
        return DeviceDTO.builder().id(id).name(name).build();
    }
}
