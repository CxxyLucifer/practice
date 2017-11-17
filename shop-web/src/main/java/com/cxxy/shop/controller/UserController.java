package com.cxxy.shop.controller;

import com.cxxy.shop.bean.User;
import com.cxxy.shop.service.UserService;
import com.cxxy.shop.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> getAllUser() throws Exception{
        return userService.getUserList();
    }


    @RequestMapping(value = "/addUsers")
    public Object addUsers() throws Exception{
        Map<String,Object> result = new HashMap<>();

        for(int i = 21;i< 25;i++){
            User user = new User();
            user.setUser_name("name" + i);
            user.setPassword(MD5Util.md5Hex("A123456"));

            Long userId = userService.addUser(user);

            result.put("name" + i , userId);
        }
        return result;
    }
}
