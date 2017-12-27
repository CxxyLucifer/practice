package com.cxxy.practice.param;

import com.cxxy.practice.form.BasicForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Author:liuhui
 * Description:
 * Date: 下午2:39 2017/12/15
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassParam extends BasicForm{

    @NotNull(message = "班级编号不能为空",groups = ClassParam.Modify.class)
    private Long class_id;

    @NotNull(message = "班级名称不能为空",groups = {ClassParam.Create.class,ClassParam.Modify.class})
    @Size(min = 4, max = 10, message = "班级名称4~10位",groups = {ClassParam.Create.class,ClassParam.Modify.class})
    private String class_name;

    public interface Create {}

    public interface Modify {}

    public interface Query {}

}
