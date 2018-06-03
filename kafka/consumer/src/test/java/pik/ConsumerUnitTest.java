package pik;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import pik.values.domain.ValueFacade;
import pik.values.domain.dto.ValueDto;

import static org.assertj.core.api.Assertions.assertThat;


public class ConsumerUnitTest {


    @MockBean
    ValueFacade valueFacadeMock;

    private Consumer consumer = new ConsumerConfiguration().consumer(valueFacadeMock);

    @Test
    public void valueIsInRepoAfterConsumed() {

       ConsumerRecords<Long, ValueDto> records = consumer.consume();

       records.forEach(record -> {
           assertThat(record.value()).isEqualTo(valueFacadeMock.get(record.key()));
       });
    }
}