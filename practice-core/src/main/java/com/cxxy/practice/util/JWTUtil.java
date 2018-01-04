package com.cxxy.practice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString())
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
        Date tokenExpired = new Date(new Date().getTime() + 60 * 60);

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userName", "userName");

        String token = JWTUtil.token(claims, tokenExpired, "liuhui1990");

        System.out.println(token);

        Claims claims1 = JWTUtil.getClaims(token,"liuhui1990");

        System.out.println(claims1.toString());
    }
}
