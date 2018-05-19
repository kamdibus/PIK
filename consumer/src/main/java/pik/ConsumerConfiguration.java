package pik;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pik.values.domain.ValueFacade;
import pik.values.domain.ValueFacadeImpl;
import pik.values.domain.ValueRepository;
import pik.values.persistance.jpa.ValueRepositoryImpl;
import pik.values.persistance.jpa.ValueRepositoryJpa;

@Configuration
public class ConsumerConfiguration {

    @Bean(name = "consumer")
    Consumer consumer(ValueFacade valueFacade) { return new Consumer(valueFacade); }

}
