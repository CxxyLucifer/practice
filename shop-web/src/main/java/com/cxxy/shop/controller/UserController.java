package com.cxxy.shop.controller;

import com.cxxy.shop.bean.User;
import com.cxxy.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllUser")
    public List<User> getAllUser() throws Exception{
        return userService.getUserList();
    }
}
