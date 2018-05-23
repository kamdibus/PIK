package pik.values.rest;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pik.values.domain.ValueFacade;
import pik.values.domain.dto.ValueDto;

import java.util.List;

@RestController
@Profile("default")
@RequestMapping("/values")
public class ValueControllerDB {

    private ValueFacade valueFacade;

    public ValueControllerDB(ValueFacade valueFacade) {
        this.valueFacade = valueFacade;
    }

    @GetMapping("/{variableId}")
    public ResponseEntity<List<ValueDto>> getVariableValues(@PathVariable String variableId) {
        return new ResponseEntity<>(valueFacade.getByVariable(variableId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addValue(@RequestBody ValueDto dto) {
        ValueDto d = valueFacade.add(dto);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }
}