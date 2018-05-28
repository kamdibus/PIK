package pik.values.rest;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.ProfileValueSource;
import org.springframework.web.bind.annotation.*;
import pik.values.domain.ValueProducerFacade;
import pik.values.domain.ValueFacade;
import pik.values.domain.dto.ValueDto;

import java.util.List;


@RestController
@Profile("prod")

@RequestMapping("/values")
public class ValueController {

    private ValueFacade valueFacade;
    private ValueProducerFacade valueProducer;

    public ValueController(ValueFacade valueFacade, ValueProducerFacade valueProducer) {
        this.valueFacade = valueFacade;
        this.valueProducer = valueProducer;
    }

    @GetMapping("/{variableId}")
    public ResponseEntity<List<ValueDto>> getVariableValues(@PathVariable String variableId) {
        return new ResponseEntity<>(valueFacade.getByVariable(variableId), HttpStatus.OK);
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
