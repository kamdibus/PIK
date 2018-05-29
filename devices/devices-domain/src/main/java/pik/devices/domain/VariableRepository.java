package pik.devices.domain;

import java.util.List;


public interface VariableRepository {

    Variable save(Variable variable);

    List<Variable> findVariablesByDeviceID(Long deviceID);

    List<Variable> findAll();

    void deleteVariableById(String id);

    void deleteVariablesByDeviceId(long id);

    Variable findOneOrThrow(String id);

    Variable update(Variable variable);

}
