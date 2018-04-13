package hello.devices.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface VariableRepository extends Repository<Variable, Long> {

    Variable save(Variable variable);
    List<Variable> findAllByDevice(Device device);
    Variable findVariableByDeviceAndName(Device device, String name);
    void deleteVariableByDeviceAndName(Device device, String name);

}
