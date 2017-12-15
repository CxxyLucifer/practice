package com.cxxy.shop.service;

import com.cxxy.shop.bean.SClass;
import com.cxxy.shop.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author:liuhui
 * Description:
 * Date: 下午10:07 2017/12/14
 */
@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    /**
     * 新增
     * @param sclass
     * @return
     * @throws Exception
     */
    public Long addClass(SClass sclass) throws Exception {

        classRepository.save(sclass);
        return sclass.getClass_id();
    }


    /**
     * 编辑
     * @param sclass
     * @throws Exception
     */
    public void updateClass(SClass sclass)throws Exception {

        if(classRepository.exists(sclass.getClass_id())){
            classRepository.update(sclass.getClass_name(),sclass.getClass_id());
        }else{
            throw new RuntimeException("班级不存在");
        }
    }


    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getAllList() throws Exception {

        return classRepository.getAllList();
    }

}
