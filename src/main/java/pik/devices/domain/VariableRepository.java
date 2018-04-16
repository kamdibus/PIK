package pik.devices.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import pik.devices.dto.VariableNotFoundException;

import javax.transaction.Transactional;

@Transactional
interface VariableRepository extends Repository<Variable, Long> {

    Variable save(Variable variable);
    Page<Variable> findAll(Pageable pageable);
    Variable findVariableById(Long id);
    void deleteVariableById(Long id);
    void deleteVariablesByDeviceId(Long id);

    default Variable findOneOrThrow(Long id){
        Variable variable = findVariableById(id);
        if (variable == null){
            throw new VariableNotFoundException(id);
        }

        return variable;
    }

}
