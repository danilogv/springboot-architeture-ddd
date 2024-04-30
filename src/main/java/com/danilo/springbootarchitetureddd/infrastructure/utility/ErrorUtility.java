package com.danilo.springbootarchitetureddd.infrastructure.utility;

import com.danilo.springbootarchitetureddd.infrastructure.design_pattern.singleton.Singleton;
import com.danilo.springbootarchitetureddd.presentation.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@ControllerAdvice
public class ErrorUtility {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDTO> errorBusinessRule(ResponseStatusException ex) {
        String msg = ex.getReason();
        HttpStatusCode status = ex.getStatusCode();
        return this.getErrorDTO(msg,status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> errorBusinessRule(MethodArgumentNotValidException ex) {
        String msg = "Error not mapping.";

        if (Objects.nonNull(ex.getFieldError())) {
            msg =  ex.getFieldError().getDefaultMessage();
        }

        HttpStatusCode status = ex.getStatusCode();
        return this.getErrorDTO(msg,status);
    }

    private ResponseEntity<ErrorDTO> getErrorDTO(String msg,HttpStatusCode status) {
        ErrorDTO error = Singleton.getErrorDTO();
        error.setMessage(msg);
        return new ResponseEntity<>(error,status);
    }

}
