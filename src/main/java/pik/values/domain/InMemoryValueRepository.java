package pik.values.domain;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class InMemoryValueRepository implements ValueRepository {

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
}
