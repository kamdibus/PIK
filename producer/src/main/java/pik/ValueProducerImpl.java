package pik;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import pik.values.domain.ValueProducerFacade;
import pik.values.dto.ValueDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class ValueProducerImpl implements ValueProducerFacade {
    private KafkaProducer<Long, ValueDto> producer;

    public ValueProducerImpl() throws IOException {
        try (InputStream props = Resources.getResource("producer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        }
    }

    public ValueDto put(ValueDto value) {
        try {
            producer.send(new ProducerRecord<>(
                    "values", value.getId(), value));
        } catch (Exception e) {
            throw new RuntimeException("Problem with accessing kafka broker.", e);
        } finally {
            producer.close();
        }
        return value;
    }
}
