package pik.values.domain;

import pik.values.domain.Value;
import pik.values.domain.ValueNotFoundException;
import pik.values.domain.ValueRepository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryValueRepository implements ValueRepository {

    private HashMap<Long, Value> map;
    private long nextId;

    public InMemoryValueRepository() {
        map = new HashMap<>();
    }

    @Override
    public Value save(Value value) {
        value.setId(nextId);
        map.put(nextId, value);
        nextId++;
        return value;
    }

    @Override
    public Value findById(long id) {
        if (!map.containsKey(id))
            throw new ValueNotFoundException(id, null);
        else return map.get(id);
    }

    @Override
    public List<Value> findAllByVariableId(long id) {
        return map.values().stream().filter(a -> a.getVariableId() == id).collect(Collectors.toList());
    }

    @Override
    public void deleteByVariableId(long variableId) {
        List<Value> list = findAllByVariableId(variableId);
        for (Value v : list)
            map.remove(v.getId());
    }
}
