package pik.devices.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pik.devices.domain.DeviceFacade;
import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/device")
public class DeviceController {
    private DeviceFacade deviceFacade;

    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> getDevices() {
        return new ResponseEntity<>(deviceFacade.getDevices(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDevice(@PathVariable final Long id) {
        DeviceDTO dto;
        try{
            dto = deviceFacade.getDevice(id);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/variable")
    public ResponseEntity<List<VariableDTO>> getAllVariables(){
        return new ResponseEntity<>(deviceFacade.getAllVariables(), HttpStatus.OK);
    }

    @GetMapping("/{deviceID}/variable")
    public ResponseEntity<List<VariableDTO>> getVariablesByDeviceID(@PathVariable long deviceID){
        return new ResponseEntity<>(deviceFacade.getVariablesByDeviceID(deviceID), HttpStatus.OK);
    }

    @GetMapping("/variable/{id}")
    public ResponseEntity<?> getVariable(@PathVariable final String id) {
        VariableDTO dto;
        try{
            dto = deviceFacade.getVariable(id);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addDevice(@RequestBody final DeviceDTO deviceDTO) {
        return new ResponseEntity<>(deviceFacade.addDevice(deviceDTO), HttpStatus.OK);
    }

    @PostMapping("/variable")
    public ResponseEntity<?> addVariable(@RequestBody final VariableDTO variableDTO) {
        return new ResponseEntity<>(deviceFacade.addVariable(variableDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable final Long id) {
        deviceFacade.deleteDevice(id);
    }

    @DeleteMapping("/variable/{id}")
    public void deleteVariable(@PathVariable final String id) {
        deviceFacade.deleteVariable(id);
    }

    @PutMapping("/variable")
    public ResponseEntity<?> updateVariable(@RequestBody final VariableDTO variableDTO, @RequestBody final String name) {
        return new ResponseEntity<>(deviceFacade.updateVariable(variableDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateDevice(@RequestBody final DeviceDTO deviceDTO) {
        return new ResponseEntity<>(deviceFacade.updateDevice(deviceDTO), HttpStatus.OK);
    }

}
