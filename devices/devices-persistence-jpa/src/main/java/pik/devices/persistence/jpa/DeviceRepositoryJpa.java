package pik.devices.persistence.jpa;

import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;


public interface DeviceRepositoryJpa extends Repository<DeviceEntity, Long> {

    DeviceEntity save(DeviceEntity deviceEntity);

    DeviceEntity getById(Long id);

    List<DeviceEntity> findAll();

    DeviceEntity findById(long id);

    @Transactional
    void deleteById(Long id);

}
