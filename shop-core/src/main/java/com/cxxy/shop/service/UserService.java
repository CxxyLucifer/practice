package com.cxxy.shop.service;

import com.cxxy.shop.repository.UserRepository;
import com.cxxy.shop.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long addUser(User user) throws Exception{
        userRepository.save(user);
        return user.getUser_id();
    }

    public Page<User> getUserList(Example<User> example, int pageNum, int pageSize) throws Exception{
        return  userRepository.findAll(example,new PageRequest(pageNum,pageSize));
    }

    /**
     * 分页查询用户信息
     * @param userName  用户名称，模糊匹配
     * @param className 班级名称，匹配
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Page<Map<String, Object>> getUserList(String userName, String className, int pageNum, int pageSize) throws Exception{
        return  userRepository.getUserList( userName, className, new PageRequest( pageNum, pageSize));
    }


    /**
     * 查询详情
     * @param user_id
     * @return
     * @throws Exception
     */
    public User getById(Long user_id) throws Exception{
        return userRepository.getById(user_id);
    }
}
