package com.cxxy.practice.util;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:liuhui
 * Description:
 * Date: 下午5:55 2017/12/25
 */
public class JWTUtil {

    public static String token(Map<String, Object> claims, Date tokenExpired, String jwtKey) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenExpired)
                .signWith(SignatureAlgorithm.HS512, jwtKey)
                .compact();
    }

    public static Claims getClaims(String token, String jwtKey) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public static void main(String[] args) {
        //token过期时间 1小时
        Date tokenExpired = new Date(new Date().getTime() + 60 * 60 * 1 * 1000);

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userName", "userName");

        String token = JWTUtil.token(claims, tokenExpired, "liuhui1990");

//        System.out.println(token);

        Claims claims1 = getClaims("eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyTmFtZSI6InVzZXJOYW1lIiwiZXhwIjoxNTE0MjAwMTQ0fQ.lo6sd5PQfkdN349vV2R7XrFo9bNgmoZdV0MgFP6FkDixeHgeCbqMm0sAuoG0sPAh8fMs4ns8c7_ZalBlmJgwPQ","liuhui1990");

        System.out.println(claims1.get("userName"));
    }
}
