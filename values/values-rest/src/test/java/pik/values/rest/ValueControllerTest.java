package pik.values.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pik.values.domain.ValueFacade;


import pik.values.domain.dto.ValueDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("prod")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ValueController.class)
@EnableWebMvc
@AutoConfigureMockMvc
public class ValueControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    ValueFacade valueFacadeMock;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenRequestedVariableValuesAreShown() throws Exception {

        String varid = "112313";
        ValueDto value1 = new ValueDto(varid, Timestamp.valueOf(LocalDateTime.of(2018, 10, 3, 12, 45)), 1251.32);
        ValueDto value2 = new ValueDto(varid, Timestamp.valueOf(LocalDateTime.of(2017, 12, 3, 12, 45)), 1123);

        when(valueFacadeMock.getByVariable(varid)).thenReturn(Arrays.asList(value1, value2));

        mockMvc.perform(MockMvcRequestBuilders.get("/values/" + varid))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].variableId").value(value1.getVariableId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].value").value(value1.getValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].variableId").value(value2.getVariableId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].value").value(value2.getValue()));
    }


    /*
    @Test
    public void whenAddedThenOk() throws Exception {
        ValueDto value = new ValueDto(120, Timestamp.valueOf(LocalDateTime.of(2016, 10, 3, 12, 45)), 12);
        String json = mapper.writeValueAsString(value);

        when(valueFacadeMock.add(any())).thenAnswer(i -> i.getArguments()[0]);

        mockMvc.perform(MockMvcRequestBuilders.post("/values")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().isOk());

    }
    */
}

