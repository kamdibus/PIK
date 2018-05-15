package pik;

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

    public Producer() throws IOException {
        try (InputStream props = Resources.getResource("pik/producer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        }
    }

    public ValueDto put(ValueDto value) {
        try {
            producer.send(new ProducerRecord<>(
                    "values", 1, value.getId(), value));
        } catch (Throwable throwable) {
            throw new RuntimeException("Internal server exception?");
        } finally {
            producer.close();
        }
        return value;
    }
}
