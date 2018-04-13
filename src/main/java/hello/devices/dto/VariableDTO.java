package hello.devices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class VariableDTO {
    private String name;
    private List<String> values;
}
