package com.cxxy.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cxxy.shop.bean.User;

import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT new User(u.user_id,u.user_name,u.class_id,u.sClass) from User u where u.user_id = :user_id")
    User getById(@Param("user_id")Long user_id);


    @Query("SELECT u.user_id as user_id," +
            "u.user_name as user_name," +
            "u.class_id as class_id," +
            "c.class_name as class_name " +
            "FROM User u,SClass c " +
            "WHERE u.class_id = c.class_id " +
            "and (:userName is null or u.user_name like CONCAT('%',:userName,'%')) " +
            "and (:className is null or c.class_name = :className) " +
            "order by u.user_id asc"
    )
    Page<Map<String, Object>> getUserList(@Param("userName") String userName,
                                          @Param("className") String className,
                                          Pageable pageable);


}
