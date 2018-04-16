package pik.devices.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pik.devices.dto.VariableDTO;

import javax.persistence.*;

@Builder
@Getter
@Entity
@Table(name = "VARIABLES")
@AllArgsConstructor
@NoArgsConstructor
class Variable {

    @Id @GeneratedValue
    @Column(name = "VARIABLE_ID", unique = true)
    private  Long id;

    @Column(name = "VARIABLE_NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEVICE_ID")
    private Device device;

    VariableDTO dto(){
        return VariableDTO.builder().id(id).name(name).deviceDTO(device.dto()).build();
    }

}
