package com.cxxy.shop.response;

@FunctionalInterface
public interface ResponseConverter<T, V> {

    V convert(T t);
}

