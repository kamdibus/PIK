package pik.devices;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pik.devices.domain.DeviceFacade;
import pik.devices.dto.DeviceDTO;
import pik.devices.dto.VariableDTO;

@AllArgsConstructor
@RestController
@RequestMapping("/devices")
public class DeviceController {
    private DeviceFacade deviceFacade;

    @GetMapping("/all")
    Page<DeviceDTO> getDevices(Pageable pageable) { return deviceFacade.findAllDevices(pageable); }

    @GetMapping("/{id}")
    DeviceDTO getDevice(@PathVariable final Long id) { return deviceFacade.showDevice(id); }

    @GetMapping("/variables/all")
    Page<VariableDTO> getVariables(Pageable pageable){ return deviceFacade.findAllVariables(pageable); }

    @GetMapping("/variables/{id}")
    VariableDTO getVariable(@PathVariable final Long id) { return deviceFacade.showVariable(id); }

    /*
    @PostMapping
    DeviceDTO addDevice(@RequestBody final DeviceDTO deviceDTO) { return deviceFacade.add(deviceDTO); }

    @PostMapping
    VariableDTO addVariable(@RequestBody final VariableDTO variableDTO) { return deviceFacade.add(variableDTO); }

    @DeleteMapping
    void deleteDevices(@PathVariable final Long id) { deviceFacade.deleteDevice(id); }

    @DeleteMapping
    void deleteVariables(@PathVariable final Long id) { deviceFacade.deleteVariable(id); }
    */
}
