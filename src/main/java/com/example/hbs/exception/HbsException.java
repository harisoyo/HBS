package com.example.hbs.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HbsException extends RuntimeException{
    private Integer code;
    private String type;
    private HttpStatus httpStatus;

    public HbsException() {
        super();
    }

    public HbsException(String message) {
        super(message);
    }

    public HbsException(String message, Exception exception) {
        super(message, exception);
    }
}
