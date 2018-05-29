package pik.devices.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {
    private Long id;
    private String name;
}
