package com.cxxy.practice.response;

@FunctionalInterface
public interface ResponseConverter<T, V> {

    V convert(T t);
}

