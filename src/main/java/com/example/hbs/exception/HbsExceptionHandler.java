package com.example.hbs.exception;

import com.example.hbs.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class HbsExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({HbsException.class})
    public ResponseEntity<Object> handleException(
            HbsException hotelException) {
        List<String> exception = new ArrayList<>(Collections.singletonList(hotelException.getMessage()));
        ResponseDto<Object> responseDto = new ResponseDto<>(exception);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}

