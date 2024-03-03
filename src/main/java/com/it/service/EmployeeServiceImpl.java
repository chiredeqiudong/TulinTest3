package com.it.service;


import com.it.mapper.AdminMapper;
import com.it.mapper.EmployeeMapper;
import com.it.pojo.*;
import com.it.util.SqlSessionFactoryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author zy293
 * EmployeeServiceImpl:Employee实现类
 */
public class EmployeeServiceImpl implements EmployeeService {
    /**
     * sqlFactory:SqlSessionFactory的接口对象,用于创建sqlSession对象
     */
    private final SqlSessionFactory sqlFactory = SqlSessionFactoryUtil.getFactory();

    /**
     * loginSelect:登录
     * @param username:用户名
     * @param password:密码
     */
    @Override
    public Employee loginSelect(String username, String password) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.loginSelect(username, password);
        sqlSession.close();
        return employee;
    }

    /**
     * updateInfo:修改个人信息
     * @param employee : 员工信息
     */
    @Override
    public void updateInfo(Employee employee) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        mapper.updateInfo(employee);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * employeeCount : 判断是否重复
     * @param id       : 主键
     * @param username : 用户名
     * @param phone    : 电话
     * @param email    : 邮箱
     * @return 返回重复个数
     */
    @Override
    public int employeeCount(int id, String username, String phone, String email) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        int count = mapper.employeeCount(id,username,phone,email);
        sqlSession.close();
        return count;
    }

    /**
     * updatePassword:修改个人密码
     * @param employeeId  : 员工id
     * @param newPassword : 新密码
     */
    @Override
    public void updatePassword(int employeeId, int newPassword) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        mapper.updatePassword(employeeId,newPassword);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * idSelect:员工数据查询
     * @param employeeId : 员工id
     * @return : 员工数据
     */
    @Override
    public Employee idSelect(int employeeId) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.idSelect(employeeId);
        sqlSession.close();
        return employee;
    }

    /**
     * salarySelect:薪资信息查询
     * @param employeeId : 员工id
     * @param page       : 当前页码和查询日期;
     * @return : 员工薪资数据
     */
    @Override
    public PageBean<Salary> showSalary(int employeeId, Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //begin值、Date模糊
        int begin = (page.getCurrentPage() - 1) * 10;
        String dateValue = page.getDateValue();
        dateValue += "%";
        page.setDateValue(dateValue);
        //薪资数据、总记录数
        List<Salary> salaries = mapper.showSalary(employeeId, begin, page);
        int count = mapper.selectCount(employeeId, page);
        //封装为PageBean
        PageBean<Salary> salaryPageBean = new PageBean<>();
        salaryPageBean.setRows(salaries);
        salaryPageBean.setTollCount(count);
        sqlSession.close();
        return  salaryPageBean;
    }

    /**
     * showAttendance:薪资信息查询
     * @param employeeId       : 员工id
     * @param currentPage :     当前页
     * @param attendanceValues : 查询目标值
     * @return : 员工考勤
     */
    @Override
    public PageBean<Attendance> showAttendance(int employeeId,int currentPage,String[] attendanceValues) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //begin值、count、attendanceList
        int begin = (currentPage - 1) * 10;
        List<Attendance> attendanceList = mapper.showAttendance(employeeId,attendanceValues, begin);
        int count = mapper.attendanceCount(employeeId,attendanceValues);
        //封装为PageBean
        PageBean<Attendance> attendancePageBean = new PageBean<>();
        attendancePageBean.setRows(attendanceList);
        attendancePageBean.setTollCount(count);
        sqlSession.close();
        return  attendancePageBean;
    }

    /**
     * showLeave:员工请假查询
     * @param employeeId : 员工id
     * @param reason     : 请假事由
     * @param page       : 当前页码和查询日期;
     * @return : 员工请假数据
     */
    @Override
    public PageBean<Leave> showLeave(int employeeId, String reason, Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //处理数据 begin、dateValue、reason
        int begin = (page.getCurrentPage() - 1) * 10;
        String dateValue = page.getDateValue() + "%";
        page.setDateValue(dateValue);
        if (reason!=null && !reason.isEmpty()){
            reason="%" + reason + "%";
        }else {
            //reason:""
            reason = "%";
        }
        List<Leave> leaveList = mapper.showLeave(employeeId, begin, page, reason);
        int leaveCount = mapper.leaveCount(employeeId, begin, page, reason);
        //封装PageBean
        PageBean<Leave> leavePageBean = new PageBean<>();
        leavePageBean.setTollCount(leaveCount);
        leavePageBean.setRows(leaveList);
        sqlSession.close();
        return leavePageBean;
    }

    /**
     * showLeave:员工请假未处理查询
     * @param employeeId : 员工id
     * @param reason     : 请假事由
     * @param page       : 当前页码和查询日期;
     * @return : 员工请假数据
     */
    @Override
    public PageBean<Leave> showUnLeave(int employeeId, String reason, Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //处理数据 begin、dateValue、reason
        int begin = (page.getCurrentPage() - 1) * 10;
        String dateValue = page.getDateValue() + "%";
        page.setDateValue(dateValue);
        if (reason!=null && !reason.isEmpty()){
            reason="%" + reason + "%";
        }else {
            //reason:""
            reason = "%";
        }
        List<Leave> leaveList = mapper.showUnLeave(employeeId, begin, page, reason);
        int leaveCount = mapper.unLeaveCount(employeeId, begin, page, reason);
        //封装PageBean
        PageBean<Leave> leavePageBean = new PageBean<>();
        leavePageBean.setTollCount(leaveCount);
        leavePageBean.setRows(leaveList);
        sqlSession.close();
        return leavePageBean;
    }

    /**
     * deleteByLeaveId : 删除数据
     * @param id : 请假表id
     */
    @Override
    public void deleteByLeaveId(int id) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        mapper.deleteByLeaveId(id);
        //提交
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * addLeave : 添加请假数据
     * @param leave : 请假表
     */
    @Override
    public void addLeave(Leave leave) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        mapper.addLeave(leave);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * showQuit:离职信息
     * @param employeeId  : 员工id
     * @param currentPage : 当前页
     * @param reason      : 离职理由
     * @return : 离职数据
     */
    @Override
    public PageBean<Quit> showQuit(int employeeId, int currentPage, String reason) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //分页索引、模糊原因
        int begin = (currentPage - 1) * 10;
        if (reason!=null && !reason.isEmpty()){
            reason="%" + reason + "%";
        }else {
            //reason:""
            reason = "%";
        }
        List<Quit> quitList = mapper.showQuit(employeeId, begin, reason);
        int quitCount = mapper.quitCount(employeeId, begin, reason);
        PageBean<Quit> quitPageBean = new PageBean<>();
        quitPageBean.setRows(quitList);
        quitPageBean.setTollCount(quitCount);
        sqlSession.close();
        return quitPageBean;
    }

    /**
     * addQuit : 添加离职申请
     * @param quit : 离职
     */
    @Override
    public void addQuit(Quit quit) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        mapper.addQuit(quit);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * deleteQuit : 删除数据
     * @param quitId : 离职主键数组
     */
    @Override
    public void deleteQuit(int[] quitId) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        mapper.deleteQuit(quitId);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * showTrain:培训信息
     * @param employeeId  : 员工id
     * @param currentPage : 当前页
     * @param trainName   : 培训名称
     * @return : 培训数据
     */
    @Override
    public PageBean<Score> showTrain(int employeeId, int currentPage, String trainName) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //分页索引、模糊原因
        int begin = (currentPage - 1) * 10;
        if (trainName!=null && !trainName.isEmpty()){
            trainName ="%" + trainName + "%";
        }else {
            //trainName:""
            trainName = "%";
        }
        List<Score> scoreList = mapper.showTrain(employeeId, begin, trainName);
        int trainCount = mapper.trainCount(employeeId, begin, trainName);
        PageBean<Score> trainPageBean = new PageBean<>();
        trainPageBean.setRows(scoreList);
        trainPageBean.setTollCount(trainCount);
        sqlSession.close();
        return trainPageBean;
    }

    /**
     * showTrains:培训信息
     * @param currentPage :  当前页
     * @param trainNames  : 培训名称
     * @return : 培训数据
     */
    @Override
    public PageBean<Train> showTrains(int currentPage, String trainNames) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //分页索引、模糊原因
        int begin = (currentPage - 1) * 10;
        if (trainNames !=null && !trainNames.isEmpty()){
            trainNames ="%" + trainNames + "%";
        }else {
            //trainName:""
            trainNames = "%";
        }
        List<Train> trainList = mapper.showTrains(begin,trainNames);
        int trainCount = mapper.trainsCount(begin, trainNames);
        PageBean<Train> trainPageBean = new PageBean<>();
        trainPageBean.setRows(trainList);
        trainPageBean.setTollCount(trainCount);
        sqlSession.close();
        return trainPageBean;
    }

    /**
     * addTrain:添加培训成绩
     * @param score : 培训成绩表
     */
    @Override
    public void addTrain(Score score) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        mapper.addTrain(score);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * judgeTrain:判断是否有重复参加
     * @param employeeId : 员工id
     * @param trainId    : 培训ID
     * @return Score对象
     */
    @Override
    public Score judgeTrain(int employeeId, int trainId) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Score score = mapper.judgeTrain(employeeId, trainId);
        sqlSession.close();
        return score;
    }

    /**
     * showAnnouncement:查询公告信息
     * @param begin : 开始索引
     * @param size  : 数目
     * @return Announcement集合
     */
    @Override
    public List<Announcement> showAnnouncement(int begin, int size) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Announcement> announcements = mapper.showAnnouncement(begin, size);
        sqlSession.close();
        return announcements;
    }



}