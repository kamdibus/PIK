package pik;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pik.devices.domain.DeviceFacade;
import pik.devices.domain.DeviceRepository;
import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;

@RunWith(SpringRunner.class)
@EnableWebMvc
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DeviceIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DeviceRepository deviceRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void clear() {

    }

    @Test
    public void whenDeviceIsAddedStatusIsOk() throws Exception {

        //given device as json
        DeviceDTO device = new DeviceDTO((long) 1, "Device");
        String json = mapper.writeValueAsString(device);

        //perform post to /device and expect status is ok

        mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(device.getName()));

    }

    @Test
    public void whenDeviceIsAddedCanBeFetched() throws Exception {

        //given device as json
        DeviceDTO device = new DeviceDTO((long) 0, "DeviceTestName1");
        String json = mapper.writeValueAsString(device);

        //perform post to /device and expect status is ok

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DeviceDTO resultDevice = mapper.readValue(result.getResponse().getContentAsString(), DeviceDTO.class);

        //perfom get to /device and expect added device to be on device list
        mockMvc.perform(MockMvcRequestBuilders.get("/device/" + resultDevice.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(resultDevice.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(device.getName()));

    }

    @Test
    public void whenVariableIsAddedToExistingDeviceStatusIsOk() throws Exception {

        //given device as json
        DeviceDTO device = new DeviceDTO((long) 0, "DeviceWithSomeName");
        String json = mapper.writeValueAsString(device);

        //perform post to /device and expect status is ok

        mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(device.getName()));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DeviceDTO resultDevice = mapper.readValue(result.getResponse().getContentAsString(), DeviceDTO.class);

        ////perform post to /variable and expect status is ok

    }



}
