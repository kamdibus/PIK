package pik.devices.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pik.devices.domain.Device;
import pik.devices.domain.DeviceRepository;
import pik.devices.domain.dto.DeviceNotFoundException;

@Repository
public class DeviceRepositoryImpl implements DeviceRepository {

    DeviceRepositoryJpa repository;

    public DeviceRepositoryImpl(DeviceRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Device save(Device device) {
        return repository.save(new DeviceEntity(device)).toDomain();
    }

    @Override
    public Page<Device> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(a -> a.toDomain());
    }

    @Override
    public Device findDeviceById(long id) {
        return repository.findDeviceById(id).toDomain();
    }

    @Override
    public void deleteDeviceById(long id) {
        repository.deleteDeviceById(id);
    }

    @Override
    public Device findOneOrThrow(long id) {

        Device d = repository.findDeviceById(id).toDomain();
        if (d == null)
            throw new DeviceNotFoundException(id);
        return d;
    }
}
