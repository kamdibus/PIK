package pik.values.domain;

public class ValueNotFoundException extends RuntimeException {

    public ValueNotFoundException(long id, Throwable cause) {
        super("No Value with id " + id, cause);
    }
}
