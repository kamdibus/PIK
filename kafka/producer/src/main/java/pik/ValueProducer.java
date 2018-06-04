package pik;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import pik.values.domain.dto.ValueDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class ValueProducer {
    private KafkaProducer<Long, ValueDto> producer;

    public ValueProducer() throws IOException {
        try (InputStream props = Resources.getResource("producer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        } catch (IOException e) {
        throw new RuntimeException("Error creating KafkaProducer", e);
    }
    }

    public ValueDto put(ValueDto value) {
        try {
            producer.send(new ProducerRecord<>(
                    "values", value.getId(), value));
        } catch (Exception e) {
            producer.close();
            throw new RuntimeException("Problem with accessing kafka broker.", e);
        } finally {

        }
        return value;
    }
}
