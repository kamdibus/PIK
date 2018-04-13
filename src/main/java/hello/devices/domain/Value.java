package hello.devices.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "VALUES")
class Value {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="VARIABLE_ID")
    private Variable variable;

    @Column
    private String value;

    @Column
    private Timestamp timestamp;
/*
    @Column
    private Date date;

    @Column
    private Time time;
*/
    Value(Variable variable, String value, Timestamp timestamp){
        this.variable = variable;
        this.value = value;
        this.timestamp = timestamp;
        //this.date = date;
        //this.time = time;
    }
}
