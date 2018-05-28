package pik.devices.domain;

import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.DeviceNotFoundException;
import pik.devices.domain.dto.VariableDTO;
import pik.devices.domain.dto.VariableNotFoundException;
import pik.values.domain.variableModulePort.ValueVariableFacade;

import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;


class DeviceFacadeImpl implements DeviceFacade {

    private VariableRepository variableRepository;
    private DeviceRepository deviceRepository;
    private DeviceCreator deviceCreator;
    private ValueVariableFacade valueVariableFacade;

    public DeviceFacadeImpl(VariableRepository variableRepository,
                            DeviceRepository deviceRepository,
                            DeviceCreator deviceCreator,
                            ValueVariableFacade valueVariableFacade) {
        this.variableRepository = variableRepository;
        this.deviceRepository = deviceRepository;
        this.deviceCreator = deviceCreator;
        this.valueVariableFacade = valueVariableFacade;
    }

    @Override
    public DeviceDTO addDevice(DeviceDTO deviceDTO){
        requireNonNull(deviceDTO);
        Device device = deviceCreator.from(deviceDTO);
        device = deviceRepository.save(device);
        return device.dto();
    }

    @Override
    public VariableDTO addVariable(VariableDTO variableDTO){
        requireNonNull(variableDTO);
        Variable variable = deviceCreator.from(variableDTO);
        MessageDigest salt = null;
        try{
            salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
        } catch (Exception ex) {}
        variable.setId(getHex(salt.digest()));
        variable = variableRepository.save(variable);
        return variable.dto();
    }

    @Override
    public DeviceDTO getDevice(long id) {
        requireNonNull(id);
        Device device = deviceRepository.findOneOrThrow(id);
        if (device == null)
            throw new DeviceNotFoundException(id);
        return device.dto();
    }

    @Override
    public VariableDTO getVariable(String id) {
        requireNonNull(id);
        Variable variable = variableRepository.findOneOrThrow(id);
        if (variable == null)
            throw new VariableNotFoundException(id);
        return variable.dto();
    }

    @Override
    public List<VariableDTO> getAllVariables() {
        return variableRepository
                .findAll()
                .stream().map(a -> a.dto()).collect(Collectors.toList());
    }

    @Override
    public void deleteDevice(long id) {
        requireNonNull(id);
        deviceRepository.findOneOrThrow(id);
        for(String variableId: variableRepository.findVariablesByDeviceID(id).stream().map(a -> a.getId()).collect(Collectors.toList()))
            valueVariableFacade.deleteByVariable(variableId);
        variableRepository.deleteVariablesByDeviceId(id);
        deviceRepository.deleteDeviceById(id);
    }

    @Override
    public void deleteVariable(String id) {
        requireNonNull(id);
        variableRepository.findOneOrThrow(id);
        valueVariableFacade.deleteByVariable(id);
        variableRepository.deleteVariableById(id);
    }


    @Override
    public List<DeviceDTO> getDevices(){
        return deviceRepository
                .findAll()
                .stream().map(a -> a.dto()).collect(Collectors.toList());
    }

    @Override
    public List<VariableDTO> getVariablesByDeviceID(Long deviceID){
        deviceRepository.findOneOrThrow(deviceID);

        return variableRepository
                .findVariablesByDeviceID(deviceID)
                .stream().map(a -> a.dto()).collect(Collectors.toList());
    }

    @Override
    public VariableDTO updateVariable(VariableDTO variableDTO) {
        variableRepository.findOneOrThrow(variableDTO.getId());
        Variable variable = deviceCreator.from(variableDTO);
        variable = variableRepository.update(variable);
        return variable.dto();
    }

    @Override
    public DeviceDTO updateDevice(DeviceDTO deviceDTO) {
        deviceRepository.findOneOrThrow(deviceDTO.getId());
        Device device = deviceCreator.from(deviceDTO);
        device = deviceRepository.update(device);
        return device.dto();
    }

    private static String getHex(byte[] raw) {
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        final String HEXES = "0123456789ABCDEF";
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }
}
