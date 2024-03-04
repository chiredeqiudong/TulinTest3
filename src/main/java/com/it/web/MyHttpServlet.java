package com.it.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zy293
 * 替换HttpServlet:service重写
 * 根据请求路径，进行方web方法的分发
 */
public class MyHttpServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取最后一段请求路径（方法名）
        String requestUri = req.getRequestURI();
        //截取字符串、获取最后斜杠索引
        int index = requestUri.lastIndexOf('/');
        //不最后包括斜杠，获取方法名
        String methodName = requestUri.substring(index + 1);
        Class<? extends MyHttpServlet> cls = this.getClass();
        try {
            //获取方法对象
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            System.out.println("方法名错误");
        } catch (InvocationTargetException e) {
            System.out.println("Servlet方法内部错误");
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            // throw new RuntimeException(e);
            System.out.println("错误3");
        }


    }
}