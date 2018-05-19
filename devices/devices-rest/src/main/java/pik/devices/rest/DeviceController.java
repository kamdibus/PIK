package pik.devices.rest;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pik.devices.domain.DeviceFacade;
import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:8000")
public class DeviceController {
    private DeviceFacade deviceFacade;

    @GetMapping("devices")
    Page<DeviceDTO> getDevices(Pageable pageable) { return deviceFacade.findAllDevices(pageable); }

    @GetMapping("devices/{id}")
    DeviceDTO getDevice(@PathVariable final Long id) { return deviceFacade.showDevice(id); }

    @GetMapping("variables")
    Page<VariableDTO> getVariables(Pageable pageable){ return deviceFacade.findAllVariables(pageable); }

    @GetMapping("variables/{id}")
    VariableDTO getVariable(@PathVariable final Long id) { return deviceFacade.showVariable(id); }

    @PostMapping("devices")
    DeviceDTO addDevice(@RequestBody final DeviceDTO deviceDTO) { return deviceFacade.add(deviceDTO); }

    @PostMapping("variables")
    VariableDTO addVariable(@RequestBody final VariableDTO variableDTO) { return deviceFacade.add(variableDTO); }

    @DeleteMapping("devices/{id}")
    void deleteDevices(@PathVariable final Long id) { deviceFacade.deleteDevice(id); }

    @DeleteMapping("variables/{id}")
    void deleteVariables(@PathVariable final Long id) { deviceFacade.deleteVariable(id); }
}
