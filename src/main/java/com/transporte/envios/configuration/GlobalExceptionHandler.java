package com.transporte.envios.configuration;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> handleMethodArgumentNoveValid(MethodArgumentNotValidException exception){

        Map<String,Object> errorDetail = new HashMap<>();
        errorDetail.put("title","invalid_form");
        errorDetail.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorDetail.put("code","invalid_form");

        List<String> errores = new ArrayList<>();

        for(FieldError fieldError:exception.getFieldErrors()){
            String codeResult = messageSource.getMessage(fieldError, Locale.getDefault());
            errores.add(codeResult);
        }
        errorDetail.put("errores",errores);

        return new ResponseEntity<>(errorDetail, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
