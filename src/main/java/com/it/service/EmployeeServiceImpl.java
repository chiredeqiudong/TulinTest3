package com.it.service;


import com.it.mapper.EmployeeMapper;
import com.it.pojo.*;
import com.it.util.SqlSessionFactoryUtil;
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


}