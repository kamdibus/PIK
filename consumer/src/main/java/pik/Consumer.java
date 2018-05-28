package pik;

import com.google.common.io.Resources;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import pik.values.domain.ValueFacade;
import pik.values.domain.dto.ValueDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

/**
 *
 */
public class Consumer {
    private KafkaConsumer<Long, ValueDto> consumer;
    private ValueFacade valueFacade;

    public Consumer(ValueFacade valueFacade) {
        this.valueFacade = valueFacade;
        try (InputStream props = Resources.getResource("consumer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            if (properties.getProperty("group.id") == null) {
                properties.setProperty("group.id", "group-" + new Random().nextInt(100000));
            }
            consumer = new KafkaConsumer<>(properties);
        } catch (IOException e) {
            throw new RuntimeException("Error creating kafka KafkaConsumer", e);
        }
        consumer.subscribe(Arrays.asList("values"));

    }

    public ConsumerRecords<Long, ValueDto> consume() {
        ConsumerRecords<Long, ValueDto> records = consumer.poll(0);

        records.forEach(record -> {
            valueFacade.add(record.value());
//            System.out.println(record.value().getVariableId());
        });
        return records;
    }
}