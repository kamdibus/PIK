package pik.devices.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pik.devices.domain.DeviceFacade;
import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.DeviceNotFoundException;
import pik.devices.domain.dto.VariableDTO;
import pik.values.domain.dto.ValueDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeviceController.class)
@EnableWebMvc
@AutoConfigureMockMvc
public class DeviceControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    DeviceFacade deviceFacadeMock;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void whenDeviceIsAddedStatusIsOk() throws Exception {

        //given
        DeviceDTO device = new DeviceDTO(null, "Czajnik");
        String json = mapper.writeValueAsString(device);

        when(deviceFacadeMock.addDevice(any())).thenAnswer(i -> i.getArguments()[0]);

        mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(device.getName()));

    }

    @Test
    public void whenRequestedAllDevicesAreShown() throws Exception {

        //given
        DeviceDTO device1 = new DeviceDTO((long) 1124132, "Pralka");
        DeviceDTO device2 = new DeviceDTO((long) 776568, "Suszarka");
        DeviceDTO device3 = new DeviceDTO((long) 236234, "Lodówka");

        when(deviceFacadeMock.getDevices()).thenReturn(Arrays.asList(device1, device2, device3));

        mockMvc.perform(MockMvcRequestBuilders.get("/device"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(device1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(device2.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value(device3.getName()));


    }

    @Test
    public void whenRequestedSpecificDeviceItIsShown() throws Exception {

        //given
        DeviceDTO device1 = new DeviceDTO((long) 12, "Pralka");
        DeviceDTO device2 = new DeviceDTO((long) 3, "Suszarka");
        DeviceDTO device3 = new DeviceDTO((long) 6, "Lodówka");

        when(deviceFacadeMock.getDevice(device1.getId())).thenReturn(device1);
        when(deviceFacadeMock.getDevice(device2.getId())).thenReturn(device2);
        when(deviceFacadeMock.getDevice(device3.getId())).thenReturn(device3);

        mockMvc.perform(MockMvcRequestBuilders.get("/device/" + device2.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(device2.getName()));

    }

    @Test
    public void whenAddingVariableToExistingDeviceStatusIsOk() throws Exception {

        //given
        DeviceDTO device1 = new DeviceDTO((long) 12, "Pralka");
        VariableDTO var1 = new VariableDTO("sskskjfhs7268745914", "moc", device1.getId(), "jednostka");
        String json = mapper.writeValueAsString(var1);

        when(deviceFacadeMock.addVariable(var1)).thenAnswer(i -> i.getArguments()[0]);
        ;

        mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(var1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(var1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unit").value(var1.getUnit()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deviceId").value(device1.getId()));
    }

}
