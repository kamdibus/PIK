package hello.devices.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceConfiguration {

    public DeviceFacade deviceFacade() {return deviceFacade(new InMemoryDeviceRepository());}

    @Bean
    DeviceFacade deviceFacade(DeviceRepository deviceRepository){
        DeviceCreator deviceCreator = new DeviceCreator();
        return new DeviceFacade(deviceRepository, deviceCreator);
    }
}
