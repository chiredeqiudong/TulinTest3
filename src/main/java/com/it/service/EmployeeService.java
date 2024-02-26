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
     * updatePassword:修改个人密码
     * @param employeeId : 员工id
     * @param newPassword : 新密码
     */
    void updatePassword(int employeeId,int newPassword);

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

    /**
     * showQuit:离职信息
     * @param employeeId: 员工id
     * @param currentPage: 当前页
     * @param reason      : 离职理由
     * @return : 离职数据
     */
    PageBean<Quit>  showQuit(int employeeId,int currentPage,String reason);

    /**
     * addQuit : 添加离职申请
     * @param quit : 离职
     * */
    void addQuit(Quit quit);

    /**
     * deleteQuit : 删除数据
     * @param quitId : 离职主键数组
     * */
    void deleteQuit(int[] quitId);

    /**
     * showTrain:培训信息
     * @param employeeId: 员工id
     * @param currentPage: 当前页
     * @param trainName  : 培训名称
     * @return : 培训数据
     */
    PageBean<Score> showTrain(int employeeId,int currentPage,String trainName);

    /**
     * showTrains:培训信息
     * @param currentPage:  当前页
     * @param trainNames : 培训名称
     * @return : 培训数据
     */
    PageBean<Train> showTrains(int currentPage,String trainNames);

    /**
     * addTrain:添加培训成绩
     * @param score : 培训成绩表
     */
    void addTrain(Score score);

    /**
     * judgeTrain:判断是否有重复参加
     * @param employeeId : 员工id
     * @param trainId : 培训ID
     * @return Score对象
     */
    Score judgeTrain(@Param("employeeId") int employeeId,@Param("trainId") int trainId);

    /**
     * showAnnouncement:查询公告信息
     * @param begin : 开始索引
     * @param size : 数目
     * @return Announcement集合
     */
    List<Announcement> showAnnouncement(@Param("begin") int begin,@Param("size") int size);









}
