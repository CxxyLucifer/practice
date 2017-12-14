package com.cxxy.shop.controller;

import com.cxxy.shop.response.Response;
import com.cxxy.shop.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping(value = "allList")
    public Object getAllList() throws Exception{

        List<Map<String, Object>> list = classService.getAllList();

        return Response.toResponse(list);
    }
}
