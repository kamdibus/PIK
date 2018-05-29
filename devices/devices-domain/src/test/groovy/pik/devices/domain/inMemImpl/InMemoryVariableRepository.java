package pik.devices.domain.inMemImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pik.devices.domain.Variable;
import pik.devices.domain.VariableRepository;
import pik.devices.domain.dto.VariableNotFoundException;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public class InMemoryVariableRepository implements VariableRepository {
    private ConcurrentHashMap<Long, Variable> map = new ConcurrentHashMap<>();

    @Override
    public Variable save(Variable variable) {
        requireNonNull(variable);
        map.put(variable.getId(), variable);
        return variable;
    }

    @Override
    public Page<Variable> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    @Override
    public void deleteVariablesByDeviceId(long id) {
        requireNonNull(id);
        for (Variable variable: map.values()){
            if (variable.getDevice().getId() == id){
                map.remove(variable.getId());
            }
        }
    }

    @Override
    public void deleteVariableById(long id) {
        map.remove(id);
    }

    @Override
    public Variable findVariableById(long id) {
        return map.get(id);
    }

    @Override
    public Variable findOneOrThrow(long id) {
        Variable v = map.get(id);
        if (v == null)
            throw new VariableNotFoundException(id);
        return v;
    }
}
