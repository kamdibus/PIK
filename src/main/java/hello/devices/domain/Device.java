package hello.devices.domain;


import hello.devices.dto.DeviceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Table(name = "DEVICES")
@AllArgsConstructor
class Device {

    @Id
    @Column(name = "DEVICE_NAME", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "device")
    private List<Variable> variables;

    DeviceDTO dto(){
        return DeviceDTO.builder().name(name).build();
    }
}
