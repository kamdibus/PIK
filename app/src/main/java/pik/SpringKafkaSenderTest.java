package pik;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.test.assertj.KafkaConditions;
import org.springframework.kafka.test.hamcrest.KafkaMatchers;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pik.values.domain.dto.ValueDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@DirtiesContext
public class SpringKafkaSenderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringKafkaSenderTest.class);

    private static String SENDER_TOPIC = "sender.values";

    @Autowired
    private ValueProducer valueProducer;

    private KafkaMessageListenerContainer<Long, ValueDto> container;

    private BlockingQueue<ConsumerRecord<Long, ValueDto>> records;

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, SENDER_TOPIC);

    @Before
    public void setUp() throws Exception {
        Map<String, Object> consumerProperties =
                KafkaTestUtils.consumerProps("sender", "false", embeddedKafka);

        DefaultKafkaConsumerFactory<Long, ValueDto> consumerFactory =
                new DefaultKafkaConsumerFactory<>(consumerProperties);

        ContainerProperties containerProperties = new ContainerProperties(SENDER_TOPIC);

        container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);

        records = new LinkedBlockingQueue<>();

        container.setupMessageListener((MessageListener<Long, ValueDto>) record -> {
            LOGGER.debug("test-listener received message='{}'", record.value().getTimestamp());
            records.add(record);
        });

        container.start();

        // wait until the container has the required number of assigned partitions
//        ContainerTestUtils.waitForAssignment(container, embeddedKafka.getPartitionsPerTopic());
        Thread.sleep(1000);
    }

    @After
    public void tearDown() { container.stop(); }

    @Test
    public void testPut() throws InterruptedException {
        ValueDto value = new ValueDto("1", Timestamp.valueOf(LocalDateTime.now()), 32);

        valueProducer.put(value);

        ConsumerRecord<Long, ValueDto> received = records.poll(10, TimeUnit.SECONDS);

        Assert.assertThat(received, KafkaMatchers.hasValue(value));
        assertThat(received).has(KafkaConditions.key(value.getId()));
    }
}