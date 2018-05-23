package pik.devices.persistence.jpa;


import lombok.Setter;
import pik.devices.domain.Variable;

import javax.persistence.*;


@Entity
@Table(name = "VARIABLES")

public class VariableEntity {

    @Id
    @Column(name = "VARIABLE_ID", unique = true)
    private String id;

    @Setter
    @Column(name = "VARIABLE_NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEVICE_ID")
    private DeviceEntity device;

    @Setter
    @Column(name = "VARIABLE_UNIT")
    private String unit;

    public VariableEntity(Variable variable) {
        this.name = variable.getName();
        this.id = variable.getId();
        this.device = new DeviceEntity(variable.getDevice());
        this.unit = variable.getUnit();
    }

    Variable toDomain() {
        return new Variable(id, name, device.toDomain(), unit);
    }
}
