package pik.devices.domain;

import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;

import java.util.List;


public interface DeviceFacade {
    DeviceDTO addDevice(DeviceDTO deviceDTO);

    VariableDTO addVariable(VariableDTO variableDTO);

    DeviceDTO getDevice(long id);

    VariableDTO getVariable(String id);

    void deleteDevice(long id);

    void deleteVariable(String id);

    List<DeviceDTO> getDevices();

    List<VariableDTO> getVariablesByDeviceID(Long deviceID);

    List<VariableDTO> getAllVariables();

    VariableDTO updateVariable(VariableDTO variableDTO);

    DeviceDTO updateDevice(DeviceDTO deviceDTO);
}
