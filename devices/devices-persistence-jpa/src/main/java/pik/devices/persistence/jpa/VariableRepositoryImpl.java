package pik.devices.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pik.devices.domain.Variable;
import pik.devices.domain.VariableRepository;
import pik.devices.domain.dto.VariableNotFoundException;

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
    public Page<Variable> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(a -> a.toDomain());
    }

    @Override
    public Variable findVariableById(long id) {
        return repository.findVariableById(id).toDomain();
    }

    @Override
    public void deleteVariableById(long id) {
        repository.deleteVariableById(id);

    }

    @Override
    public void deleteVariablesByDeviceId(long id) {
        repository.deleteVariablesByDeviceId(id);

    }

    @Override
    public Variable findOneOrThrow(long id) {
        Variable v = repository.findVariableById(id).toDomain();
        if (v == null)
            throw new VariableNotFoundException(id);
        return v;
    }
}
