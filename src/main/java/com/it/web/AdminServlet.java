package com.it.web;

import com.alibaba.fastjson.JSON;
import com.it.pojo.*;
import com.it.service.AdminService;
import com.it.service.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

/**
 * @author zy293
 * AdminServlet:Admin层方法处理
 */

@WebServlet("/admin/*")
public class AdminServlet extends MyHttpServlet {
    /**
     * adminService:AdminService的实现类对象
     */
    private final AdminService adminService = new AdminServiceImpl();

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
        if (announcement.getWriter().isEmpty() || announcement.getContent().isEmpty() || announcement.getTitle().isEmpty()){
            resp.getWriter().write("error");
        }
        else {
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
        if (announcement.getWriter().isEmpty() || announcement.getContent().isEmpty() || announcement.getTitle().isEmpty()){
            resp.getWriter().write("error");
        }
        else {
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
        if (announcesId.length == 0){
            resp.getWriter().write("error");
        }
        else {
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
        if ( train.getTrainName().isEmpty() || train.getTrainLocation().isEmpty() || train.getStartDate() == null || train.getEndDate() == null){
            resp.getWriter().write("error");
        }
        else {
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
        Train train = JSON.parseObject(jsonTrain,Train.class);
        //service
        if ( train.getTrainName().isEmpty() || train.getTrainLocation().isEmpty() || train.getStartDate() == null || train.getEndDate() == null){
            resp.getWriter().write("error");
        }
        else {
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
        if (trainsId.length == 0){
            resp.getWriter().write("error");
        }
        else {
            //service
            adminService.deleteTrains(trainsId);
            resp.getWriter().write("success");
        }

    }






}
