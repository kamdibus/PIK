package pik.values.domain;

import pik.values.domain.dto.ValueDto;

import java.util.List;
import java.util.stream.Collectors;

public class ValueFacadeImpl implements ValueFacade {
    private ValueRepository valueRepository;

    public ValueFacadeImpl(ValueRepository valueRepository) {
        this.valueRepository = valueRepository;
    }

    public ValueDto add(ValueDto dto) {
        return valueRepository.save(new Value(dto)).getDto();

    }

    public ValueDto get(Long id) {
        return valueRepository.findById(id).getDto();
    }

    public List<ValueDto> getByVariable(String id) {
        return valueRepository.findAllByVariableId(id).stream().map(a -> a.getDto()).collect(Collectors.toList());
    }

}
