package pik.values.domain;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import pik.values.dto.ValueDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class Producer {
    private KafkaProducer<Long, ValueDto> producer;

    Producer() throws IOException {
        try (InputStream props = Resources.getResource("producer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        }
    }

    public void put(ValueDto value) {
        try {
            producer.send(new ProducerRecord<Long, ValueDto>(
                    "values", 1, value.getId(), value));
        } catch (Throwable throwable) {
            System.out.printf("%s", throwable.getStackTrace());
        } finally {
            producer.close();
        }
    }
}
