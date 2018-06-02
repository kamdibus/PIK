package pik;

import org.junit.Test;
import pik.values.domain.ValueProducerFacade;
import pik.values.domain.dto.ValueDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


public class ValueProducerUnitTest {
    private ValueProducerFacade valueProducerFacade = new ValueProducerConfiguration().valueProducer();

    @Test()
    public void whenValueIsPutSuccessfullyItIsReturned() {

        ValueDto value = new ValueDto("1", Timestamp.valueOf(LocalDateTime.now()), 32);
        ValueDto value2 = new ValueDto("2", Timestamp.valueOf(LocalDateTime.now()), 52);


        ValueDto valueReturned = valueProducerFacade.put(value);
        ValueDto valueReturned2 = valueProducerFacade.put(value2);

        assertThat(value).isEqualTo(valueReturned);
        assertThat(value2).isEqualTo(valueReturned2);
    }
}