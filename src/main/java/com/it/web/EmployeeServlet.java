package com.it.web;

import com.alibaba.fastjson.JSON;
import com.it.pojo.Employee;
import com.it.service.EmployeeService;
import com.it.service.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;


/**
 * @author zy293
 * EmployeeServlet:EmployeeWeb层方法处理
 */
@WebServlet("/employee/*")
public class EmployeeServlet extends MyHttpServlet {
    /**
     * employeeService:EmployeeService的实现类对象(多态？)
     * */
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    /**
     * login:员工登录验证
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用login方法");
        //json:员工账号、密码、是否需要cookie、session数据
        BufferedReader reader = req.getReader();
        String employeeJson = reader.readLine();
        Employee tempEmployee = JSON.parseObject(employeeJson, Employee.class);
        String checked = req.getParameter("checked");
        String account = tempEmployee.getAccount();
        String password = tempEmployee.getPassword();
        // 数据库查询
        Employee employee = employeeService.loginSelect(account, password);
        if (Objects.isNull(employee)){
            //输入的账号密码错误
            resp.getWriter().write("error");
        }
        else {
            //输入正确
            //cookie设置
            String judge = "true";
            if (judge.equals(checked)){
                //创建cookie
                Cookie cookie1 = new Cookie("account",account);
                Cookie cookie2 = new Cookie("password",password);
                //三天时间
                cookie1.setMaxAge(60*60*24*3);
                cookie2.setMaxAge(60*60*24*3);
                //发送Cookie
                resp.addCookie(cookie1);
                resp.addCookie(cookie2);
            }
            //登录成功
            resp.getWriter().write("success");
            //请求转发\重定向employee数据
        }




    }

}
