package hello.devices.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public class InMemoryDeviceRepository implements DeviceRepository {
    private ConcurrentHashMap<String, Device> map = new ConcurrentHashMap<>();

    @Override
    public Device save(Device device) {
        requireNonNull(device);
        map.put(device.dto().getName(), device);
        return device;
    }

    @Override
    public Page<Device> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    @Override
    public Device findDeviceByName(String name) {
        return map.get(name);
    }

    @Override
    public void deleteDeviceByName(String name) {
        map.remove(name);
    }
}
