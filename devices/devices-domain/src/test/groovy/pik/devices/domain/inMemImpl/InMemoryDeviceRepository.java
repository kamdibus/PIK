package pik.devices.domain.inMemImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pik.devices.domain.Device;
import pik.devices.domain.DeviceRepository;
import pik.devices.domain.dto.DeviceNotFoundException;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public class InMemoryDeviceRepository implements DeviceRepository {
    private ConcurrentHashMap<Long, Device> map = new ConcurrentHashMap<>();

    @Override
    public Device save(Device device) {
        requireNonNull(device);
        map.put(device.getId(), device);
        return device;
    }

    @Override
    public Page<Device> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    @Override
    public Device findDeviceById(long id) {
        return map.get(id);
    }

    @Override
    public void deleteDeviceById(long id) {
        map.remove(id);
    }

    @Override
    public Device findOneOrThrow(long id) {
        Device d = map.get(id);
        if (d == null)
            throw new DeviceNotFoundException(id);
        return d;
    }
}
