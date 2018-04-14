package pik.devices.domain;

import pik.devices.dto.DeviceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
interface DeviceRepository extends Repository<Device, Long> {

    Device save(Device device);
    Page<Device> findAll(Pageable pageable);
    Device findDeviceById(Long id);
    void deleteDeviceById(Long id);

    default Device findOneOrThrow(Long id){
        Device device = findDeviceById(id);
        if (device == null){
            throw new DeviceNotFoundException(id);
        }

        return device;
    }
}
