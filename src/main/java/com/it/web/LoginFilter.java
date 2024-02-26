package com.it.web;


import com.it.pojo.Employee;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zy293
 * 登录拦截
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        String name = "login";
        //包含login的请求
        if (uri.contains(name)){
            filterChain.doFilter(req, resp);
            return;
        }
        //查看会话是否存在
        Employee employee = (Employee) req.getSession().getAttribute("employee");
        if (employee != null) {
            filterChain.doFilter(req, resp);
        } else {
            //重定向
            String path = req.getContextPath();
            resp.sendRedirect(path + "/login.html");
        }
    }
    @Override
    public void destroy() {
    }
}
