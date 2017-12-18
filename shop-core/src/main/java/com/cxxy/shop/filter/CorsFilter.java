package com.cxxy.shop.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author:liuhui
 * Description:
 * Date: 下午6:07 2017/11/28
 */
@Component
public class CorsFilter implements Filter{

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest)req;

        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", StringUtils.defaultIfBlank(origin,"*"));
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
