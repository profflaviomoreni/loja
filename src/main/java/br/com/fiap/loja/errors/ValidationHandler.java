package br.com.fiap.loja.errors;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<RestValidationError>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        List<RestValidationError> errors = new ArrayList<>();
        e.getFieldErrors().forEach(v -> errors.add(new RestValidationError(v.getField(), v.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errors);
    }

}

record RestValidationError( String field, String message) {}

