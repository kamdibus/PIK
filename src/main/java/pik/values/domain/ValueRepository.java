package pik.values.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface ValueRepository {

    Value save(Value value);

    Value findById(long id);

    List<Value> findAllByVariableId(long id);

    void deleteByVariableId(long variableId);

}
