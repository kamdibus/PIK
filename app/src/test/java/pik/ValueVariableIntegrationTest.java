package pik;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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
import pik.devices.domain.DeviceRepository;
import pik.devices.domain.dto.DeviceDTO;
import pik.devices.domain.dto.VariableDTO;
import pik.values.domain.dto.ValueDto;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@RunWith(SpringRunner.class)
@EnableWebMvc
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
@ActiveProfiles("test")
public class ValueVariableIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DeviceRepository deviceRepository;

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void whenVariableIsDeletedAllValuesAreDeleted() throws Exception {

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

        //add values to variable 1

        ValueDto value1 = new ValueDto(resultVariable1.getId(), Timestamp.valueOf(LocalDateTime.of(2017, 10, 2, 14, 45)), 23);
        ValueDto value2 = new ValueDto(resultVariable1.getId(), Timestamp.valueOf(LocalDateTime.of(2018, 9, 3, 12, 03)), 996);
        String json1 = mapper.writeValueAsString(value1);
        String json2 = mapper.writeValueAsString(value2);

        //perfom post to /values and expect status ok
        mockMvc.perform(MockMvcRequestBuilders.post("/values")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json1))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/values")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //get all values for variable 1 and expect 2
        mockMvc.perform(MockMvcRequestBuilders.get("/values/" + resultVariable1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));


        //remove variable 1
        mockMvc.perform(MockMvcRequestBuilders.delete("/device/variable/" + resultVariable1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //get all values for variable 1 and expect 0
        mockMvc.perform(MockMvcRequestBuilders.get("/values/" + resultVariable1.getId()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


}
