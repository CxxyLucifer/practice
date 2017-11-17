package com.cxxy.shop.service;

import com.cxxy.shop.repository.UserRepository;
import com.cxxy.shop.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUserList() throws Exception{
        return  userRepository.findAll();
    }
}
