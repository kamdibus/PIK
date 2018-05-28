package pik.devices.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pik.values.domain.variableModulePort.ValueVariableFacade;

@Configuration
class DeviceConfiguration {


    @Bean
    DeviceFacadeImpl deviceFacade(DeviceRepository deviceRepository, VariableRepository variableRepository, ValueVariableFacade valueVariableFacade) {
        DeviceCreator deviceCreator = new DeviceCreator(deviceRepository);
        return new DeviceFacadeImpl(variableRepository, deviceRepository, deviceCreator, valueVariableFacade);
    }
}
