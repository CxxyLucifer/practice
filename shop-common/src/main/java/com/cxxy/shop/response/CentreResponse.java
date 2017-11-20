package com.cxxy.shop.response;

import java.io.Serializable;

public class CentreResponse<T> implements Serializable {

    /**
     * 返回结果所带出来的数据对象；
     */
    private T data;

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }
}
