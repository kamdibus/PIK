package pik.devices.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pik.devices.domain.dto.VariableNotFoundException;


public interface VariableRepository {

    Variable save(Variable variable);
    Page<Variable> findAll(Pageable pageable);

    Variable findVariableById(long id);

    void deleteVariableById(long id);

    void deleteVariablesByDeviceId(long id);

    Variable findOneOrThrow(long id);

}
