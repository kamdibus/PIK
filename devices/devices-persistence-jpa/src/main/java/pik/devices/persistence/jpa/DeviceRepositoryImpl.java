package pik.devices.persistence.jpa;

import org.springframework.stereotype.Repository;
import pik.devices.domain.Device;
import pik.devices.domain.DeviceRepository;
import pik.devices.domain.dto.DeviceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Device> findAll() {
        return repository.findAll().stream().map(a -> a.toDomain()).collect(Collectors.toList());
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

    @Override
    public Device update(Device device) {
        DeviceEntity entity = repository.getById(device.getId());
        entity.setName(device.getName());
        return entity.toDomain();
    }
}
