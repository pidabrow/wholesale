package eu.piotrdabrowski.demo.wholesale.controller;

import eu.piotrdabrowski.demo.wholesale.dto.ExceptionResponseDto;
import eu.piotrdabrowski.demo.wholesale.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        HttpStatus status = NOT_FOUND;
        ExceptionResponseDto response = new ExceptionResponseDto(String.valueOf(status.value()), e.getMessage());

        return new ResponseEntity<>(response, status);
    }
}
