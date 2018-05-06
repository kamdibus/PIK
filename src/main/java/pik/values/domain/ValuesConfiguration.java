package pik.values.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ValuesConfiguration {

    @Bean
    ValueFacadeImpl valueFacade(ValueRepository valueRepository) {
        return new ValueFacadeImpl(valueRepository);
    }

}
