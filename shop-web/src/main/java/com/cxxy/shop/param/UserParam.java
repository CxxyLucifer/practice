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

    private Long user_id;

    @NotNull(message = "用户名不能为空",groups = {Create.class,Modify.class})
    @Size(min = 4, max = 20, message = "用户名必须大于4小于20",groups = {Create.class,Modify.class})
    private String userName;

    private String password;

    @Pattern(regexp = ValidUtil.MOBILE, message = "手机号码格式不正确", groups ={Create.class,Modify.class})
    private String mobile;

    private String class_id;

    private String className;

    public interface Create {}
    public interface Modify {}
    public interface Query {}
}
