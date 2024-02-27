package com.it.mapper;

import com.it.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zy293
 * EmployeeMapper:员工mapper
 */
public interface EmployeeMapper {
    /**
     * loginSelect:查询登录账户
     *
     * @param username : 用户名
     * @param password : 登录密码
     * @return : 该员工数据
     */
    Employee loginSelect(@Param("username") String username, @Param("password") String password);

    /**
     * updateInfo:修改个人信息
     *
     * @param employee : 员工信息
     */
    void updateInfo(Employee employee);

    /**
     * updatePassword:修改个人密码
     * @param employeeId : 员工id
     * @param newPassword : 新密码
     */
    void updatePassword(@Param("employeeId") int employeeId,@Param("newPassword") int newPassword);

    /**
     * salarySelect:薪资信息查询
     * @param employeeId : 员工id
     * @param begin:开始索引
     * @param page:      当前页码和查询日期;
     * @return : 员工薪资数据
     */
    List<Salary> showSalary(@Param("employeeId") int employeeId, @Param("begin") int begin, @Param("page") Page page);

    /**
     * idSelect:员工数据查询
     *
     * @param employeeId : 员工id
     * @return : 薪资数据
     */
    Employee idSelect(int employeeId);

    /**
     * selectCount:薪资数目查询
     *
     * @param employeeId:员工id
     * @param page:           当前页码和查询日期;
     * @return : 数据个数
     */
    int selectCount(@Param("employeeId") int employeeId, @Param("page") Page page);

    /**
     * showAttendance:薪资信息查询
     *
     * @param employeeId:       员工id
     * @param begin:开始索引
     * @param attendanceValues: 查询目标值
     * @return : 员工考勤
     */
    List<Attendance> showAttendance(@Param("employeeId") int employeeId, @Param("attendanceValues") String[] attendanceValues, @Param("begin") int begin);

    /**
     * attendanceCount:考勤信息
     *
     * @param employeeId:       员工id
     * @param attendanceValues: 查询目标值
     * @return : 考勤数目
     */
    int attendanceCount(@Param("employeeId") int employeeId, @Param("attendanceValues") String[] attendanceValues);

    /**
     * showLeave:请假信息
     *
     * @param employeeId: 员工id
     * @param begin:      索引
     * @param page        : 时间值
     * @param reason      : 请假理由
     * @return : 请假数据
     */
    List<Leave> showLeave(@Param("employeeId") int employeeId, @Param("begin") int begin, @Param("page") Page page, @Param("reason") String reason);

    /**
     * leaveCount:请假数目
     *
     * @param employeeId: 员工id
     * @param begin:      索引
     * @param page        : 时间值
     * @param reason      : 请假理由
     * @return : 请假数目
     */
    int leaveCount(@Param("employeeId") int employeeId, @Param("begin") int begin, @Param("page") Page page, @Param("reason") String reason);

    /**
     * showLeave:请假信息
     *
     * @param employeeId: 员工id
     * @param begin:      索引
     * @param page        : 时间值
     * @param reason      : 请假理由
     * @return : 请假数据
     */
    List<Leave> showUnLeave(@Param("employeeId") int employeeId, @Param("begin") int begin, @Param("page") Page page, @Param("reason") String reason);

    /**
     * leaveCount:请假数目
     *
     * @param employeeId: 员工id
     * @param begin:      索引
     * @param page        : 时间值
     * @param reason      : 请假理由
     * @return : 请假数目
     */
    int unLeaveCount(@Param("employeeId") int employeeId, @Param("begin") int begin, @Param("page") Page page, @Param("reason") String reason);

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
     * @param begin:      索引
     * @param reason      : 离职理由
     * @return : 离职数据
     */
    List<Quit> showQuit(@Param("employeeId") int employeeId, @Param("begin") int begin, @Param("reason") String reason);

    /**
     * quitCount:离职查询
     * @param employeeId: 员工id
     * @param begin:      索引
     * @param reason      : 离职理由
     * @return : 离职数目
     */
    int quitCount(@Param("employeeId") int employeeId, @Param("begin") int begin, @Param("reason") String reason);

    /**
     * addQuit : 添加离职申请
     * @param quit : 离职
     * */
    void addQuit(Quit quit);

    /**
     * deleteQuit : 删除数据
     * @param quitId : 离职主键数组
     * */
    void deleteQuit(@Param("quitId") int[] quitId);

    /**
     * showTrain:培训信息
     * @param employeeId: 员工id
     * @param begin:      索引
     * @param trainName : 培训名称
     * @return : 培训数据
     */
    List<Score> showTrain(@Param("employeeId") int employeeId, @Param("begin") int begin, @Param("trainName") String trainName);
    /**
     * trainCount:培训信息数
     * @param employeeId: 员工id
     * @param begin:      索引
     * @param trainName : 培训名称
     * @return : 培训信息数
     */
    int trainCount(@Param("employeeId") int employeeId, @Param("begin") int begin, @Param("trainName") String trainName);

    /**
     * showTrain:培训信息
     * @param begin:  索引
     * @param trainNames : 培训名称
     * @return : 培训数据
     */
    List<Train> showTrains(@Param("begin") int begin, @Param("trainNames") String trainNames);
    /**
     * trainCount:培训信息数
     * @param begin:      索引
     * @param trainNames : 培训名称
     * @return : 培训信息数
     */
    int trainsCount(@Param("begin") int begin, @Param("trainNames") String trainNames);

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

