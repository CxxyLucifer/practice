package com.cxxy.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author:liuhui
 * Description:
 * Date: 下午8:23 2017/11/20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable{

    private Long user_id;

    private String user_name;

    private Long class_id;

    private String class_name;
}
