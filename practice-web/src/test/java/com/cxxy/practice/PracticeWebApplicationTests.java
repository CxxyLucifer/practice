package com.cxxy.practice;

import com.cxxy.practice.bean.User;
import com.cxxy.practice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PracticeWebApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void getUserListTest() throws Exception {

//		Assert.isTrue(list.size() > 0,"getUserList 失败");
	}

}
