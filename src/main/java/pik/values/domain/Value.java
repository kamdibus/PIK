package pik.values.domain;

import pik.values.dto.ValueDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
class Value {
    @GeneratedValue
    @Id
    private long id;
    private long variableId;
    private Timestamp timestamp;
    private double value;

    Value(long id, long variableId, Timestamp timestamp, double value) {
        this.id = id;
        this.variableId = variableId;
        this.timestamp = timestamp;
        this.value = value;
    }

    Value(ValueDto dto) {
        this.variableId = dto.getVariableId();
        this.timestamp = dto.getTimestamp();
        this.value = dto.getValue();
    }

    Value() {
    }

    void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    long getVariableId() {
        return variableId;
    }

    ValueDto getDto() {
        return new ValueDto(id, variableId, timestamp, value);
    }


}
