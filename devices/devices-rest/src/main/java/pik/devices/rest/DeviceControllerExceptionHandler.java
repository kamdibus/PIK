package pik.devices.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pik.devices.domain.dto.DeviceNotFoundException;
import pik.devices.domain.dto.VariableNotFoundException;

@ControllerAdvice
public class DeviceControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DeviceNotFoundException.class, VariableNotFoundException.class})
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
