package com.cxxy.practice.filter;

import com.alibaba.fastjson.JSONObject;
import com.cxxy.practice.filter.wrapper.ResponseWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author:liuhui
 * Description: cors跨域请求处理、token过期处理、请求参数解密和返回值加密处理
 * Date: 下午6:07 2017/11/28
 */
@WebFilter(filterName = "CommonFilter", urlPatterns = {"/user/*", "/class/*"})
public class CommonFilter implements Filter {

    @Value("${jwtKey}")
    private String jwtKey;

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

            ResponseWrapper wrapperResponse = new ResponseWrapper(response);//转换成代理类

            chain.doFilter(request, wrapperResponse);

            byte[] content = wrapperResponse.getContent();//获取返回值

            if (content.length > 0) {
                String str = new String(content, "UTF-8");
                String ciphertext = null;
                try {
                    JSONObject json = JSONObject.parseObject(str);
                    json.put("token", "2806e7e624204664a8c4f45500ff5499");

                    ciphertext = json.toJSONString();

                } catch (Exception e) {
                    ciphertext = str;
                    e.printStackTrace();
                }
                ServletOutputStream out = response.getOutputStream();
                out.write(ciphertext.getBytes());
                out.flush();
            }
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
