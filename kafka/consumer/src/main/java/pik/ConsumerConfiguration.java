package pik;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pik.values.domain.ValueFacade;

@Configuration
public class ConsumerConfiguration {

    @Bean(name = "consumer")
    Consumer consumer(ValueFacade valueFacade) { return new Consumer(valueFacade); }

}
