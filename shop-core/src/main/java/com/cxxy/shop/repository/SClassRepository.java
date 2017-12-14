package com.cxxy.shop.repository;

import com.cxxy.shop.bean.SClass;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Author:liuhui
 * Description:
 * Date: 下午4:56 2017/11/20
 */
@Repository
public interface SClassRepository extends JpaRepository<SClass,Long>{

    @Query("SELECT c.class_id as orgId," +
            "c.class_name as orgName," +
            "'1' as isLeaf," +
            "1 as depth " +
            "from SClass c"
    )
    List<Map<String, Object>> getAllList();
}
