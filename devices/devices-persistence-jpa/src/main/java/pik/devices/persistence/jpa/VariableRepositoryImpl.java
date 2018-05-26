package pik.devices.persistence.jpa;

import org.springframework.stereotype.Repository;
import pik.devices.domain.Variable;
import pik.devices.domain.VariableRepository;
import pik.devices.domain.dto.VariableNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VariableRepositoryImpl implements VariableRepository {

    VariableRepositoryJpa repository;

    public VariableRepositoryImpl(VariableRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Variable save(Variable variable) {
        return repository.save(new VariableEntity(variable)).toDomain();
    }

    @Override
    public List<Variable> findVariablesByDeviceID(Long deviceID) {
        return repository.findAllByDeviceId(deviceID).stream().map(a -> a.toDomain()).collect(Collectors.toList());
    }

    @Override
    public List<Variable> findAll() {
        return repository.findAll().stream().map(a -> a.toDomain()).collect(Collectors.toList());
    }

    @Override
    public void deleteVariableById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteVariablesByDeviceId(long id) {
        repository.deleteByDeviceId(id);
    }

    @Override
    public Variable findOneOrThrow(String id) {
        Variable v = repository.findById(id).toDomain();
        if (v == null)
            throw new VariableNotFoundException(id);
        return v;
    }

    @Override
    public Variable update(Variable variable) {
        VariableEntity entity = repository.getById(variable.getId());
        entity.setName(variable.getName());
        entity.setUnit(variable.getUnit());
        entity = repository.save(entity);
        return entity.toDomain();
    }
}
