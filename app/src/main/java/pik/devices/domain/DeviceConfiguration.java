package pik.devices.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DeviceConfiguration {

    DeviceFacade deviceFacade() {
        return deviceFacade(new InMemoryDeviceRepository(), new InMemoryVariableRepository());
    }

    @Bean
    DeviceFacade deviceFacade(DeviceRepository deviceRepository, VariableRepository variableRepository){
        DeviceCreator deviceCreator = new DeviceCreator();
        return new DeviceFacade(variableRepository, deviceRepository, deviceCreator);
    }
}
