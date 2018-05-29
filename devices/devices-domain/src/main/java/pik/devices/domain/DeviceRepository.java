package pik.devices.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeviceRepository {

    Device save(Device device);
    Page<Device> findAll(Pageable pageable);

    Device findDeviceById(long id);

    void deleteDeviceById(long id);

    Device findOneOrThrow(long id);
}
