package com.cxxy.shop.service;

import com.cxxy.shop.bean.SClass;
import com.cxxy.shop.config.annotation.ReadDataSource;
import com.cxxy.shop.exception.CommonException;
import com.cxxy.shop.repository.ClassRepository;
import com.cxxy.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private UserRepository userRepository;

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
    @Transactional
    public void updateClass(SClass sclass) throws Exception {

        if(classRepository.exists(sclass.getClass_id())){
            classRepository.update(sclass.getClass_name(),sclass.getClass_id());
        }else{
            throw new CommonException("班级不存在");
        }
    }

    /**
     * 删除
     * @param classId
     * @throws Exception
     */
    public void delete(Long classId) throws Exception {

        if(classRepository.exists(classId)){
            Long total = userRepository.getUserCountByClassId(classId);
            if(total == 0){
                classRepository.delete(classId);
            }else {
                throw new CommonException("请先删除该班级下的学生");
            }
        }else{
            throw new CommonException("班级不存在");
        }
    }

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @ReadDataSource
    public List<Map<String, Object>> getAllList() throws Exception {

        return classRepository.getAllList();
    }

}
