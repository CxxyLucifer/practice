package com.cxxy.practice.controller;

import com.cxxy.practice.param.LoginParam;
import com.cxxy.practice.redis.RedisManager;
import com.cxxy.practice.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:liuhui
 * Description:
 * Date: 下午4:43 2017/12/25
 */
@ApiIgnore
@RestController
public class LoginController extends BaseController {

    @Autowired
    private RedisManager redisManager;

    @Value("${jwtKey}")
    private String jwtKey;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@Valid @RequestBody LoginParam loginParam) throws Exception {
        Map<String, Object> result = new HashMap<>();

        //token过期时间 1小时
        Date tokenExpired = new Date(new Date().getTime() + 60 * 60 * 1 * 1000);

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userName",loginParam.getUserName());

        String token = JWTUtil.token(claims, tokenExpired, jwtKey);

        result.put("status", "ok");
        result.put("token", token);
        redisManager.set(token, claims, 1 * 60 * 60);

        return result;
    }

}
