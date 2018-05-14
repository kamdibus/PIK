package pik.values.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pik.values.domain.ValueFacade;
import pik.values.dto.ValueDto;

import java.util.List;

@RestController
@RequestMapping("/values")
public class ValueController {

    private ValueFacade valueFacade;

    public ValueController(ValueFacade valueFacade) {
        this.valueFacade = valueFacade;
    }

    @GetMapping("/{variableId}")
    public ResponseEntity<List<ValueDto>> getVariableValues(@PathVariable long variableId) {
        return new ResponseEntity<>(valueFacade.getByVariable(variableId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addValue(@RequestBody ValueDto dto) {
        ValueDto d = valueFacade.add(dto);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }
}
