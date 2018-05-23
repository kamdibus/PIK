package pik.devices.domain.inMemImpl;

import pik.devices.domain.Variable;
import pik.devices.domain.VariableRepository;
import pik.devices.domain.dto.VariableNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public class InMemoryVariableRepository implements VariableRepository {
    private ConcurrentHashMap<String, Variable> map = new ConcurrentHashMap<>();

    @Override
    public Variable save(Variable variable) {
        requireNonNull(variable);
        map.put(variable.getId(), variable);
        return variable;
    }

    @Override
    public List<Variable> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void deleteVariablesByDeviceId(long id) {
        requireNonNull(id);
        for (Variable variable: map.values())
            if (variable.getDevice().getId() == id)
                map.remove(variable.getId());
    }

    @Override
    public void deleteVariableById(String id) {
        map.remove(id);
    }

    @Override
    public List<Variable> findVariablesByDeviceID(Long id) {
        List<Variable> variableList = new ArrayList<>();
        for(Variable var: map.values())
            if (var.getDevice().getId() == id)
                variableList.add(var);
        return variableList;
    }

    @Override
    public Variable findOneOrThrow(String id) {
        Variable v = map.get(id);
        if (v == null)
            throw new VariableNotFoundException(id);
        return v;
    }

    @Override
    public Variable update(Variable variable){
        Variable var = map.get(variable.getId());
        var.setName(variable.getName());
        var.setUnit(variable.getUnit());
        return var;
    }
}
