package com.example.tinkoffhomework.advice;

import com.example.tinkoffhomework.exception.ImageNotFoundException;
import com.example.tinkoffhomework.exception.ImagesNotFoundException;
import com.example.tinkoffhomework.exception.MessageNotFoundException;
import com.example.tinkoffhomework.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            ImagesNotFoundException.class,
            ImageNotFoundException.class,
            MessageNotFoundException.class
    })
    public ResponseEntity<Object> handleCarNotFoundException(NotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error ", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}