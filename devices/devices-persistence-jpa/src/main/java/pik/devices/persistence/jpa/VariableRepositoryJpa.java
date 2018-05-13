package pik.devices.persistence.jpa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;


public interface VariableRepositoryJpa extends Repository<VariableEntity, Long> {

    VariableEntity save(VariableEntity VariableEntity);

    Page<VariableEntity> findAll(Pageable pageable);

    VariableEntity findVariableById(Long id);

    void deleteVariableById(Long id);

    void deleteVariablesByDeviceId(Long id);

}
