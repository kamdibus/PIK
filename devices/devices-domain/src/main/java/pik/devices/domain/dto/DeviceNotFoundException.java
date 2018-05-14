package pik.devices.domain.dto;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(long id) {
        super("No device of id " + id + " found", null, false, false);
    }
}
