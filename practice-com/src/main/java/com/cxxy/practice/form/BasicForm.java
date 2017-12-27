package com.cxxy.practice.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicForm implements Serializable{
    /**
     * 开始索引
     */
    private int pageNum;

    /**
     * 要显示的数量
     */
    private int pageSize = 10;
}
