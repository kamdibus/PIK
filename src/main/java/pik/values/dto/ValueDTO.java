package pik.values.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pik.devices.dto.DeviceDTO;

@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ValueDTO {
    private Long id;
    private String name;
    private DeviceDTO deviceDTO;
}
