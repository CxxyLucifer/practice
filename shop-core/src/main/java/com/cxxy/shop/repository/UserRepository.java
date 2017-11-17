package com.cxxy.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cxxy.shop.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
