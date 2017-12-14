package com.cxxy.shop.service;

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
     * 查询所有
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getAllList() throws Exception{
        return  classRepository.getAllList();
    }
}
