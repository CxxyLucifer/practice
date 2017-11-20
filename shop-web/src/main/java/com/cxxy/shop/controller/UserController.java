package com.cxxy.shop.controller;

import com.cxxy.shop.bean.User;
import com.cxxy.shop.builder.ResponseBuilder;
import com.cxxy.shop.param.UserParam;
import com.cxxy.shop.response.Response;
import com.cxxy.shop.service.UserService;
import com.cxxy.shop.util.MD5Util;
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

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public Object getAllUser() throws Exception{
        List<User> users = userService.getUserList();

        return Response.toListResponse(users);
    }

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


    @RequestMapping(value = "getUserListByPage", method = RequestMethod.POST)
    public Object getUserListByPage(@Valid UserParam userParam) throws Exception{

        User user = new User();
        user.setUser_name(userParam.getUserName());
        ExampleMatcher matcher = ExampleMatcher.matchingAll();

        Example<User> example = Example.of(user,matcher);

        Page<User> page = userService.getUserList(example,userParam.getPageNum(),userParam.getPageSize());

        return ResponseBuilder.toPageResponse(page);
    }


    /**
     * http://127.0.0.1:8080/user/getUserListByName?pageNum=1&UserName=name 直接调用
     *
     * @param userParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getUserListByName", method = RequestMethod.GET)
    public Object getUserListByName(@Valid UserParam userParam) throws Exception{

        Page<User> page = userService.getUserList(userParam.getUserName(),userParam.getPageNum(),userParam.getPageSize());

        return ResponseBuilder.toPageResponse(page);
    }
}
