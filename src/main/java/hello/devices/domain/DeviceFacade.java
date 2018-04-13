package hello.devices.domain;

import hello.devices.dto.DeviceDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;

@Transactional
@AllArgsConstructor
public class DeviceFacade {

    private DeviceRepository deviceRepository;
    private DeviceCreator deviceCreator;

    public DeviceDTO add(DeviceDTO deviceDTO){
        requireNonNull(deviceDTO);
        Device device = deviceCreator.from(deviceDTO);
        device = deviceRepository.save(device);
        return device.dto();
    }

    public DeviceDTO show(String name){
        requireNonNull(name);
        Device device = deviceRepository.findDeviceByName(name);
        return device.dto();
    }

    public void delete(String... names){
        requireNonNull(names);
        Arrays.stream(names).forEach(deviceRepository::deleteDeviceByName);
    }

    public Page<DeviceDTO> findAll(Pageable pageable){
        requireNonNull(pageable);
        return deviceRepository
                .findAll(pageable)
                .map(device -> device.dto());
    }
}
