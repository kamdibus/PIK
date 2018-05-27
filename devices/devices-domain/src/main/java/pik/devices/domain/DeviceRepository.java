package pik.devices.domain;


import pik.devices.domain.dto.DeviceNotFoundException;

import java.util.List;

public interface DeviceRepository {

    Device save(Device device);

    List<Device> findAll();

    Device findOneOrThrow(long id) throws DeviceNotFoundException;

    void deleteDeviceById(long id);

    Device update(Device device);
}
