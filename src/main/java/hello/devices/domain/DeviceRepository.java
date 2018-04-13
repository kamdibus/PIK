package hello.devices.domain;

import hello.devices.dto.DeviceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
interface DeviceRepository extends Repository<Device, String> {

    Device save(Device device);
    Page<Device> findAll(Pageable pageable);
    Device findDeviceByName(String name);
    void deleteDeviceByName(String name);

    default Device findOneOrThrow(String name){
        Device device = findDeviceByName(name);
        if (device == null){
            throw new DeviceNotFoundException(name);
        }

        return device;
    }
}
