package com.cxxy.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cxxy.shop.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    @Query("SELECT u FROM User u WHERE u.user_name like CONCAT('%',:userName,'%')")
    Page<User> getUserList(@Param("userName") String userName, Pageable pageable);

}
