package com.example.SpringAsync;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
public class SampleControllerAdvice {

    @ExceptionHandler(Exception.class)
    public SampleErrorResponseModel handleException(Exception ex){
        SampleErrorResponseModel responseModel = new SampleErrorResponseModel();
        responseModel.setCode(ex.getClass().getName());
        responseModel.setTimestamp(LocalDateTime.now());
        responseModel.setMessage(ex.getMessage());
        return responseModel;
    }

}