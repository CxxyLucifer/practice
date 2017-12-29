package com.cxxy.practice.service;

import com.cxxy.practice.config.annotation.ReadDataSource;
import com.cxxy.practice.exception.CommonException;
import com.cxxy.practice.repository.UserRepository;
import com.cxxy.practice.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 新增
     *
     * @param user
     * @return
     * @throws Exception
     */
    public Long addUser(User user) throws Exception {
        userRepository.save(user);
        return user.getUser_id();
    }

    /**
     * 批量新增
     *
     * @param list
     * @return
     * @throws Exception
     */
    @Transactional
    public int[] batchAdd(List<User> list) throws Exception {

        int[] updatedCountArray = jdbcTemplate.batchUpdate("INSERT INTO t_user (user_name,password,mobile,class_id) VALUES (?,?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                User user = list.get(i);

                int index = 1;
                preparedStatement.setString(index++, user.getUser_name());
                preparedStatement.setString(index++, user.getPassword());
                preparedStatement.setString(index++, user.getMobile());
                preparedStatement.setLong(index++, user.getClass_id());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
        return updatedCountArray;
    }


    /**
     * 分页查询用户信息
     *
     * @param userName  用户名称，模糊匹配
     * @param className 班级名称，匹配
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @ReadDataSource
    public Page<Map<String, Object>> getUserList(String userName, Long classId, String className, int pageNum, int pageSize) throws Exception {
        return userRepository.getUserList(userName, classId, className, new PageRequest(pageNum, pageSize));
    }


    /**
     * 查询详情
     *
     * @param user_id
     * @return
     * @throws Exception
     */
    @ReadDataSource
    public Map<String, Object> getById(Long user_id) throws Exception {

        if (!userRepository.exists(user_id)) {
            throw new CommonException("用户不存在");
        }
        return userRepository.getById(user_id);
    }
}
