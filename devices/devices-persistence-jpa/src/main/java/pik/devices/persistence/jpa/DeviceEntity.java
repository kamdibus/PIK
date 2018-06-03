package pik.devices.persistence.jpa;


import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEVICE_ID", unique = true, nullable = false)
    private long id;

    @Setter
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
