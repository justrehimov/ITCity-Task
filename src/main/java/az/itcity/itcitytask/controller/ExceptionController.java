package az.itcity.itcitytask.controller;

import az.itcity.itcitytask.dto.response.ResponseStatus;
import az.itcity.itcitytask.exception.CustomException;
import az.itcity.itcitytask.exception.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseStatus responseStatus(CustomException ex){
        return new ResponseStatus(ex.getMessage(), ex.getCode());
    }

    @ExceptionHandler(Exception.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseStatus responseStatus(Exception ex){
        return new ResponseStatus(ex.getMessage(), ExceptionCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseStatus responseStatus(BindingResult result){
        List<String> errors = result.getAllErrors().stream()
                .map(e->e.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseStatus(errors.toString(), ExceptionCode.VALIDATION_ERROR);
    }
}
