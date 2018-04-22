package pik.values;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import pik.values.domain.ValueFacade;
import pik.values.dto.ValueDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ValueIntegrationTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ValueFacade valueFacade;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenAddedStatusIsOk() throws Exception {

        ValueDto value = new ValueDto(123123, Timestamp.valueOf(LocalDateTime.of(2018, 10, 3, 12, 45)), 1251.32);
        String json = mapper.writeValueAsString(value);

        mockMvc.perform(post("/values")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void whenRequestedVariableValuesAreShown() throws Exception {

        ValueDto value1 = new ValueDto(112313, Timestamp.valueOf(LocalDateTime.of(2018, 10, 3, 12, 45)), 1251.32);
        ValueDto value2 = new ValueDto(112313, Timestamp.valueOf(LocalDateTime.of(2017, 12, 3, 12, 45)), 1123);
        valueFacade.add(value1);
        valueFacade.add(value2);

        mockMvc.perform(get("/values/" + value1.getVariableId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].variableId").value(value1.getVariableId()))
                .andExpect(jsonPath("$[0].value").value(value1.getValue()))
                .andExpect(jsonPath("$[1].variableId").value(value2.getVariableId()))
                .andExpect(jsonPath("$[1].value").value(value2.getValue()));
    }

    @Test
    public void whenAddedCanBeGet() throws Exception {
        ValueDto value = new ValueDto(120, Timestamp.valueOf(LocalDateTime.of(2016, 10, 3, 12, 45)), 12);
        String json = mapper.writeValueAsString(value);

        mockMvc.perform(post("/values")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isOk());

        mockMvc.perform(get("/values/" + value.getVariableId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].variableId").value(value.getVariableId()))
                .andExpect(jsonPath("$[0].value").value(value.getValue()));

    }
}
