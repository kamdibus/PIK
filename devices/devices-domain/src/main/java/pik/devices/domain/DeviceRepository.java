package pik.devices.domain;


import java.util.List;

public interface DeviceRepository {

    Device save(Device device);

    List<Device> findAll();

    Device findOneOrThrow(long id);

    void deleteDeviceById(long id);

    Device update(Device device);
}
