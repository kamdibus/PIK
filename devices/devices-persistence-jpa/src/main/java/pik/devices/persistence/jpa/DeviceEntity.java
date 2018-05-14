package pik.devices.persistence.jpa;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import lombok.NoArgsConstructor;
import pik.devices.domain.Device;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Table(name = "DEVICES")
@AllArgsConstructor
@NoArgsConstructor
public class DeviceEntity {


    @Id
    @GeneratedValue
    @Column(name = "DEVICE_ID", unique = true, nullable = false)
    private long id;

    @Column(name = "DEVICE_NAME", nullable = false)
    private String name;

    public DeviceEntity(Device device) {
        this.name = device.getName();
        this.id = device.getId();
    }

    Device toDomain() {
        return new Device(id, name);
    }

}
