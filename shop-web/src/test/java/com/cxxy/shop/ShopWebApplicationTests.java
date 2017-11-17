package com.cxxy.shop;

import com.cxxy.shop.bean.User;
import com.cxxy.shop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopWebApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void getUserListTest() throws Exception {
		List<User> list = userService.getUserList();

		Assert.isTrue(list.size() > 0,"getUserList 失败");
	}

}
