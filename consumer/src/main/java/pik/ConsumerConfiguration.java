package pik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pik.values.domain.ValueFacadeImpl;
import pik.values.domain.ValueRepository;
import pik.values.persistance.jpa.ValueRepositoryImpl;
import pik.values.persistance.jpa.ValueRepositoryJpa;

@Configuration
public class ConsumerConfiguration {

    @Autowired
    ValueRepository valueRepository(ValueRepositoryJpa valueRepositoryJpa) { return new ValueRepositoryImpl(valueRepositoryJpa); };

    @Autowired
    ValueFacadeImpl valueFacade(ValueRepository valueRepository) {
        return new ValueFacadeImpl(valueRepository);
    }

}
