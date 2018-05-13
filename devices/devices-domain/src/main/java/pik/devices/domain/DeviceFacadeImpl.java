package pik.devices.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;



import static java.util.Objects.requireNonNull;


class DeviceFacadeImpl implements DeviceFacade {

    private VariableRepository variableRepository;
    private DeviceRepository deviceRepository;
    private DeviceCreator deviceCreator;

    public DeviceFacadeImpl(VariableRepository variableRepository,
                            DeviceRepository deviceRepository,
                            DeviceCreator deviceCreator) {
        this.variableRepository = variableRepository;
        this.deviceRepository = deviceRepository;
        this.deviceCreator = deviceCreator;
    }

    @Override
    public DeviceDTO add(DeviceDTO deviceDTO){
        requireNonNull(deviceDTO);
        Device device = deviceCreator.from(deviceDTO);
        device = deviceRepository.save(device);
        return device.dto();
    }

    @Override
    public VariableDTO add(VariableDTO variableDTO){
        requireNonNull(variableDTO);
        Variable variable = deviceCreator.from(variableDTO);
        variable = variableRepository.save(variable);
        return variable.dto();
    }

    @Override
    public DeviceDTO showDevice(long id) {
        requireNonNull(id);
        Device device = deviceRepository.findOneOrThrow(id);
        return device.dto();
    }

    @Override
    public VariableDTO showVariable(long id) {
        requireNonNull(id);
        Variable variable = variableRepository.findOneOrThrow(id);
        return variable.dto();
    }

    @Override
    public void deleteDevice(long id) {
        requireNonNull(id);
        variableRepository.deleteVariablesByDeviceId(id);
        deviceRepository.deleteDeviceById(id);
    }

    @Override
    public void deleteVariable(long id) {
        requireNonNull(id);
        variableRepository.deleteVariableById(id);
    }


    @Override
    public Page<DeviceDTO> findAllDevices(Pageable pageable){
        requireNonNull(pageable);
        return deviceRepository
                .findAll(pageable)
                .map(device -> device.dto());
    }

    @Override
    public Page<VariableDTO> findAllVariables(Pageable pageable){
        requireNonNull(pageable);
        return variableRepository
                .findAll(pageable)
                .map(variable -> variable.dto());
    }
}
