package com.it.web;

import com.alibaba.fastjson.JSON;
import com.it.pojo.*;
import com.it.service.AdminService;
import com.it.service.AdminServiceImpl;
import com.it.util.Regex;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @author zy293
 * AdminServlet:Admin层方法处理
 */

@WebServlet("/admin/*")
@MultipartConfig
public class AdminServlet extends MyHttpServlet {
    /**
     * adminService:AdminService的实现类对象
     */
    private final AdminService adminService = new AdminServiceImpl();
    private String url;
    private boolean flag;

    /**
     * login:管理员登录验证
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用login方法");
        //json:账号、密码、是否需要cookie、session数据
        BufferedReader reader = req.getReader();
        String adminJson = reader.readLine();
        Admin tempAdmin = JSON.parseObject(adminJson, Admin.class);
        String checked = req.getParameter("checked");
        String username = tempAdmin.getUsername();
        String password = tempAdmin.getPassword();
        // 数据库查询
        Admin admin = adminService.loginSelect(username, password);
        if (Objects.isNull(admin)) {
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
            session.setAttribute("admin", admin);
        }
    }

    /**
     * adminHome:首页请求
     */
    public void adminHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用adminHome方法");
        //获取会话技术
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        //转为json
        String jsonString = JSON.toJSONString(admin);
        //响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * adminSelect:查询admin数据
     */
    public void adminSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用adminSelect方法");
        //数据处理
        String adminId = req.getParameter("adminId");
        int parseInt = Integer.parseInt(adminId);
        //service
        Admin admin = adminService.adminSelect(parseInt);
        //转为json数据响应
        String jsonString = JSON.toJSONString(admin);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * showAnnounce:展示公告信息
     */
    public void showAnnounce(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showAnnounce方法");
        //数据处理
        String jsonAnnounce = req.getReader().readLine();
        Page page = JSON.parseObject(jsonAnnounce, Page.class);
        //service
        PageBean<Announcement> announcementPageBean = adminService.showAnnounce(page);
        //响应
        String jsonString = JSON.toJSONString(announcementPageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 添加公告数据
     */
    public void addAnnounce(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用addAnnounce方法");
        //json
        String jsonAnnounce = req.getReader().readLine();
        Announcement announcement = JSON.parseObject(jsonAnnounce, Announcement.class);
        if (announcement.getWriter().isEmpty() || announcement.getContent().isEmpty() || announcement.getTitle().isEmpty()) {
            resp.getWriter().write("error");
        } else {
            //service
            adminService.addAnnounce(announcement);
            resp.getWriter().write("success");
        }

    }

    /**
     * 删除公告
     */
    public void deleteAnnounce(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用deleteAnnounce方法");
        //数据
        String id = req.getParameter("id");
        int parseInt = Integer.parseInt(id);
        //service
        adminService.deleteAnnounce(parseInt);
        resp.getWriter().write("success");
    }

    /**
     * 数据回显
     */
    public void selectAnnounce(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用selectAnnounce方法");
        //数据
        String id = req.getParameter("id");
        int parseInt = Integer.parseInt(id);
        //service
        Announcement announcement = adminService.selectAnnounce(parseInt);
        //json
        String jsonString = JSON.toJSONString(announcement);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 修改数据
     */
    public void updateAnnounce(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用updateAnnounce方法");
        //json
        String jsonAnnounce = req.getReader().readLine();
        Announcement announcement = JSON.parseObject(jsonAnnounce, Announcement.class);
        //service
        if (announcement.getWriter().isEmpty() || announcement.getContent().isEmpty() || announcement.getTitle().isEmpty()) {
            resp.getWriter().write("error");
        } else {
            //service
            adminService.updateAnnounce(announcement);
            resp.getWriter().write("success");
        }
    }

    /**
     * 批量删除数据
     */
    public void deleteAnnounces(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用deleteAnnounces方法");
        //主键数组
        String jsonAnnounces = req.getReader().readLine();
        int[] announcesId = JSON.parseObject(jsonAnnounces, int[].class);
        if (announcesId.length == 0) {
            resp.getWriter().write("error");
        } else {
            //service
            adminService.deleteAnnounces(announcesId);
            resp.getWriter().write("success");
        }

    }

    /**
     * showTrain:展示培训信息
     */
    public void showTrain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showTrain方法");
        //数据处理
        String jsonTrain = req.getReader().readLine();
        Page page = JSON.parseObject(jsonTrain, Page.class);
        //service
        PageBean<Train> terainPageBean = adminService.showTrain(page);
        //响应
        String jsonString = JSON.toJSONString(terainPageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 添加培训数据
     */
    public void addTrain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用addTrain方法");
        //json
        String jsonTrain = req.getReader().readLine();
        Train train = JSON.parseObject(jsonTrain, Train.class);
        if (train.getTrainName().isEmpty() || train.getTrainLocation().isEmpty() || train.getStartDate() == null || train.getEndDate() == null) {
            resp.getWriter().write("error");
        } else {
            //service
            adminService.addTrain(train);
            resp.getWriter().write("success");
        }

    }

    /**
     * 删除培训
     */
    public void deleteTrain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用deleteTrain方法");
        //数据
        String id = req.getParameter("id");
        int parseInt = Integer.parseInt(id);
        //service
        adminService.deleteTrain(parseInt);
        resp.getWriter().write("success");
    }

    /**
     * 数据回显
     */
    public void selectTrain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用selectTrain方法");
        //数据
        String id = req.getParameter("id");
        int parseInt = Integer.parseInt(id);
        //service
        Train train = adminService.selectTrain(parseInt);
        //json
        String jsonString = JSON.toJSONString(train);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 修改数据
     */
    public void updateTrain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用updateTrain方法");
        //json
        String jsonTrain = req.getReader().readLine();
        Train train = JSON.parseObject(jsonTrain, Train.class);
        //service
        if (train.getTrainName().isEmpty() || train.getTrainLocation().isEmpty() || train.getStartDate() == null || train.getEndDate() == null) {
            resp.getWriter().write("error");
        } else {
            //service
            adminService.updateTrain(train);
            resp.getWriter().write("success");
        }
    }

    /**
     * 批量删除数据
     */
    public void deleteTrains(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用deleteTrains方法");
        //主键数组
        String jsonTrains = req.getReader().readLine();
        int[] trainsId = JSON.parseObject(jsonTrains, int[].class);
        if (trainsId.length == 0) {
            resp.getWriter().write("error");
        } else {
            //service
            adminService.deleteTrains(trainsId);
            resp.getWriter().write("success");
        }

    }

    /**
     * 展示员工信息
     */
    public void showEmployees(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showEmployees方法");
        //数据处理
        String jsonEmployee = req.getReader().readLine();
        Page page = JSON.parseObject(jsonEmployee, Page.class);
        String index = req.getParameter("index");
        int id = Integer.parseInt(index);
        //service
        if (id == 0) {
            PageBean<Employee> employeePageBean = adminService.showEmployees2(page);
            //响应
            String jsonString = JSON.toJSONString(employeePageBean);
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        } else {
            //默认模糊
            PageBean<Employee> employeePageBean = adminService.showEmployees(page);
            //响应
            String jsonString = JSON.toJSONString(employeePageBean);
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        }


    }

    /**
     * 添加数据验证
     */
    public void selectEmployeeByData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用selectEmployeeByData方法");
        //数据
        String readLine = req.getReader().readLine();
        Employee employee = JSON.parseObject(readLine, Employee.class);
        String index = req.getParameter("index");
        int parseInt = Integer.parseInt(index);
        int count = adminService.selectEmployeeByData(employee);
        //没有重复的数据
        boolean flag = count == 0;
        //service
        if (parseInt == 0) {
            if (flag) {
                resp.getWriter().write("add");
            } else {
                resp.getWriter().write("error");
            }
        }
        if (parseInt == 1) {
            resp.getWriter().write("update");
        }
    }

    /**
     * 注册员工数据
     */
    public void addEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用addEmployee方法");
        //json
        String jsonTrain = req.getReader().readLine();
        Employee employee = JSON.parseObject(jsonTrain, Employee.class);
        String phone = employee.getPhone();
        String email = employee.getEmail();
        String username = employee.getUsername();
        //数据格式
        if (Regex.userNameCheck(username) && Regex.phoneCheck(phone) && Regex.emailCheck(email)) {
            //判断重复
            int count = adminService.employeeCount(username, phone, email);
            if (count == 0) {
                //service
                employee.setPassword("123456");
                adminService.addEmployee(employee);
                resp.getWriter().write("success");
            }
        } else {
            resp.getWriter().write("error");
        }

    }

    /**
     * 数据回显
     */
    public void selectEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用selectEmployee方法");
        //数据
        String id = req.getParameter("id");
        int parseInt = Integer.parseInt(id);
        //service
        Employee employee = adminService.selectEmployee(parseInt);
        //json
        String jsonString = JSON.toJSONString(employee);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 修改数据
     */
    public void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用updateEmployee方法");
        //json
        String jsonEmployee = req.getReader().readLine();
        Employee employee = JSON.parseObject(jsonEmployee, Employee.class);
        String gender = employee.getGender();
        String phone = employee.getPhone();
        String email = employee.getEmail();
        String username = employee.getUsername();
        int id = employee.getId();
        //service
        if (Regex.phoneCheck(phone) && Regex.genderCheck(gender) &&  Regex.emailCheck(email) && Regex.userNameCheck(username)) {
            //判断重复
            int count = adminService.employeesCount(id,username, phone, email);
            if (count == 0) {
                adminService.updateEmployee(employee);
                resp.getWriter().write("success");
            } else {
                resp.getWriter().write("repetition");
            }

        } else {
            resp.getWriter().write("error");
        }
    }

    /**
     * 批量删除数据
     */
    public void deleteEmployees(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用deleteEmployees方法");
        //主键数组
        String jsonEmployees = req.getReader().readLine();
        int[] employeesId = JSON.parseObject(jsonEmployees, int[].class);
        if (employeesId.length == 0) {
            resp.getWriter().write("error");
        } else {
            //service
            adminService.deleteEmployees(employeesId);
            resp.getWriter().write("success");
        }

    }

    /**
     * 展示请假信息
     */
    public void showLeaves(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showLeaves方法");
        //数据处理
        String jsonLeaves = req.getReader().readLine();
        Page page = JSON.parseObject(jsonLeaves, Page.class);
        //service
        PageBean<Leave> leavePageBean = adminService.showLeaves(page);
        //响应
        String jsonString = JSON.toJSONString(leavePageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

    }

    /**
     * 请假审核
     */
    public void judgeLeave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用judgeLeave方法");
        //数据处理
        String parameter1 = req.getParameter("value");
        String parameter2 = req.getParameter("id");
        int value = Integer.parseInt(parameter1);
        int id = Integer.parseInt(parameter2);
        String leaveStatus = "驳回";
        if (value == 0) {
            //批准
            leaveStatus = "已批准";
        }
        //service
        adminService.judgeLeave(id, leaveStatus);
        //响应
        resp.getWriter().write("success");

    }

    /**
     * 展示离职申请
     */
    public void showQuits(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showQuits方法");
        //数据处理
        String jsonQuits = req.getReader().readLine();
        Page page = JSON.parseObject(jsonQuits, Page.class);
        //service
        PageBean<Quit> quitPageBean = adminService.showQuits(page);
        //响应
        String jsonString = JSON.toJSONString(quitPageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 离职审核
     */
    public void judgeQuit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用judgeQuit方法");
        //数据处理
        String parameter1 = req.getParameter("value");
        String parameter2 = req.getParameter("id");
        int value = Integer.parseInt(parameter1);
        int id = Integer.parseInt(parameter2);
        String quitStatus = "驳回";
        String deleteStatus = "在职";
        if (value == 0) {
            //离职
            quitStatus = "已批准";
            deleteStatus = "离职";
        }
        //service
        adminService.judgeQuit(id, quitStatus, deleteStatus);
        //响应
        resp.getWriter().write("success");

    }

    /**
     * 展示可以设置培训成绩的信息
     */
    public void showScores(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showScores方法");
        //数据处理
        String jsonScores = req.getReader().readLine();
        Page page = JSON.parseObject(jsonScores, Page.class);
        //service
        PageBean<Score> scorePageBean = adminService.showScores(page);
        //响应
        String jsonString = JSON.toJSONString(scorePageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 修改培训成绩的信息
     */
    public void updateScore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用updateScore方法");
        //数据处理
        String jsonScore = req.getReader().readLine();
        Score scoreTable = JSON.parseObject(jsonScore, Score.class);
        double score = scoreTable.getScore();
        int id = scoreTable.getId();
        //数据判断
        if (Regex.scoreCheck(score)) {
            //service
            adminService.updateScore(id, score);
            //响应
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("error");
        }

    }

    /**
     * 展示管理员的信息
     */
    public void showAdminInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showAdminInfo方法");
        //数据处理
        String parameter = req.getParameter("id");
        int id = Integer.parseInt(parameter);
        //service
        Admin admin = adminService.showAdminInfo(id);
        //响应
        String jsonString = JSON.toJSONString(admin);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 修改管理员的信息
     */
    public void updateAdminInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用updateAdminInfo方法");
        //数据处理
        String jsonAdmin = req.getReader().readLine();
        Admin admin = JSON.parseObject(jsonAdmin, Admin.class);
        String phone = admin.getPhone();
        String email = admin.getEmail();
        String username = admin.getUsername();
        int id = admin.getId();
        if (flag){
            admin.setAvatar(url);
            flag = false;
        }
        //service
        if (Regex.phoneCheck(phone) && Regex.emailCheck(email) && Regex.userNameCheck(username)) {
            //判断重复
            int count = adminService.adminCount(id,username, phone, email);
            if (count == 0) {
                adminService.updateAdminInfo(admin);
                //覆盖会话
                HttpSession session = req.getSession();
                session.setAttribute("admin", admin);
                resp.getWriter().write("success");
            } else {
                resp.getWriter().write("repetition");
            }

        } else {
            resp.getWriter().write("error");
        }
    }

    /**
     * 修改管理员密码
     */
    public void updateAdminPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用updateAdminPassword方法");
        //数据处理
        String jsonPassword = req.getReader().readLine();
        Admin admin = JSON.parseObject(jsonPassword, Admin.class);
        Integer id = admin.getId();
        String password = admin.getPassword();
        String checkPassword = admin.getCheckPassword();
        //数据判断
        if (password.equals(checkPassword) && !checkPassword.isEmpty()) {
            //service
            adminService.updateAdminPassword(id, checkPassword);
            //响应
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("error");
        }

    }

    /**
     * 退出登录
     */
    public void adminLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用adminLogout方法");
        // 清除认证信息,使当前会话无效
        req.getSession().invalidate();
        // 删除Cookie中的认证信息
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("authentication".equals(cookie.getName())) {
                    // 设置Cookie的最大生存时间为0，使其立即过期
                    cookie.setMaxAge(0);
                    // 将修改后的Cookie添加到响应中
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        resp.getWriter().write("success");
    }

    /**
     * 考勤展示
     */
    public void showAttendances(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showAttendances方法");
        //数据处理
        String jsonAttendances = req.getReader().readLine();
        Page page = JSON.parseObject(jsonAttendances, Page.class);
        //service
        PageBean<Employee> employeePageBean = adminService.showAttendances(page);
        //响应
        String jsonString = JSON.toJSONString(employeePageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 考勤设置
     */
    public void attendanceJudge(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用attendanceJudge方法");
        //数据处理
        String parameter = req.getParameter("value");
        String name = req.getParameter("name");
        int value = Integer.parseInt(parameter);
        byte[] nameBytes = name.getBytes(StandardCharsets.ISO_8859_1);
        String attendanceName = new String(nameBytes,StandardCharsets.UTF_8);
        String absenceRecord = "";
        switch (value) {
            case 0 -> absenceRecord = "全勤";
            case 1 -> absenceRecord = "迟到";
            case 2 -> absenceRecord = "早退";
            case 3 -> absenceRecord = "缺勤";
            default -> resp.getWriter().write("error");
        }
        String jsonAttendances = req.getReader().readLine();
        int[] attendancesId = JSON.parseObject(jsonAttendances, int[].class);
        if (attendancesId.length == 0) {
            resp.getWriter().write("length");
        } else {
            //service
            adminService.attendanceJudge(attendancesId, absenceRecord,attendanceName);
            //响应
            resp.getWriter().write("success");
        }

    }


    /**
     * 薪资处罚
     */
    public void showSalaries(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用showSalaries方法");
        //数据处理
        String jsonAttendances = req.getReader().readLine();
        Page page = JSON.parseObject(jsonAttendances, Page.class);
        //service
        PageBean<Salary> salaryPageBean = adminService.showSalaries(page);
        //响应
        String jsonString = JSON.toJSONString(salaryPageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 薪资处罚
     */
    public void updateSalary(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试是否调用该方法
        System.out.println("调用updateSalary方法");
        //数据处理
        String jsonSalary = req.getReader().readLine();
        Salary salary = JSON.parseObject(jsonSalary, Salary.class);
        double money = salary.getMoney();
        int id = salary.getId();
        String absenceRecord = salary.getAbsenceRecord();
        //service
        double basicMoney = 3100.0;
        if (money < basicMoney){
            resp.getWriter().write("error");
        }
        else {
            adminService.updateSalary(id,money,absenceRecord);
            resp.getWriter().write("success");
        }
    }

    /**头像上传*/
    public void uploadAvatar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 检查请求是否包含文件上传
        if (!ServletFileUpload.isMultipartContent(req)) {
            throw new ServletException("请求不包含文件上传");
        }
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && !formItems.isEmpty()) {
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = "F:\\java.code\\Test\\resource_app\\src\\main\\webapp\\img\\" + fileName;
                        File storeFile = new File(filePath);
                        if (!storeFile.exists()){
                            // 保存文件到硬盘
                            item.write(storeFile);
                        }
                        //生成该图片的URL
                        url = "http://localhost:8080/resource_app/img/"+fileName;
                        flag = true;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("捕捉到错误");
        }
    }

}

