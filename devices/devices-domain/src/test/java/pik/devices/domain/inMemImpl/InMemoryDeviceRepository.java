package pik.devices.domain.inMemImpl;

import pik.devices.domain.Device;
import pik.devices.domain.DeviceRepository;
import pik.devices.domain.dto.DeviceNotFoundException;

import java.util.ArrayList;
import java.util.List;
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
    public List<Device> findAll() {
        return new ArrayList<>(map.values());
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

    @Override
    public Device update(Device device){
        Device dev = map.get(device.getId());
        dev.setName(device.getName());
        return dev;
    }
}
