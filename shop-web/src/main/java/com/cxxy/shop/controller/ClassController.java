package com.cxxy.shop.controller;

import com.cxxy.shop.bean.SClass;
import com.cxxy.shop.bean.User;
import com.cxxy.shop.param.ClassParam;
import com.cxxy.shop.param.UserParam;
import com.cxxy.shop.response.Response;
import com.cxxy.shop.service.ClassService;
import com.cxxy.shop.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:liuhui
 * Description:
 * Date: 下午10:09 2017/12/14
 */
@RestController
@RequestMapping(value = "/class")
public class ClassController extends BaseController{

    @Autowired
    private ClassService classService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@Validated(ClassParam.Create.class) @RequestBody ClassParam classParam) throws Exception {
        Map<String, Object> result = new HashMap<>();

        SClass sClass = new SClass();
        BeanUtils.copyProperties(classParam,sClass);

        Long classId = classService.addClass(sClass);

        result.put("status", "ok");
        result.put("id", classId);

        return Response.toResponse(result);
    }



    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@Validated(ClassParam.Modify.class) @RequestBody ClassParam classParam) throws Exception {
        Map<String, Object> result = new HashMap<>();

        SClass sClass = new SClass();
        BeanUtils.copyProperties(classParam,sClass);

        classService.updateClass(sClass);

        result.put("status", "ok");

        return Response.toResponse(result);
    }


    @RequestMapping(value = "allList")
    public Object getAllList() throws Exception{

        List<Map<String, Object>> list = classService.getAllList();

        return Response.toResponse(list);
    }
}
