package pik;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pik.values.domain.dto.ValueDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("prod")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ValueInController.class)
@EnableWebMvc
@AutoConfigureMockMvc
public class ValueInControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    ValueProducer valueProducerMock;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenAddedStatusIsOk() throws Exception {

        ValueDto value = new ValueDto("123123", Timestamp.valueOf(LocalDateTime.of(2018, 10, 3, 12, 45)), 1251.32);
        String json = mapper.writeValueAsString(value);

        when(valueProducerMock.put(any())).thenAnswer(i -> i.getArguments()[0]);

        mockMvc.perform(MockMvcRequestBuilders.post("/values")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

