package pik.values.persistence.jpa;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface ValueRepositoryJpa extends Repository<ValueEntity, Long> {


    ValueEntity save(ValueEntity valueEntity);

    ValueEntity findById(long id);

    List<ValueEntity> findAllByVariableId(String id);

    void deleteByVariableId(String variableId);
}
