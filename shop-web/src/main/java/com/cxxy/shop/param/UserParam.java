package com.cxxy.shop.param;

import com.cxxy.shop.form.BasicForm;
import com.cxxy.shop.util.ValidUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserParam extends BasicForm{

    @NotNull(message = "用户名不能为空",groups = {Create.class})
    @Size(min = 4, max = 20, message = "用户名必须大于4小于20")
    private String UserName;

    @Pattern(regexp = ValidUtil.MOBILE, message = "手机号码格式不正确", groups ={Create.class})
    private String mobile;

    public interface Create {}
}
