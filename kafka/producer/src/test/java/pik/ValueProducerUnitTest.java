package pik;

import org.junit.Test;
import pik.values.domain.dto.ValueDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


public class ValueProducerUnitTest {
    private ValueProducer valueProducer = new ValueProducerConfiguration().valueProducer();

    @Test()
    public void whenValueIsPutSuccessfullyItIsReturned() {

        ValueDto value = new ValueDto("1", Timestamp.valueOf(LocalDateTime.now()), 32);

        ValueDto valueReturned = valueProducer.put(value);

        assertThat(value).isEqualTo(valueReturned);
    }
}