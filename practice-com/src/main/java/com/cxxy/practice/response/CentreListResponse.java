package com.cxxy.practice.response;

import java.io.Serializable;
import java.util.List;

public class CentreListResponse<T> implements Serializable{

    /**
     * 记录列表;
     */
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
