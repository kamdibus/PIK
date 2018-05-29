package pik.values.domain.dto;

public class NoValuesForVariableException extends RuntimeException {

    public NoValuesForVariableException(String id, Throwable cause) {
        super("No values found for variable with id " + id, cause);
    }
}


