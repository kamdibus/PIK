package pik.devices.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pik.devices.dto.DeviceDTO;
import pik.devices.dto.VariableDTO;

import javax.transaction.Transactional;

import static java.util.Objects.requireNonNull;

@Transactional
public class DeviceFacade {

    private VariableRepository variableRepository;
    private DeviceRepository deviceRepository;
    private DeviceCreator deviceCreator;

    public DeviceFacade(VariableRepository variableRepository,
                        DeviceRepository deviceRepository,
                        DeviceCreator deviceCreator){
        this.variableRepository = variableRepository;
        this.deviceRepository = deviceRepository;
        this.deviceCreator = deviceCreator;
    }

    public DeviceDTO add(DeviceDTO deviceDTO){
        requireNonNull(deviceDTO);
        Device device = deviceCreator.from(deviceDTO);
        device = deviceRepository.save(device);
        return device.dto();
    }

    public VariableDTO add(VariableDTO variableDTO){
        requireNonNull(variableDTO);
        Variable variable = deviceCreator.from(variableDTO);
        variable = variableRepository.save(variable);
        return variable.dto();
    }

    public DeviceDTO showDevice(Long id){
        requireNonNull(id);
        Device device = deviceRepository.findOneOrThrow(id);
        return device.dto();
    }

    public VariableDTO showVariable(Long id){
        requireNonNull(id);
        Variable variable = variableRepository.findOneOrThrow(id);
        return variable.dto();
    }

    public void deleteDevice(Long id){
        requireNonNull(id);
        variableRepository.deleteVariablesByDeviceId(id);
        deviceRepository.deleteDeviceById(id);
    }

    public void deleteVariable(Long id){
        requireNonNull(id);
        variableRepository.deleteVariableById(id);
    }

    public Page<DeviceDTO> findAllDevices(Pageable pageable){
        requireNonNull(pageable);
        return deviceRepository
                .findAll(pageable)
                .map(device -> device.dto());
    }

    public Page<VariableDTO> findAllVariables(Pageable pageable){
        requireNonNull(pageable);
        return variableRepository
                .findAll(pageable)
                .map(variable -> variable.dto());
    }
}
