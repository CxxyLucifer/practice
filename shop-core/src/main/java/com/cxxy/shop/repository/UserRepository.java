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

    @Query("SELECT u.user_id as key," +
            "u.user_name as user_name," +
            "u.class_id as class_id," +
            "sc.class_name as class_name  " +
            "from User u LEFT JOIN u.sClass sc where u.user_id = :user_id"
    )
    Map<String, Object> getById(@Param("user_id")Long user_id);

    //SELECT u FROM User u LEFT JOIN FETCH u.emails e WHERE e.address LIKE'%.%@openhome.cc'
    @Query("SELECT u.user_id as key," +
            "u.user_name as user_name," +
            "u.class_id as class_id," +
            "sc.class_name as class_name  " +
            "FROM User u LEFT JOIN u.sClass sc " +
            "WHERE (:userName is null or u.user_name like CONCAT('%',:userName,'%')) " +
            "and (:classId is null or sc.class_id = :classId) " +
            "and (:className is null or sc.class_name = :className) " +
            "order by u.user_id asc"
    )
    Page<Map<String, Object>> getUserList(@Param("userName") String userName,
                                          @Param("classId") Long classId,
                                          @Param("className") String className,
                                          Pageable pageable);


}
