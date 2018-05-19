package pik.values.domain.dto;

import java.sql.Timestamp;


public class ValueDto {

    private long id;
    private long variableId;
    private Timestamp timestamp;
    private double value;

    public ValueDto(long variableId, Timestamp timestamp, double value) {
        this.variableId = variableId;
        this.timestamp = timestamp;
        this.value = value;
    }

    public ValueDto(long id, long variableId, Timestamp timestamp, double value) {
        this.id = id;
        this.variableId = variableId;
        this.timestamp = timestamp;
        this.value = value;
    }

    public ValueDto() {

    }

    public long getId() {
        return id;
    }

    public long getVariableId() {
        return variableId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setVariableId(long variableId) {
        this.variableId = variableId;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
