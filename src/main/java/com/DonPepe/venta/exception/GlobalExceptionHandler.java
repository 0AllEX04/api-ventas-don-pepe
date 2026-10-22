package com.DonPepe.venta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidacionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarValidacion(
            ValidacionException ex) {

        Map<String, String> error = new HashMap<>();

        error.put("error", ex.getMessage());

        return error;
    }
}