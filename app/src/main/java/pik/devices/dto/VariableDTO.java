package pik.devices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class VariableDTO {
    private Long id;
    private String name;
    private DeviceDTO deviceDTO;
}
