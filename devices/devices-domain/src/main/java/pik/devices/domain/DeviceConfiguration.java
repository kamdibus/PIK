package pik.devices.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DeviceConfiguration {


    @Bean
    DeviceFacadeImpl deviceFacade(DeviceRepository deviceRepository, VariableRepository variableRepository) {
        DeviceCreator deviceCreator = new DeviceCreator();
        return new DeviceFacadeImpl(variableRepository, deviceRepository, deviceCreator);
    }
}
