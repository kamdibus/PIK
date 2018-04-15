package pik.values.domain;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import pik.infrastructure.Profiles;
import pik.values.dto.ValueDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(Profiles.DEV)
public class ValueUnitTest {

    private ValueFacade valueFacade = new ValuesConfiguration().valueFacade();

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
}