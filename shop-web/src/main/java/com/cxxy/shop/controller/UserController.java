package com.cxxy.shop.controller;

import com.cxxy.shop.bean.User;
import com.cxxy.shop.builder.ResponseBuilder;
import com.cxxy.shop.param.UserParam;
import com.cxxy.shop.response.Response;
import com.cxxy.shop.service.UserService;
import com.cxxy.shop.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUsers", method = RequestMethod.POST)
    public Object addUsers(@Validated(UserParam.Create.class) UserParam userParam) throws Exception{
        Map<String,Object> result = new HashMap<>();

        User user = new User();
        user.setUser_name(userParam.getUserName());
        user.setPassword(MD5Util.md5Hex("A123456"));
        Long userId = userService.addUser(user);

        result.put("status" , "ok");
        result.put("id" , userId);

        return Response.toResponse(result);
    }


    @RequestMapping(value = "getUserListByPage")
    public Object getUserListByPage(@Valid UserParam userParam) throws Exception{

        User user = new User();
        user.setUser_name(userParam.getUserName());
        ExampleMatcher matcher = ExampleMatcher.matchingAll();

        Example<User> example = Example.of(user,matcher);

        Page<User> page = userService.getUserList(example,userParam.getPageNum(),userParam.getPageSize());

        return ResponseBuilder.toPageResponse(page);
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
    @RequestMapping(value = "getUserListByName")
    public Object getUserListByName(@Validated(UserParam.Query.class) UserParam userParam) throws Exception{

        if(StringUtils.isBlank(userParam.getClassName())){
            userParam.setClassName(null);
        }
        if(StringUtils.isBlank(userParam.getUserName())){
            userParam.setUserName(null);
        }
        Page<Map<String, Object>> page = userService.getUserList(userParam.getUserName(),userParam.getClassName(),userParam.getPageNum(),userParam.getPageSize());

        return ResponseBuilder.toPageResponse(page);
    }



    /**
     * 根据用户名和班级查询UserList
     *
     * http://127.0.0.1:8080/user/getUserListByJoin?pageNum=2&UserName=&className=
     *
     * @param userParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getUserListByJoin")
    public Object getUserListByJoin(@Validated(UserParam.Query.class) UserParam userParam) throws Exception{

        if(StringUtils.isBlank(userParam.getClassName())){
            userParam.setClassName(null);
        }
        if(StringUtils.isBlank(userParam.getUserName())){
            userParam.setUserName(null);
        }
        Page<User> page = userService.getUserListByJoin(userParam.getUserName(),userParam.getClassName(),userParam.getPageNum(),userParam.getPageSize());

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
    @RequestMapping(value = "getUserById")
    public Object getUserById(@Validated(UserParam.Query.class) UserParam userParam) throws Exception{

        User user = userService.getById(userParam.getUser_id());

        return Response.toResponse(user);
    }
}
