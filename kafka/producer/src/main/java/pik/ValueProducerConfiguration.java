package pik;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ValueProducerConfiguration {

    @Bean(name = "valueProducer")
    ValueProducer valueProducer() {

        ValueProducer producer = null;
        try {
            producer = new ValueProducer();
        } catch (IOException e) {
            System.err.println("Error with creating BEAN producer");
        }
        return producer;
    }

}
