package com.cxxy.shop.controller;

import com.cxxy.shop.bean.User;
import com.cxxy.shop.builder.ResponseBuilder;
import com.cxxy.shop.param.UserParam;
import com.cxxy.shop.redis.RedisManager;
import com.cxxy.shop.response.Response;
import com.cxxy.shop.service.UserService;
import com.cxxy.shop.util.MD5Util;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "创建用户", notes = "根据UserParam对象创建用户")
    @ApiImplicitParam(name = "userParam", value = "用户请求实体类", required = true, dataType = "UserParam")
    @RequestMapping(value = "/addUsers", method = RequestMethod.POST)
    public Object addUsers(@Validated(UserParam.Create.class) @RequestBody UserParam userParam) throws Exception {
        Map<String, Object> result = new HashMap<>();

        User user = new User();
        user.setUser_name(userParam.getUserName());
        user.setPassword(MD5Util.md5Hex("A123456"));
        user.setMobile(userParam.getMobile());

        Long userId = userService.addUser(user);

        result.put("status", "ok");
        result.put("id", userId);

        return Response.toResponse(result);
    }


    /**
     * 根据用户名和班级查询UserList
     * <p>
     * http://127.0.0.1:8080/user/getUserListByName?pageNum=2&UserName=&className=
     *
     * @param userParam
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询用户列表", notes = "分页查询用户列表，支持姓名、班级搜索")
    @ApiImplicitParam(name = "userParam", value = "用户请求实体类", required = true, dataType = "UserParam")
    @RequestMapping(value = "getUserList", method = RequestMethod.POST)
    public Object getUserListByName(@Validated(UserParam.Query.class) @RequestBody UserParam userParam) throws Exception {

        if (StringUtils.isBlank(userParam.getClassName())) {
            userParam.setClassName(null);
        }

        if (StringUtils.isBlank(userParam.getUserName())) {
            userParam.setUserName(null);
        }
        Page<Map<String, Object>> page = userService.getUserList(userParam.getUserName(), userParam.getClass_id(), userParam.getClassName(), userParam.getPageNum(), userParam.getPageSize());

        return ResponseBuilder.toPageResponse(page);
    }


    /**
     * 查询用户详情
     * <p>
     * http://127.0.0.1:8080/user/getUserById?user_id=11
     *
     * @param user_id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询用户详情", notes = "查询用户详情")
    @ApiImplicitParam(name = "user_id", value = "用户编号", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "getUserById/{user_id}", method = RequestMethod.GET)
    public Object getUserById(@PathVariable Long user_id) throws Exception {

        Map<String, Object> user = userService.getById(user_id);

        return Response.toResponse(user);
    }
}
