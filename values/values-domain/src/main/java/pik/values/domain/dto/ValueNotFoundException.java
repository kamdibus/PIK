package pik.values.domain.dto;

public class ValueNotFoundException extends RuntimeException {

    public ValueNotFoundException(long id, Throwable cause) {
        super("No Value with id " + id, cause);
    }
}
