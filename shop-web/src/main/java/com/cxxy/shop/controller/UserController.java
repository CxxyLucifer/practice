package com.cxxy.shop.controller;

import com.cxxy.shop.bean.User;
import com.cxxy.shop.builder.ResponseBuilder;
import com.cxxy.shop.param.UserParam;
import com.cxxy.shop.response.Response;
import com.cxxy.shop.service.UserService;
import com.cxxy.shop.util.MD5Util;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @ApiOperation(value="创建用户", notes="根据UserParam对象创建用户")
    @ApiImplicitParam(name="userParam",value = "用户请求实体类",required = true,dataType = "UserParam")
    @RequestMapping(value = "/addUsers", method = RequestMethod.POST)
    public Object addUsers(@Validated(UserParam.Create.class) @RequestBody UserParam userParam) throws Exception{
        Map<String,Object> result = new HashMap<>();

        User user = new User();
        user.setUser_name(userParam.getUserName());
        user.setPassword(MD5Util.md5Hex("A123456"));
        user.setMobile(userParam.getMobile());

        Long userId = userService.addUser(user);

        result.put("status" , "ok");
        result.put("id" , userId);

        return Response.toResponse(result);
    }


    /**
     * 根据用户名和班级查询UserList
     *
     * http://127.0.0.1:8080/user/getUserListByName?pageNum=2&UserName=&className=
     *
     * @param userParam
     * @return
     * @throws Exception
     */
    @ApiOperation(value="查询用户列表", notes="分页查询用户列表，支持姓名、班级搜索")
    @ApiImplicitParam(name="userParam",value = "用户请求实体类",required = true,dataType = "UserParam")
    @RequestMapping(value = "getUserList",method = RequestMethod.POST)
    public Object getUserListByName(@Validated(UserParam.Query.class) @RequestBody UserParam userParam) throws Exception{

        if(StringUtils.isBlank(userParam.getClassName())){
            userParam.setClassName(null);
            //throw new CommonException("参数异常");
        }

        if(StringUtils.isBlank(userParam.getUserName())){
            userParam.setUserName(null);
        }
        Page<Map<String, Object>> page = userService.getUserList(userParam.getUserName(),userParam.getClass_id(),userParam.getClassName(),userParam.getPageNum(),userParam.getPageSize());

        return ResponseBuilder.toPageResponse(page);
    }


    /**
     * 查询用户详情
     *
     * http://127.0.0.1:8080/user/getUserById?user_id=11
     *
     * @param userParam
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询用户详情",notes= "查询用户详情")
    @ApiImplicitParam(name="user_id",value = "用户编号",required = true,dataType = "Long")
    @RequestMapping(value = "getUserById",method = RequestMethod.GET)
    public Object getUserById(@Validated(UserParam.Query.class) UserParam userParam) throws Exception{

        Map<String, Object> user = userService.getById(userParam.getUser_id());

        return Response.toResponse(user);
    }
}
