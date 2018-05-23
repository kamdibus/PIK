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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pik.values.domain.dto.ValueDto;


import java.sql.Timestamp;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@EnableWebMvc
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ValueIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void clear() {

    }

    @Test
    public void whenAddedStatusIsOk() throws Exception {

        //given value as json
        ValueDto value = new ValueDto("123123", Timestamp.valueOf(LocalDateTime.of(2018, 10, 3, 12, 45)), 1251.32);
        String json = mapper.writeValueAsString(value);

        //perfom post to /values and expect status ok, and the same json back
        mockMvc.perform(MockMvcRequestBuilders.post("/values")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.variableId").value(value.getVariableId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value(value.getValue()));
    }

    @Test
    public void whenValueIsAddedCanBeFetchedByVariableId() throws Exception {

        //given value as json
        ValueDto value = new ValueDto("123123", Timestamp.valueOf(LocalDateTime.of(2018, 10, 3, 12, 45)), 1251.32);
        String json = mapper.writeValueAsString(value);

        //perfom post to /values and expect status ok
        mockMvc.perform(MockMvcRequestBuilders.post("/values")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //perfom get to /values/{id} and expect same json that was added with generated id
        mockMvc.perform(MockMvcRequestBuilders.get("/values/" + value.getVariableId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].variableId").value(value.getVariableId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].value").value(value.getValue()));

    }

    @Test
    public void whenMultipleValuesAreAddedWithDifferentVariableIdCanBeFetchedByVariableId() throws Exception {

        //given value as json
        ValueDto value1 = new ValueDto("123123", Timestamp.valueOf(LocalDateTime.of(2017, 10, 2, 14, 45)), 23);
        ValueDto value2 = new ValueDto("aadbcb", Timestamp.valueOf(LocalDateTime.of(2018, 9, 3, 12, 03)), 996);
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

        //perfom get to /values/{id} and expect same json that was added with generated id
        mockMvc.perform(MockMvcRequestBuilders.get("/values/" + value1.getVariableId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].variableId").value(value1.getVariableId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].value").value(value1.getValue()));

        //perfom get to /values/{id} and expect same json that was added with generated id
        mockMvc.perform(MockMvcRequestBuilders.get("/values/" + value2.getVariableId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].variableId").value(value2.getVariableId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].value").value(value2.getValue()));

    }


    @Test
    public void whenMultipleValuesAreAddedwithSameAllAreFetched() throws Exception {

        //given value as json
        ValueDto value1 = new ValueDto("llsle", Timestamp.valueOf(LocalDateTime.of(2015, 10, 10, 10, 45)), 197.1);
        ValueDto value2 = new ValueDto("llsle", Timestamp.valueOf(LocalDateTime.of(2018, 1, 3, 17, 05)), 164);
        ValueDto value3 = new ValueDto("llsle", Timestamp.valueOf(LocalDateTime.of(2018, 1, 3, 17, 05)), 164);
        String json1 = mapper.writeValueAsString(value1);
        String json2 = mapper.writeValueAsString(value2);
        String json3 = mapper.writeValueAsString(value2);


        //perfom post to /values and expect status ok
        mockMvc.perform(MockMvcRequestBuilders.post("/values")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json1))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/values")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/values")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json3))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //perfom get to /values/{id} and expect same json that was added with generated id
        mockMvc.perform(MockMvcRequestBuilders.get("/values/" + value1.getVariableId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));


    }

}
