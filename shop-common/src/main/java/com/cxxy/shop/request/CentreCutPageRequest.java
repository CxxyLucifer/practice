package com.cxxy.shop.request;


public abstract class CentreCutPageRequest extends CentreRequest {

    /**
     * 序列化ID.
     */
    private static final long serialVersionUID = -3583130481377033176L;

    /**
     * 当前页；
     */
    private int pageNum;

    /**
     * 每页条数；
     */
    private int pageSize;

    /**
     * @return the pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * @param pageNum the pageNum to set
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
