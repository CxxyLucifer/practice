package com.cxxy.practice.repository;

import com.cxxy.practice.bean.SClass;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Author:liuhui
 * Description:
 * Date: 下午4:56 2017/11/20
 */
@Repository
public interface ClassRepository extends JpaRepository<SClass,Long>{

    @Query("SELECT c.class_id as orgId," +
            "c.class_name as orgName," +
            "'0' as pid," +
            "'0' as isLeaf," +
            "1 as depth " +
            "from SClass c"
    )
    List<Map<String, Object>> getAllList();


    @Modifying
    @Query("update SClass c set c.class_name = :className where c.class_id = :classId")
    void update(@Param("className") String className,@Param("classId") Long classId);
}
