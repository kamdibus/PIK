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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pik.devices.domain.DeviceRepository;
import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@EnableWebMvc
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
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

        //perfom get to /device and expect fetched device to be the same as added
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

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DeviceDTO resultDevice = mapper.readValue(result.getResponse().getContentAsString(), DeviceDTO.class);

        ////perform post to /variable and expect status is ok

        VariableDTO variable = new VariableDTO("", "VariableNameTest", resultDevice.getId(), "asangh");

        json = mapper.writeValueAsString(variable);

        mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(variable.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unit").value(variable.getUnit()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deviceId").value(resultDevice.getId()));

    }

    @Test
    public void whenVariableIsAddedToExistingDeviceCanBeFetchedById() throws Exception {

        //given device as json
        DeviceDTO device = new DeviceDTO((long) 0, "DeviceWithSomeName");
        String json = mapper.writeValueAsString(device);

        //perform post to /device and expect status is ok

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DeviceDTO resultDevice = mapper.readValue(result.getResponse().getContentAsString(), DeviceDTO.class);

        ////perform post to /device/variable and expect status is ok

        VariableDTO variable = new VariableDTO("", "VariableNameTest", resultDevice.getId(), "asangh");

        json = mapper.writeValueAsString(variable);

        result = mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        VariableDTO resultVariable = mapper.readValue(result.getResponse().getContentAsString(), VariableDTO.class);

        //perform get to /device/variable and expect fetched variable to be the same as posted

        mockMvc.perform(MockMvcRequestBuilders.get("/device/variable/" + resultVariable.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(resultVariable.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(variable.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unit").value(variable.getUnit()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deviceId").value(variable.getDeviceId()));
    }

    @Test
    public void AllDeviceVariablesCanBeFetchedByDeviceId() throws Exception {

        //add first device
        DeviceDTO device1 = new DeviceDTO((long) 0, "DeviceWithSomeName1");
        String json = mapper.writeValueAsString(device1);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DeviceDTO resultDevice1 = mapper.readValue(result.getResponse().getContentAsString(), DeviceDTO.class);

        //add 2 variables
        VariableDTO variable1 = new VariableDTO("", "VariableNo1", resultDevice1.getId(), "unit1");
        VariableDTO variable2 = new VariableDTO("", "VariableNo2", resultDevice1.getId(), "unit2");

        json = mapper.writeValueAsString(variable1);

        mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        json = mapper.writeValueAsString(variable2);

        mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //add second device
        DeviceDTO device2 = new DeviceDTO((long) 0, "DeviceWithSomeName1");
        json = mapper.writeValueAsString(device1);

        result = mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DeviceDTO resultDevice2 = mapper.readValue(result.getResponse().getContentAsString(), DeviceDTO.class);

        //add 1 variable to second device
        VariableDTO variable3 = new VariableDTO("", "VariableNo3", resultDevice2.getId(), "unit3");

        json = mapper.writeValueAsString(variable3);

        mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());


        //get all variables for device 1
        mockMvc.perform(MockMvcRequestBuilders.get("/device/" + resultDevice1.getId() + "/variable"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(variable1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(variable2.getName()));

        //get all variables for device 2
        mockMvc.perform(MockMvcRequestBuilders.get("/device/" + resultDevice2.getId() + "/variable"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(variable3.getName()));

    }

    @Test
    public void whenDeviceIsDeletedAllVariablesAreDeleted() throws Exception {

        //add first device
        DeviceDTO device1 = new DeviceDTO((long) 0, "DeviceWithSomeName1");
        String json = mapper.writeValueAsString(device1);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DeviceDTO resultDevice1 = mapper.readValue(result.getResponse().getContentAsString(), DeviceDTO.class);

        //add 2 variables
        VariableDTO variable1 = new VariableDTO("", "VariableNo1", resultDevice1.getId(), "unit1");
        VariableDTO variable2 = new VariableDTO("", "VariableNo2", resultDevice1.getId(), "unit2");

        json = mapper.writeValueAsString(variable1);

        mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        json = mapper.writeValueAsString(variable2);

        mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //add second device
        DeviceDTO device2 = new DeviceDTO((long) 0, "DeviceWithSomeName1");
        json = mapper.writeValueAsString(device1);

        result = mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DeviceDTO resultDevice2 = mapper.readValue(result.getResponse().getContentAsString(), DeviceDTO.class);

        //add 1 variable to second device
        VariableDTO variable3 = new VariableDTO("", "VariableNo3", resultDevice2.getId(), "unit3");

        json = mapper.writeValueAsString(variable3);

        mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //remove device 1
        mockMvc.perform(MockMvcRequestBuilders.delete("/device/" + resultDevice1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //get all variables for device 1
        mockMvc.perform(MockMvcRequestBuilders.get("/device/" + resultDevice1.getId() + "/variable"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));


        //get all variables for device 2
        mockMvc.perform(MockMvcRequestBuilders.get("/device/" + resultDevice2.getId() + "/variable"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(variable3.getName()));

    }

    @Test
    public void whenVariableIsDeletedItIsNotListedInDevice() throws Exception {

        //add first device
        DeviceDTO device1 = new DeviceDTO((long) 0, "DeviceWithSomeName1");
        String json = mapper.writeValueAsString(device1);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DeviceDTO resultDevice1 = mapper.readValue(result.getResponse().getContentAsString(), DeviceDTO.class);

        //add 2 variables
        VariableDTO variable1 = new VariableDTO("", "VariableNo1", resultDevice1.getId(), "unit1");
        VariableDTO variable2 = new VariableDTO("", "VariableNo2", resultDevice1.getId(), "unit2");

        json = mapper.writeValueAsString(variable1);

        result = mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        VariableDTO resultVariable1 = mapper.readValue(result.getResponse().getContentAsString(), VariableDTO.class);

        json = mapper.writeValueAsString(variable2);

        mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());


        //remove variable 1
        mockMvc.perform(MockMvcRequestBuilders.delete("/device/variable/" + resultVariable1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //get all variables for device 1
        mockMvc.perform(MockMvcRequestBuilders.get("/device/" + resultDevice1.getId() + "/variable"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));

    }

    @Test
    public void deviceIsUpdatedAfterUpdate() throws Exception {

        //given device as json
        DeviceDTO device = new DeviceDTO((long) 0, "BeforeUpdateName");
        String json = mapper.writeValueAsString(device);

        //perform post to /device and expect status is ok

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DeviceDTO resultDevice = mapper.readValue(result.getResponse().getContentAsString(), DeviceDTO.class);

        //perfom get to /device and expect fetched device to be the same as added
        mockMvc.perform(MockMvcRequestBuilders.get("/device/" + resultDevice.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(resultDevice.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(device.getName()));

        //perform update of device
        DeviceDTO newDevice = new DeviceDTO(resultDevice.getId(), "AfterUpdateName");

        json = mapper.writeValueAsString(newDevice);

        mockMvc.perform(MockMvcRequestBuilders.put("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //perfom get to /device and expect device to have new name and old id
        mockMvc.perform(MockMvcRequestBuilders.get("/device/" + resultDevice.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(newDevice.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(newDevice.getName()));

    }

    @Test
    public void variableIsUpdatedAfterUpdate() throws Exception {

        //given device as json
        DeviceDTO device = new DeviceDTO((long) 0, "DeviceWithSomeName");
        String json = mapper.writeValueAsString(device);

        //perform post to /device and expect status is ok

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        DeviceDTO resultDevice = mapper.readValue(result.getResponse().getContentAsString(), DeviceDTO.class);

        ////perform post to /device/variable and expect status is ok

        VariableDTO variable = new VariableDTO("", "BeforeUpdateName", resultDevice.getId(), "BeforeUpdateUnit");

        json = mapper.writeValueAsString(variable);

        result = mockMvc.perform(MockMvcRequestBuilders.post("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        VariableDTO resultVariable = mapper.readValue(result.getResponse().getContentAsString(), VariableDTO.class);

        //update variable
        VariableDTO newVariable = new VariableDTO(resultVariable.getId(), "AfterUpdateName", resultVariable.getDeviceId(), "AfterUpdateUnit");

        json = mapper.writeValueAsString(newVariable);

        mockMvc.perform(MockMvcRequestBuilders.put("/device/variable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //perform get to /device/variable and expect fetched variable to be updated one

        mockMvc.perform(MockMvcRequestBuilders.get("/device/variable/" + resultVariable.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(resultVariable.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(newVariable.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unit").value(newVariable.getUnit()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deviceId").value(newVariable.getDeviceId()));
    }


}
