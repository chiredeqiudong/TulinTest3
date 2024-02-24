package com.it.web;

import com.alibaba.fastjson.JSON;
import com.it.pojo.*;
import com.it.service.EmployeeService;
import com.it.service.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;


/**
 * @author zy293
 * EmployeeServlet:EmployeeWeb层方法处理
 */
@WebServlet("/employee/*")
public class EmployeeServlet extends MyHttpServlet {
    /**
     * employeeService:EmployeeService的实现类对象(多态？)
     */
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
        String username = tempEmployee.getUsername();
        String password = tempEmployee.getPassword();
        String role = tempEmployee.getRole();
        // 数据库查询
        Employee employee = employeeService.loginSelect(username, password);
        if (Objects.isNull(employee)) {
            //输入的账号密码错误
            resp.getWriter().write("error");
        } else {
            //输入正确
            //cookie设置
            String judge = "true";
            if (judge.equals(checked)) {
                //创建cookie
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);
                //三天时间
                cookie1.setMaxAge(60 * 60 * 24 * 3);
                cookie2.setMaxAge(60 * 60 * 24 * 3);
                //发送Cookie
                resp.addCookie(cookie1);
                resp.addCookie(cookie2);
            }
            //会话存储
            HttpSession session = req.getSession();
            session.setAttribute("employee", employee);
        }
    }

    /**
     * firstPage:首页请求
     */
    public void firstPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用firstPage方法");
        //获取会话技术
        HttpSession session = req.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        //转为json
        String jsonString = JSON.toJSONString(employee);
        //响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 修改数据
     */
    public void polishInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用polishInfo方法");
        //读取json数据
        BufferedReader reader = req.getReader();
        String infoJson = reader.readLine();
        Employee employee = JSON.parseObject(infoJson, Employee.class);
        employeeService.updateInfo(employee);
        //覆盖会话
        HttpSession session = req.getSession();
        session.setAttribute("employee", employee);
        //响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 薪资信息
     */
    public void showSalary(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showSalary方法");
        //获取员工id
        String employeeId = req.getParameter("employeeId");
        int parseInt = Integer.parseInt(employeeId);
        //获取pageBean数据
        String jsonPage = req.getReader().readLine();
        Page page =  JSON.parseObject(jsonPage,Page.class);
        if (Objects.isNull(page.getDateValue())){
            //如果为null，全部查询，——>空字符串：
            page.setDateValue("");
        }
        //service
        PageBean<Salary> salaryPageBean = employeeService.showSalary(parseInt, page);
        //转为Json响应
        String jsonString = JSON.toJSONString(salaryPageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 根据员工id查找数据;
     */
    public void idSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用idSelect方法");
        //获取员工id
        String employeeId = req.getParameter("employeeId");
        int parseInt = Integer.parseInt(employeeId);
        Employee employee = employeeService.idSelect(parseInt);
        //转为json数据响应
        String jsonString = JSON.toJSONString(employee);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 考勤信息查询
     */
    public void showAttendance(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showAttendance方法");
        //获取员工id、currentPage、
        String employeeId = req.getParameter("employeeId");
        String currentPage = req.getParameter("currentPage");
        int parseInt1 = Integer.parseInt(employeeId);
        int parseInt2 = Integer.parseInt(currentPage);
        String jsonStr = req.getReader().readLine();
        String[] stringArray = JSON.parseObject(jsonStr, String[].class);
        if (stringArray.length == 0){
            stringArray = new String[3];
            stringArray[0] = "迟到";
            stringArray[1] = "早退";
            stringArray[2] = "缺勤";
        }
        //如果为空数组数据
        System.out.println("选中的数组值："+ Arrays.toString(stringArray));
        //service
        PageBean<Attendance> attendancePageBean = employeeService.showAttendance(parseInt1,parseInt2,stringArray);
        //转为Json响应
        String jsonString = JSON.toJSONString(attendancePageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 请假信息查询
     */
    public void showLeave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showLeave方法");
        //获取员工id、reason、page
        String employeeId = req.getParameter("employeeId");
        int parseInt = Integer.parseInt(employeeId);
        String tempReason = req.getParameter("reason");
        //编码、解码
        byte[] reasonBytes = tempReason.getBytes(StandardCharsets.ISO_8859_1);
        String reason = new String(reasonBytes,StandardCharsets.UTF_8);
        String jsonPage = req.getReader().readLine();
        Page page =  JSON.parseObject(jsonPage,Page.class);
        if (Objects.isNull(page.getDateValue())){
            //如果为null，全部查询，——>空字符串：
            page.setDateValue("");
        }
        //service
        PageBean<Leave> leavePageBean = employeeService.showLeave(parseInt, reason, page);
        //转为Json响应
        String jsonString = JSON.toJSONString(leavePageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 请假申请信息查询
     */
    public void showUnLeave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showUnLeave方法");
        //获取员工id、reason、page
        String employeeId = req.getParameter("employeeId");
        int parseInt = Integer.parseInt(employeeId);
        String tempReason = req.getParameter("reason");
        //编码、解码
        byte[] reasonBytes = tempReason.getBytes(StandardCharsets.ISO_8859_1);
        String reason = new String(reasonBytes,StandardCharsets.UTF_8);
        String jsonPage = req.getReader().readLine();
        Page page =  JSON.parseObject(jsonPage,Page.class);
        if (Objects.isNull(page.getDateValue())){
            //如果为null，全部查询，——>空字符串：
            page.setDateValue("");
        }
        //service
        PageBean<Leave> leavePageBean = employeeService.showUnLeave(parseInt, reason, page);
        //转为Json响应
        String jsonString = JSON.toJSONString(leavePageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 撤销请假申请
     */
    public void deleteLeave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用deleteLeave方法");
        //获取员工请假申请id主键
        String id = req.getParameter("id");
        int parseInt = Integer.parseInt(id);
        //service
        employeeService.deleteByLeaveId(parseInt);
        //转为Json响应
        resp.getWriter().write("success");
    }

    /**
     * 添加请假申请
     */
    public void addLeave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用addLeave方法");
        //json
        String jsonLeave = req.getReader().readLine();
        Leave leave = JSON.parseObject(jsonLeave, Leave.class);
        System.out.println( "ygid:" + leave.getEmployeeId());
        if (leave.getEmployeeId() != 0 && leave.getEmployeeId() != null){
            //设置审核状态为未处理
            leave.setLeaveStatus("未处理");
            //service
            employeeService.addLeave(leave);
            //转为Json响应
            resp.getWriter().write("success");
        }
        else {
            //数据不完善
            resp.getWriter().write("error");
        }


    }






}
