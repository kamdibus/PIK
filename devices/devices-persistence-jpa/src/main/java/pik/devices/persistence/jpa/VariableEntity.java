package pik.devices.persistence.jpa;


import jdk.nashorn.internal.objects.annotations.Getter;
import pik.devices.domain.Device;
import pik.devices.domain.Variable;
import pik.devices.domain.dto.VariableDTO;

import javax.persistence.*;


@Entity
@Table(name = "VARIABLES")

public class VariableEntity {

    @Id
    @GeneratedValue
    @Column(name = "VARIABLE_ID", unique = true)
    private Long id;

    @Column(name = "VARIABLE_NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEVICE_ID")
    private DeviceEntity device;

    public VariableEntity(String name, DeviceEntity deviceEntity) {
        this.name = name;
        this.device = deviceEntity;
    }

    public VariableEntity(Variable variable) {
        this.name = variable.getName();
        this.id = variable.getId();
        this.device = new DeviceEntity(variable.getDevice());
    }

    Variable toDomain() {
        return new Variable(id, name, device.toDomain());
    }
}
