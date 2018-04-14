package pik.devices.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public class InMemoryVariableRepository implements VariableRepository{
    private ConcurrentHashMap<Long, Variable> map = new ConcurrentHashMap<>();

    @Override
    public Variable save(Variable variable) {
        requireNonNull(variable);
        map.put(variable.dto().getId(), variable);
        return variable;
    }

    @Override
    public Page<Variable> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    @Override
    public void deleteVariablesByDeviceId(Long id){
        requireNonNull(id);
        for (Variable variable: map.values()){
            if (variable.getDevice().getId() == id){
                map.remove(variable.getId());
            }
        }
    }

    @Override
    public void deleteVariableById(Long id) { map.remove(id); }

    @Override
    public Variable findVariableById(Long id) { return map.get(id);}

}
