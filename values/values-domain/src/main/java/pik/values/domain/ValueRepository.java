package pik.values.domain;

import java.util.List;

public interface ValueRepository {

    Value save(Value value);

    Value findById(long id);

    List<Value> findAllByVariableId(String id);

    void deleteByVariableId(String variableId);

}
