package com.cxxy.practice.controller;

import com.cxxy.practice.bean.SClass;
import com.cxxy.practice.param.ClassParam;
import com.cxxy.practice.redis.RedisManager;
import com.cxxy.practice.response.Response;
import com.cxxy.practice.service.ClassService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class ClassController extends BaseController {

    @Autowired
    private ClassService classService;

    @Autowired
    private RedisManager redisManager;

    @ApiOperation(value = "创建班级", notes = "根据ClassParam对象创建班级")
    @ApiImplicitParam(name = "classParam", value = "班级请求实体类", required = true, dataType = "ClassParam")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@Validated(ClassParam.Create.class) @RequestBody ClassParam classParam) throws Exception {
        Map<String, Object> result = new HashMap<>();

        SClass sClass = new SClass();
        BeanUtils.copyProperties(classParam, sClass);

        Long classId = classService.addClass(sClass);

        result.put("status", "ok");
        result.put("id", classId);

        return Response.toResponse(result);
    }


    @ApiOperation(value = "编辑班级", notes = "根据ClassParam对象更新班级")
    @ApiImplicitParam(name = "classParam", value = "班级请求实体类", required = true, dataType = "ClassParam")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@Validated(ClassParam.Modify.class) @RequestBody ClassParam classParam) throws Exception {
        Map<String, Object> result = new HashMap<>();

        SClass sClass = new SClass();
        BeanUtils.copyProperties(classParam, sClass);

        classService.updateClass(sClass);

        result.put("status", "ok");

        return Response.toResponse(result);
    }

    @ApiOperation(value = "删除班级", notes = "根据ClassParam对象创建班级")
    @ApiImplicitParam(name = "class_id", value = "班级编号", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/delete/{class_id}", method = RequestMethod.GET)
    public Object delete(@PathVariable Long class_id) throws Exception {
        Map<String, Object> result = new HashMap<>();

        classService.delete(class_id);

        result.put("status", "ok");

        return Response.toResponse(result);
    }

    @ApiOperation(value = "查询所有班级", notes = "查询所有班级")
    @RequestMapping(value = "allList", method = RequestMethod.GET)
    public Object getAllList() throws Exception {
        List<Map<String, Object>> list = null;

        if (redisManager.get("allClassList") != null) {
            logger.info("================ allClassList from redis");
            list = (List<Map<String, Object>>) redisManager.get("allClassList");
        } else {
            list = classService.getAllList();
            redisManager.set("allClassList", list);
            logger.info("================ allClassList from db");
        }

        return Response.toResponse(list);
    }
}
