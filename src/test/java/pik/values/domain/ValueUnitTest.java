package pik.values.domain;

import org.junit.Test;

import pik.values.dto.ValueDto;
import pik.values.persistance.inmemory.InMemoryValueRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


public class ValueUnitTest {

    private ValueFacade valueFacade = new ValuesConfiguration().valueFacade(new InMemoryValueRepository());

    @Test
    public void valueIsStoredWhenAdded() {
        //given
        ValueDto value = new ValueDto(1, Timestamp.valueOf(LocalDateTime.now()), 102.12);

        //when
        long id = valueFacade.add(value).getId();

        //then
        ValueDto newValue = valueFacade.get(id);
        assertThat(newValue.getValue()).isEqualTo(value.getValue());
        assertThat(newValue.getVariableId()).isEqualTo(value.getVariableId());
        assertThat(newValue.getTimestamp()).isEqualTo(value.getTimestamp());
    }

    @Test
    public void allValuesAreGoneWhenVarDeleted() {
        //given
        long var1 = 1;
        long var2 = 2;
        valueFacade.add(new ValueDto(var1, Timestamp.valueOf(LocalDateTime.now()), 123.4));
        valueFacade.add(new ValueDto(var2, Timestamp.valueOf(LocalDateTime.now()), 12));
        valueFacade.add(new ValueDto(var2, Timestamp.valueOf(LocalDateTime.now()), 1423));
        valueFacade.add(new ValueDto(var1, Timestamp.valueOf(LocalDateTime.now()), 5432));

        //when
        valueFacade.dropValues(var1);

        //then

        assertThat(valueFacade.getByVariable(var1)).isEmpty();
        assertThat(valueFacade.getByVariable(var2)).isNotEmpty();
    }
}