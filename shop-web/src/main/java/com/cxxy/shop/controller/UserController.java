package com.cxxy.shop.controller;

import com.cxxy.shop.bean.User;
import com.cxxy.shop.builder.ResponseBuilder;
import com.cxxy.shop.response.Response;
import com.cxxy.shop.service.UserService;
import com.cxxy.shop.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllUser")
    public Object getAllUser() throws Exception{
        List<User> users = userService.getUserList();

        return Response.toListResponse(users);
    }

    @RequestMapping(value = "/addUsers")
    public Object addUsers() throws Exception{
        Map<String,Object> result = new HashMap<>();

        for(int i = 26;i< 30;i++){
            User user = new User();
            user.setUser_name("name" + i);
            user.setPassword(MD5Util.md5Hex("A123456"));

            Long userId = userService.addUser(user);

            result.put("name" + i , userId);
        }
        return Response.toResponse(result);
    }


    @RequestMapping(value = "getUserListByPage")
    public Object getUserListByPage() throws Exception{

        User user = new User();

        ExampleMatcher matcher = ExampleMatcher.matchingAll();

        Example<User> example = Example.of(user,matcher);

        Page<User> page = userService.getUserList(example,1,5);

        return ResponseBuilder.toPageResponse(page);
    }
}
