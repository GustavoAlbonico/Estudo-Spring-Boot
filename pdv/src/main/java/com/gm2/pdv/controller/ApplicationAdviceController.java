package com.gm2.pdv.controller;

import com.gm2.pdv.dto.ResponseDTO;
import com.gm2.pdv.exceptions.InvalidOperationException;
import com.gm2.pdv.exceptions.NoItemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationAdviceController {

    @ExceptionHandler(NoItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleNoItemException(NoItemException ex){
        String messageError =  ex.getMessage();
        return new ResponseDTO(messageError);
    }

    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleInvalidOperationException(InvalidOperationException ex){
        String messageError =  ex.getMessage();
        return new ResponseDTO(messageError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleValidationExceptions(MethodArgumentNotValidException ex){
        List<String> errors =  new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });

        return new ResponseDTO(errors);
    }
}