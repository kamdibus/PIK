package pik.devices.persistence.jpa;

import org.springframework.data.repository.Repository;

import java.util.List;


public interface DeviceRepositoryJpa extends Repository<DeviceEntity, Long> {

    DeviceEntity save(DeviceEntity deviceEntity);

    DeviceEntity getById(Long id);

    List<DeviceEntity> findAll();

    DeviceEntity findDeviceById(long id);

    void deleteDeviceById(long id);

}
