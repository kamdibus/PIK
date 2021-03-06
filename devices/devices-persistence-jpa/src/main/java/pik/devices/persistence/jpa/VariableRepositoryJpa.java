package pik.devices.persistence.jpa;


import org.springframework.data.repository.Repository;

import java.util.List;


public interface VariableRepositoryJpa extends Repository<VariableEntity, String> {

    VariableEntity save(VariableEntity VariableEntity);

    List<VariableEntity> findAll();

    List<VariableEntity> findAllByDeviceId(Long id);

    VariableEntity getById(String id);

    VariableEntity findById(String id);

    void deleteById(String id);

    void deleteByDeviceId(Long id);



}
