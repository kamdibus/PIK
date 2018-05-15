package pik.values.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pik.Producer;

import java.io.IOException;

@Configuration
public class RestConfiguration {

    @Bean
    Producer valueProducer() {

        Producer producer = null;
        try {
            producer = new Producer();
        } catch (IOException e) {
            System.err.println("Error with creating BEAN PRODUCER");
        }
        return producer;
    }
}
