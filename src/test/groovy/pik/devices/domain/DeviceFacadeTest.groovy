package pik.devices.domain

import org.junit.After
import org.junit.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import pik.devices.dto.DeviceDTO
import pik.devices.dto.DeviceNotFoundException
import pik.devices.dto.VariableDTO
import pik.devices.dto.VariableNotFoundException
import spock.lang.Specification

class DeviceFacadeTest extends Specification implements SampleDevices {
    DeviceFacade facade = new DeviceConfiguration().deviceFacade()

    @After
    def "removeDevices"(){
        facade.deleteDevice(kettle.getId())
        facade.deleteDevice(washer.getId())
    }

    @Test
    def "Add device"() {
        when: "We add a device"
            facade.add(kettle)

        then: "We have a device"
            facade.showDevice(kettle.getId()) == kettle
    }

    @Test
    def "Add variable"() {
        given: "We have a device"
            facade.add(kettle)

        when: "We add a variable to device"
            VariableDTO temp = VariableDTO.builder().id(1).name("temp").deviceDTO(kettle).build()
            facade.add(temp)

        then: "We have a variable"
            facade.showVariable(temp.getId()) == temp
    }

    @Test
    def "ShowDevice"() {
        given: "We have a device"
            facade.add(kettle)

        when: "We show this device"
            DeviceDTO temp1 = facade.showDevice(kettle.getId())

        then: "This device is the one we had"
            temp1.getId() == kettle.getId()
    }

    @Test
    def "ShowVariable"() {
        given: "We have a device and his variable"
            facade.add(kettle)
            facade.add(temperature)

        when: "We show this variable"
            VariableDTO temp1 = facade.showVariable(temperature.getId())

        then: "This variable is the one we had"
            temp1.getId() == temperature.getId()
    }

    @Test
    def "DeleteDevice"() {
        given: "We have a device with variables"
            facade.add(kettle)
            facade.add(temperature)
            facade.add(current)

        when: "We remove device"
            facade.deleteDevice(kettle.getId())
            facade.showDevice(kettle.getId())
            //facade.showVariable(temp1.getId())
            //facade.showVariable(temp2.getId())

        then: "We have not device and its variables too"
            thrown(DeviceNotFoundException)
            //thrown(VariableNotFoundException)
    }

    @Test
    def "DeleteVariable"() {
        given: "We have a device with variables"
            facade.add(kettle)
            facade.add(temperature)
            facade.add(current)

        when: "We delete one of his variables"
            facade.deleteVariable(temperature.getId())
            facade.showVariable(temperature.getId())

        then: "This device has not this variables anymore"
            thrown(VariableNotFoundException)
    }

    @Test
    def "FindAllDevices"() {
        given: "we have two devices in system"
            facade.add(kettle)
            facade.add(washer)

        when: "We list all the devices"
            Page<DeviceDTO> page = facade.findAllDevices(new PageRequest(0, 10))
            List<DeviceDTO> devices = page.getContent()

        then: "We get this two devices"
            devices.contains(kettle)
            devices.contains(washer)

    }

    @Test
    def "FindAllVariables"() {
        given: "We have a device with two variables"
            facade.add(kettle)
            facade.add(temperature)
            facade.add(current)

        when: "We list all the variables that this device contains"
            Page<VariableDTO> page = facade.findAllVariables(new PageRequest(0,10))
            List<VariableDTO> variables = page.getContent()

        then: "We got that two variables we added"
            variables.contains(temperature)
            variables.contains(current)
    }
}
