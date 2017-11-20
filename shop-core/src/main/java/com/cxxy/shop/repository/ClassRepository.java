package com.cxxy.shop.repository;

import com.cxxy.shop.bean.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author:liuhui
 * Description:
 * Date: 下午4:56 2017/11/20
 */
@Repository
public interface ClassRepository extends JpaRepository<Class,Long>{
}
