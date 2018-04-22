package pik.values.domain;

import pik.values.dto.ValueDto;

import java.util.List;
import java.util.stream.Collectors;

public class ValueFacade {
    private ValueRepository valueRepository;

    public ValueFacade(ValueRepository valueRepository) {
        this.valueRepository = valueRepository;
    }

    public ValueDto add(ValueDto dto) {
        return valueRepository.save(new Value(dto)).getDto();

    }

    public ValueDto get(Long id) {
        return valueRepository.findById(id).getDto();
    }

    public List<ValueDto> getByVariable(long id) {
        return valueRepository.findAllByVariableId(id).stream().map(a -> a.getDto()).collect(Collectors.toList());
    }

    public void dropValues(long variableId) {
        valueRepository.deleteByVariableId(variableId);
    }
}
