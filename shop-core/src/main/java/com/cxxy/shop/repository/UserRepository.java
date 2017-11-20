package com.cxxy.shop.repository;

import com.cxxy.shop.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cxxy.shop.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    @Query("SELECT new com.cxxy.shop.dto.UserDto(u.user_id,u.user_name,u.class_id,c.class_name) FROM User u,Class c WHERE u.class_id = c.class_id " +
            "and (:userName is null or u.user_name like CONCAT('%',:userName,'%')) " +
            "and (:className is null or c.class_name = :className)")
    Page<UserDto> getUserList(@Param("userName") String userName, @Param("className") String className , Pageable pageable);


}
