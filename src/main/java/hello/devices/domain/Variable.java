package hello.devices.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VARIABLES")
class Variable {

    @Id @GeneratedValue
    @Column(name = "VARIABLE_ID")
    private  Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEVICE_NAME")
    private Device device;

    @OneToMany(mappedBy = "variable")
    private List<Value> values;
}
