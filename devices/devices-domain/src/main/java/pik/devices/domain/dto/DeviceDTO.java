package pik.devices.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pik.devices.domain.Device;

@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class DeviceDTO {
    private Long id;
    private String name;

    public DeviceDTO() {}
}
