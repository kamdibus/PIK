package pik;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pik.values.domain.dto.ValueDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class SpringKafkaReceiverTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringKafkaReceiverTest.class);

    private static String RECEIVER_TOPIC = "receiver.values";

    @Autowired
    private Consumer consumer;

    private KafkaTemplate<Long, ValueDto> template;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, RECEIVER_TOPIC);

    @Before
    public void setUp() throws Exception {
        Map<String, Object> senderProperties =
                KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());

        ProducerFactory<Long, ValueDto> producerFactory =
                new DefaultKafkaProducerFactory<>(senderProperties);

        template = new KafkaTemplate<>(producerFactory);
        template.setDefaultTopic(RECEIVER_TOPIC);

        // wait until the partitions are assigned
        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
                .getListenerContainers()) {
//            ContainerTestUtils.waitForAssignment(messageListenerContainer,
//                    embeddedKafka.getPartitionsPerTopic());
            Thread.sleep(1000);
        }
    }

    @Test
    public void testConsumer() {
        ValueDto value = new ValueDto("1", Timestamp.valueOf(LocalDateTime.now()), 32);

        template.sendDefault(value);
        LOGGER.debug("test-sender sent message='{}'", value);

        ConsumerRecords<Long, ValueDto> received = consumer.consume();
        received.forEach(record -> assertThat(record.value()).isEqualTo(value));

    }
}