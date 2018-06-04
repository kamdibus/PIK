package pik;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import pik.values.domain.dto.ValueDto;

import java.util.Map;

public class ValueDtoDeserializer implements Deserializer<ValueDto> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public ValueDto deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        ValueDto valueDto = null;
        try {
            valueDto = mapper.readValue(data, ValueDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valueDto;
    }

    @Override
    public void close() {

    }
}
