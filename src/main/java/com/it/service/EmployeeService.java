package com.it.service;

import com.it.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zy293
 * EmployeeService:Employee接口
 */
public interface EmployeeService {
    /**
     * loginSelect:登录
     * @param username:用户名
     * @param password:密码
     * @return Employee对象
     */
    Employee loginSelect(String username,String password);
    /**
     * updateInfo:修改个人信息
     * @param employee : 员工信息
     */
    void updateInfo(Employee employee);

    /**
     * salarySelect:薪资信息查询
     * @param employeeId : 员工id
     * @param page: 当前页码和查询日期;
     * @return : 员工薪资数据
     */
    PageBean<Salary> showSalary(int employeeId, Page page);

    /**
     * idSelect:员工数据查询
     * @param employeeId : 员工id
     * @return : 员工数据
     */
    Employee idSelect(int employeeId);

    /**
     * showAttendance:薪资信息查询
     * @param employeeId: 员工id
     * @param currentPage :     当前页
     * @param attendanceValues: 查询目标值
     * @return : 员工考勤
     */
    PageBean<Attendance> showAttendance(int employeeId,int currentPage,String[] attendanceValues);

    /**
     * showLeave:员工请假查询
     * @param employeeId : 员工id
     * @param page: 当前页码和查询日期;
     * @param reason : 请假事由
     * @return : 员工请假数据
     */
    PageBean<Leave> showLeave(int employeeId,String reason,Page page);

    /**
     * showLeave:员工请假未处理查询
     * @param employeeId : 员工id
     * @param page: 当前页码和查询日期;
     * @param reason : 请假事由
     * @return : 员工请假数据
     */
    PageBean<Leave> showUnLeave(int employeeId,String reason,Page page);

    /**
     * deleteByLeaveId : 删除数据
     * @param id : 请假表id
     * */
    void deleteByLeaveId(int id);

    /**
     * addLeave : 添加请假数据
     * @param leave : 请假表
     * */
    void addLeave(Leave leave);









}
