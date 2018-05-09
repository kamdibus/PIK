package pik.devices.dto;

public class VariableNotFoundException extends RuntimeException {
    public VariableNotFoundException(Long id){
        super("No variable of id " + id + " found", null, false, false);
    }
}
