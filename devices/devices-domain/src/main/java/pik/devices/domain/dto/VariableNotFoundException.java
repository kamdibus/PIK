package pik.devices.domain.dto;

public class VariableNotFoundException extends RuntimeException {
    public VariableNotFoundException(String id) {
        super("No variable of id '" + id + "' found", null, false, false);
    }
}
