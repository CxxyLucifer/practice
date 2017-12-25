package com.cxxy.shop.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Author:liuhui
 * Description:
 * Date: 下午4:46 2017/12/25
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginParam implements Serializable {

    @NotNull(message = "用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名必须大于4小于20")
    private String userName;

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码必须大于6小于20")
    private String password;
}
