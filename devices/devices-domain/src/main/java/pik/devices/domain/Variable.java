package pik.devices.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pik.devices.domain.dto.VariableDTO;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Variable {


    private long id;
    private String name;
    private Device device;

    VariableDTO dto(){
        return VariableDTO.builder().id(id).name(name).deviceDTO(device.dto()).build();
    }

}
