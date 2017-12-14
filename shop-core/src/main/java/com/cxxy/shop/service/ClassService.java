package com.cxxy.shop.service;

import com.cxxy.shop.repository.SClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private SClassRepository  classRepository;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getAllList() throws Exception{
        return  classRepository.getAllList();
    }
}
