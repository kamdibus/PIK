package pik.devices.domain;

import lombok.*;
import pik.devices.domain.dto.VariableDTO;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Variable {

    private String id;
    private String name;
    private Device device;
    private String unit;

    VariableDTO dto(){
        return VariableDTO.builder().id(id).name(name).deviceDTO(device.dto()).unit(unit).build();
    }

}
