package com.cxxy.shop.response;

public class CentreCutPageResponse<T> extends CentreListResponse<T> {

    /**
     * 记录总数；
     */
    private int totalCount;

    /**
     * 当前页；
     */
    private int pageNum;

    /**
     * 每页条数；
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

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


    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
