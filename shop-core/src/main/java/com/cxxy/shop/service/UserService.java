package com.cxxy.shop.service;

import com.cxxy.shop.repository.UserRepository;
import com.cxxy.shop.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getUserList() throws Exception{
        return  userRepository.findAll();
    }

    public Long addUser(User user) throws Exception{
        userRepository.save(user);
        return user.getUser_id();
    }

    public Page<User> getUserList(Example<User> example, int pageNum, int pageSize) throws Exception{
        return  userRepository.findAll(example,new PageRequest(pageNum,pageSize));
    }
}
