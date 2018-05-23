package pik.devices.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pik.devices.domain.DeviceFacade;
import pik.devices.domain.dto.DeviceDTO;


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
    public void addDevice() throws Exception {
        DeviceDTO deviceDTO = new DeviceDTO((long) 1, "Czajnik");
        String json = mapper.writeValueAsString(deviceDTO);
/*
        deviceFacadeMock.addDevice(deviceDTO);

        mockMvc.perform(get("/device/{"+deviceDTO.getId().toString()+"}")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
                */
    }
}
