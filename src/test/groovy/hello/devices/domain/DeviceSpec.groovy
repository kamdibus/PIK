package hello.devices.domain

import hello.devices.dto.DeviceDTO
import org.junit.After
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

class DeviceSpec extends Specification implements SampleDevices{
    DeviceFacade facade = new DeviceConfiguration().deviceFacade()

    @After
    def "remove devices"() {
        facade.delete(kettle.name, router.name)
    }

    def "should add device"() {
        when: "we add a device"
            facade.add(kettle);

        then: "system has this device"
            facade.show(kettle.name) == kettle
    }

    def "should list devices"() {
        given: "we have two devices in system"
            facade.add(kettle)
            facade.add(router)

        when: "we ask for all devices"
            Page<DeviceDTO> foundDevices = facade.findAll(new PageRequest(0, 10))

        then: "system returns the devices we have added"
            foundDevices.contains(kettle)
            foundDevices.contains(router)

    }
}
