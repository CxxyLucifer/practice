package com.cxxy.shop.request;

import java.io.Serializable;

public abstract class CentreRequest implements Serializable {


    /**
     * 参数校验方法，由具体的参数对象去定义.
     * 此方法可能会抛出IllegalArgumentException的运行时异常；
     * @return 校验结果；true:通过；false:不通过
     */
    public abstract boolean checkParam();
}
