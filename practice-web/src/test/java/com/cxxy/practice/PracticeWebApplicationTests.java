package com.cxxy.practice;

import com.cxxy.practice.bean.User;
import com.cxxy.practice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PracticeWebApplicationTests {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void getUserListTest() throws Exception {
//		Assert.isTrue(list.size() > 0,"getUserList 失败");
    }

    @Test
    public void AsyncTaskTest() throws Exception {
        for (int i = 0; i < 1000000; i++) {
            asyncTask.doTask1(i);
        }

        logger.info("All tasks finished");
    }

}
