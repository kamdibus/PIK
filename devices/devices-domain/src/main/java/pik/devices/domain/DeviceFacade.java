package pik.devices.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;


public interface DeviceFacade {
    DeviceDTO add(DeviceDTO deviceDTO);

    VariableDTO add(VariableDTO variableDTO);

    DeviceDTO showDevice(long id);

    VariableDTO showVariable(long id);

    void deleteDevice(long id);

    void deleteVariable(long id);

    Page<DeviceDTO> findAllDevices(Pageable pageable);

    Page<VariableDTO> findAllVariables(Pageable pageable);
}
