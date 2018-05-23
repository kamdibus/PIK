package pik.values.domain;

import pik.values.domain.dto.ValueDto;

import java.sql.Timestamp;

public class Value {

    private long id;
    private String variableId;
    private Timestamp timestamp;
    private double value;

    public Value(long id, String variableId, Timestamp timestamp, double value) {
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

    public Value() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVariableId() {
        return variableId;
    }

    public void setVariableId(String variableId) {
        this.variableId = variableId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    ValueDto getDto() {
        return new ValueDto(id, variableId, timestamp, value);
    }


}
