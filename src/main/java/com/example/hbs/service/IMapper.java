package com.example.hbs.service;

public interface IMapper<T,V> {
    V map(T t);
}