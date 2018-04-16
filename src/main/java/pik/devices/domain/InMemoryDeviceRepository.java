package pik.devices.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

class InMemoryDeviceRepository implements DeviceRepository {
    private ConcurrentHashMap<Long, Device> map = new ConcurrentHashMap<>();

    @Override
    public Device save(Device device) {
        requireNonNull(device);
        map.put(device.dto().getId(), device);
        return device;
    }

    @Override
    public Page<Device> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    @Override
    public Device findDeviceById(Long id) {
        return map.get(id);
    }

    @Override
    public void deleteDeviceById(Long id) {
        map.remove(id);
    }
}
