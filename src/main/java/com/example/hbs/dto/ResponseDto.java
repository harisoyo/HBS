package com.example.hbs.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDto<T> {

    private T data;
    private List<String> messages = new ArrayList<>();
    private List<String> errors = new ArrayList<>();


    public ResponseDto(T data) {
        this.data = data;
    }

    public ResponseDto(List<String> errors) {
        this.errors = errors;
        this.data = null;
    }

    public ResponseDto(T data, List<String> messages) {
        this.data = data;
        this.setMessages(messages);
    }
}