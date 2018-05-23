package pik.values.persistence.jpa;

import org.springframework.stereotype.Repository;
import pik.values.domain.Value;
import pik.values.domain.ValueRepository;
import pik.values.domain.dto.ValueNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ValueRepositoryImpl implements ValueRepository {

    private ValueRepositoryJpa repository;


    public ValueRepositoryImpl(ValueRepositoryJpa valueRepositoryJpa) {
        repository = valueRepositoryJpa;
    }

    @Override
    public Value save(Value value) {
        return repository.save(new ValueEntity(value)).toDomain();
    }

    @Override
    public Value findById(long id) {
        ValueEntity valueEntity = repository.findById(id);
        if (valueEntity == null) throw new ValueNotFoundException(id, null);
        return valueEntity.toDomain();
    }

    @Override
    public List<Value> findAllByVariableId(String id) {
        return repository.findAllByVariableId(id).stream().map(v -> v.toDomain()).collect(Collectors.toList());
    }

    @Override
    public void deleteByVariableId(String variableId) {
        repository.deleteByVariableId(variableId);
    }

}
