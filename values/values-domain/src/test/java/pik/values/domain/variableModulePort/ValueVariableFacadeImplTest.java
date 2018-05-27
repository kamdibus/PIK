package pik.values.domain.variableModulePort;


import org.junit.Test;
import pik.values.domain.Value;
import pik.values.domain.ValueRepository;
import pik.values.domain.inMemImpl.InMemoryValueRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ValueVariableFacadeImplTest {

    ValueRepository repository = new InMemoryValueRepository();
    ValueVariableFacade facade = new ValueVariableFacadeImpl(repository);

    @Test
    public void valuesAreDeleted() {
        //given
        String varid_1 = "abababccad112311443331";
        String varid_2 = "abababc443saad311443331";
        String varid_3 = "abababc14afae11443331";
        repository.save(new Value(1, varid_1, Timestamp.valueOf(LocalDateTime.now()), 1152.457));
        repository.save(new Value(1, varid_2, Timestamp.valueOf(LocalDateTime.now()), 1152.457));
        repository.save(new Value(1, varid_3, Timestamp.valueOf(LocalDateTime.now()), 1152.457));
        repository.save(new Value(1, varid_1, Timestamp.valueOf(LocalDateTime.now()), 1152.457));
        repository.save(new Value(1, varid_3, Timestamp.valueOf(LocalDateTime.now()), 1152.457));
        repository.save(new Value(1, varid_2, Timestamp.valueOf(LocalDateTime.now()), 1152.457));

        //when
        facade.deleteByVariable(varid_2);

        //then
        assertThat(repository.findAllByVariableId(varid_2).isEmpty());
        assertThat(repository.findAllByVariableId(varid_1).size()).isEqualTo(2);
        assertThat(repository.findAllByVariableId(varid_3).size()).isEqualTo(2);
    }

}
