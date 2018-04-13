package hello.devices.dto;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(String name){
        super("No device of name " + name + " found", null, false, false);
    }
}
