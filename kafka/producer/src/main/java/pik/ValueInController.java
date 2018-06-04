package pik;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pik.values.domain.dto.ValueDto;


@RestController
@Profile({"prod"})
@CrossOrigin
@RequestMapping("/values")
public class ValueInController {

    private ValueProducer valueProducer;

    public ValueInController(ValueProducer valueProducer) {
        this.valueProducer = valueProducer;
    }

    @PostMapping
    public ResponseEntity<?> addValue(@RequestBody ValueDto dto) {
        try {
            valueProducer.put(dto);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
