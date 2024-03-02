package com.it.web;

import com.alibaba.fastjson.JSON;
import com.it.pojo.*;
import com.it.service.EmployeeService;
import com.it.service.EmployeeServiceImpl;
import com.it.util.Regex;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * @author zy293
 * EmployeeServlet:EmployeeWeb层方法处理
 */
@WebServlet("/employee/*")
@MultipartConfig
public class EmployeeServlet extends MyHttpServlet {
    /**
     * employeeService:EmployeeService的实现类对象(多态？)
     */
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private String url;
    private boolean flag;
    /**
     * login:员工登录验证
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //json:员工账号、密码、是否需要cookie、session数据
        BufferedReader reader = req.getReader();
        String employeeJson = reader.readLine();
        Employee tempEmployee = JSON.parseObject(employeeJson, Employee.class);
        String checked = req.getParameter("checked");
        String username = tempEmployee.getUsername();
        String password = tempEmployee.getPassword();
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
        //读取json数据
        BufferedReader reader = req.getReader();
        String infoJson = reader.readLine();
        Employee employee = JSON.parseObject(infoJson, Employee.class);
        if (flag){
            employee.setAvatar(url);
            flag = false;
        }
        if (Regex.phoneCheck(employee.getPhone()) && Regex.emailCheck(employee.getEmail()) && Regex.userNameCheck(employee.getUsername()) && Regex.genderCheck(employee.getGender())){
            employeeService.updateInfo(employee);
            //覆盖会话
            HttpSession session = req.getSession();
            session.setAttribute("employee", employee);
            //响应成功标识
            resp.getWriter().write("success");
        }
        else {
            resp.getWriter().write("error");
        }

    }

    /**
     * 薪资信息
     */
    public void showSalary(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        //json
        String jsonLeave = req.getReader().readLine();
        Leave leave = JSON.parseObject(jsonLeave, Leave.class);
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

    /**
     * 展示离职信息
     * */
    public void showQuit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //员工id、查询原因,当前页
        String employeeId = req.getParameter("employeeId");
        int parseInt = Integer.parseInt(employeeId);
        String quitReason = req.getParameter("quitReason");
        //编码、解码
        byte[] reasonBytes = quitReason.getBytes(StandardCharsets.ISO_8859_1);
        String reason = new String(reasonBytes,StandardCharsets.UTF_8);
        String jsonQuit = req.getReader().readLine();
        Page page = JSON.parseObject(jsonQuit, Page.class);
        int currentPage = page.getCurrentPage();
        //service
        PageBean<Quit> quitPageBean = employeeService.showQuit(parseInt, currentPage, reason);
        //json响应
        String jsonString = JSON.toJSONString(quitPageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 添加离职申请
     */
    public void addQuit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //json
        String jsonQuit = req.getReader().readLine();
        Quit quit = JSON.parseObject(jsonQuit, Quit.class);
        if (quit.getEmployeeId() != 0 && quit.getEmployeeId() != null){
            //设置审核状态为未处理
            quit.setQuitStatus("处理中");
            //service
            employeeService.addQuit(quit);
            //转为Json响应
            resp.getWriter().write("success");
        }
        else {
            //数据不完善
            resp.getWriter().write("error");
        }
    }

    /**
     * 撤销离职申请
     */
    public void deleteQuit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //主键数组
        String jsonQuit = req.getReader().readLine();
        int[] quitId = JSON.parseObject(jsonQuit, int[].class);
        if (quitId.length == 0){
            resp.getWriter().write("error");
        }
        else {
            //service
            employeeService.deleteQuit(quitId);
            resp.getWriter().write("success");
        }
    }

    /**
     * 已参加培训信息
     * */
    public void showTrain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //员工id、培训名称、currentPage
        String jsonPage = req.getReader().readLine();
        Page page = JSON.parseObject(jsonPage, Page.class);
        int currentPage = page.getCurrentPage();
        String employeeId = req.getParameter("employeeId");
        int parseInt = Integer.parseInt(employeeId);
        String tempTrainName = req.getParameter("trainName");
        if (tempTrainName == null){
            tempTrainName = "";
        }
        //编码、解码
        byte[] trainNameBytes = tempTrainName.getBytes(StandardCharsets.ISO_8859_1);
        String trainName = new String(trainNameBytes,StandardCharsets.UTF_8);
        //service
        PageBean<Score> scorePageBean = employeeService.showTrain(parseInt, currentPage, trainName);
        //json响应
        String jsonString = JSON.toJSONString(scorePageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 展示培训信息
     * */
    public void showTrains(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //培训名称、currentPage
        String jsonPage = req.getReader().readLine();
        Page page = JSON.parseObject(jsonPage, Page.class);
        int currentPage = page.getCurrentPage();
        String tempTrainName = req.getParameter("trainNames");
        if (tempTrainName == null){
            tempTrainName = "";
        }
        //编码、解码
        byte[] trainNameBytes = tempTrainName.getBytes(StandardCharsets.ISO_8859_1);
        String trainName = new String(trainNameBytes,StandardCharsets.UTF_8);
        //service
        PageBean<Train> trainPageBean = employeeService.showTrains(currentPage, trainName);
        //json响应
        String jsonString = JSON.toJSONString(trainPageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 添加培训
     * */
    public void addTrain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Json数据
        String jsonTrain = req.getReader().readLine();
        Score score = JSON.parseObject(jsonTrain, Score.class);
        //service
        employeeService.addTrain(score);
        //json响应
        resp.getWriter().write("success");
    }

    /**
     * 判断是否参加过培训
     */
    public void judgeTrain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Json数据
        String tempEmployeeId = req.getParameter("employeeId");
        String tempTrainId = req.getParameter("trainId");
        int trainId = Integer.parseInt(tempTrainId);
        int employeeId = Integer.parseInt(tempEmployeeId);
        //service
        Score score = employeeService.judgeTrain(employeeId, trainId);
        if (Objects.isNull(score)){
            resp.getWriter().write("success");
        }
    }

    /**
     * 修改密码
     * */
    public void polishPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Json数据
        String tempEmployeeId = req.getParameter("employeeId");
        int employeeId = Integer.parseInt(tempEmployeeId);
        String tempNewPassword = req.getParameter("newPassword");
        int newPassword = Integer.parseInt(tempNewPassword);
        //service
        employeeService.updatePassword(employeeId,newPassword);
        resp.getWriter().write("success");
    }

    /**
     * 退出登录
     * */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
     * 公告信息
     * */
    public void showAnnouncement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //service
        List<Announcement> announcements = employeeService.showAnnouncement(0, 10);
        String jsonString = JSON.toJSONString(announcements);
        //响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
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
