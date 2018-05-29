package pik.values.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pik.values.domain.variableModulePort.ValueVariableFacadeImpl;

@Configuration
public class ValuesConfiguration {

    @Bean
    ValueFacadeImpl valueFacade(ValueRepository valueRepository) {
        return new ValueFacadeImpl(valueRepository);
    }

    @Bean
    ValueVariableFacadeImpl valueVariableFadace(ValueRepository valueRepository) {
        return new ValueVariableFacadeImpl(valueRepository);
    }

}
