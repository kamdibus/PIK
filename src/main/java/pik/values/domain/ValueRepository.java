package pik.values.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

interface ValueRepository extends Repository<Value, Long> {

    Value save(Value value);

    Value findById(long id);

    List<Value> findAllByVariableId(long id);

}
