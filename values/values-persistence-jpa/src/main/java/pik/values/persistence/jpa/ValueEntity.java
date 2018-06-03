package pik.values.persistence.jpa;

import pik.values.domain.Value;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "VAL")
public class ValueEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String variableId;
    private Timestamp timestamp;
    private double value;

    public ValueEntity(long id, String variableId, Timestamp timestamp, double value) {
        this.id = id;
        this.variableId = variableId;
        this.timestamp = timestamp;
        this.value = value;
    }

    ValueEntity(Value value) {
        this.variableId = value.getVariableId();
        this.timestamp = value.getTimestamp();
        this.value = value.getValue();
    }

    public ValueEntity() {
    }

    Value toDomain() {
        return new Value(id, variableId, timestamp, value);
    }

}
