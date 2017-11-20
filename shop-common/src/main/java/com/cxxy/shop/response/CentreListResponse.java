package com.cxxy.shop.response;

import java.io.Serializable;
import java.util.List;

public class CentreListResponse<T> implements Serializable{

    /**
     * 记录列表;
     */
    private List<T> dataList;

    /**
     * @return the dataList
     */
    public List<T> getDataList() {
        return dataList;
    }

    /**
     * @param dataList the dataList to set
     */
    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
