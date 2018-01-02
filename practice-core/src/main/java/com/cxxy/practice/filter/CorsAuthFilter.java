package com.cxxy.practice.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:liuhui
 * Description:
 * Date: 下午6:07 2017/11/28
 */
@WebFilter(filterName = "CorsAuthFilter", urlPatterns = {"/user/*", "/class/*"})
public class CorsAuthFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String origin = request.getHeader("Origin");
        String auth = request.getHeader("Authorization");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", StringUtils.defaultIfBlank(origin, "*"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Platform, Authorization, Origin, X-Requested-With, Content-Type, Accept");

        if (StringUtils.isNotEmpty(auth)) {
            chain.doFilter(req, res);
        } else {
            PrintWriter out = response.getWriter();

            JSONObject json = new JSONObject();
            json.put("unauthorize", true);
            json.put("message", "非法请求");

            out.append(json.toJSONString());
        }
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}
