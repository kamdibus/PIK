package pik.devices.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class VariableDTO {
    private String id;
    private String name;
    private DeviceDTO deviceDTO;
    private String unit;
}
