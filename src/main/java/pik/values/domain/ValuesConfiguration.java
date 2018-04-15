package pik.values.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pik.infrastructure.Profiles;

@Configuration
class ValuesConfiguration {


    ValueFacade valueFacade(ValueRepository valueRepository) {
        return new ValueFacade(valueRepository);
    }

    @Bean
    ValueFacade valueFacade() {
        return new ValueFacade(new InMemoryValueRepository());
    }
}
