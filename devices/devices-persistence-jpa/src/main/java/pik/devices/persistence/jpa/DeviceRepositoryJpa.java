package pik.devices.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import pik.devices.domain.Device;
import pik.devices.domain.dto.DeviceNotFoundException;


public interface DeviceRepositoryJpa extends Repository<DeviceEntity, Long> {

    DeviceEntity save(DeviceEntity deviceEntity);

    Page<DeviceEntity> findAll(Pageable pageable);

    DeviceEntity findDeviceById(long id);

    void deleteDeviceById(long id);

}
