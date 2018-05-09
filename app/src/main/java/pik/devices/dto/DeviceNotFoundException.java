package pik.devices.dto;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(Long id){
        super("No device of id " + id + " found", null, false, false);
    }
}
