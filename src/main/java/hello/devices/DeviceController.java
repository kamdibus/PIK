package hello.devices;

import hello.devices.domain.DeviceFacade;
import hello.devices.dto.DeviceDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class DeviceController {
    private DeviceFacade deviceFacade;

    @GetMapping("films")
    Page<DeviceDTO> getDevices(Pageable pageable) { return deviceFacade.findAll(pageable); }

    @GetMapping("device/{name}")
    DeviceDTO getDevice(@PathVariable String name) { return deviceFacade.show(name);}
}
