package pik.values.persistance.jpa;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface ValueRepositoryJpa extends Repository<ValueEntity, Long> {


    ValueEntity save(ValueEntity valueEntity);

    ValueEntity findById(long id);

    List<ValueEntity> findAllByVariableId(long id);

    void deleteByVariableId(long variableId);
}
