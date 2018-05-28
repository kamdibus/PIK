package pik.values.domain;

import pik.values.domain.dto.ValueDto;

import java.util.List;

public interface ValueFacade {

    ValueDto add(ValueDto dto);

    ValueDto get(Long id);

    List<ValueDto> getByVariable(String id);
}
