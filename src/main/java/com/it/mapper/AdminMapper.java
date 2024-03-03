package com.it.mapper;

import com.it.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zy293
 */
public interface AdminMapper {
    /**
     * loginSelect:查询登录账户
     * @param username : 用户名
     * @param password : 登录密码
     * @return : 该管理员数据
     */
    Admin loginSelect(@Param("username") String username, @Param("password") String password);

    /**
     * adminSelect:管理员数据
     * @param adminId : 管理员id
     * @return : 管理员
     */
    Admin adminSelect(int adminId);

    /**
     * showAnnounce:查询公告数据
     * @param begin : 开始索引
     * @param title : 标题
     * @return : 公告数据
     */
    List<Announcement> showAnnounce(@Param("begin") int begin, @Param("title") String title);

    /**
     * announceCount:查询公告数目
     * @param begin : 开始索引
     * @param title : 标题
     * @return : 公告数目
     */
    int announceCount(@Param("begin") int begin, @Param("title") String title);

    /**
     * addAnnounce:添加公告数据
     * @param announcement : 公告
     */
    void addAnnounce(Announcement announcement);

    /**
     * deleteAnnounce:删除公告数据
     * @param id : 主键
     */
    void deleteAnnounce(int id);

    /**
     * selectAnnounce:数据回显
     * @param id : 主键
     * @return 公告数据
     */
    Announcement selectAnnounce(int id);

    /**
     * updateAnnounce:修改数据
     * @param announcement : 公告数据
     */
    void updateAnnounce(Announcement announcement);

    /**
     * deleteAnnounces:批量删除
     * @param announcesId : id数据
     */
    void deleteAnnounces(@Param("announcesId") int[] announcesId);

    /**
     * showTrain:查询培训数据
     * @param begin : 开始索引
     * @param trainName : 培训名称
     * @return : 公告数据
     */
    List<Train> showTrain(@Param("begin") int begin, @Param("trainName") String trainName);

    /**
     * trainCount:查询培训数目
     * @param begin : 开始索引
     * @param trainName : 培训名称
     * @return : 培训数目
     */
    int trainCount(@Param("begin") int begin,@Param("trainName") String trainName);

    /**
     * addTrain:添加培训数据
     * @param train : 培训
     */
    void addTrain(Train train);

    /**
     * deleteTrain:删除培训数据
     * @param id : 主键
     */
    void deleteTrain(int id);

    /**
     * selectTrain:数据回显
     * @param id : 主键
     * @return 培训数据
     */
    Train selectTrain(int id);

    /**
     * updateTrain:修改数据
     * @param train : 培训数据
     */
    void updateTrain(Train train);

    /**
     * deleteTrains:批量删除
     * @param trainsId : id数据
     */
    void deleteTrains(@Param("trainsId") int[] trainsId);

    /**
     * showEmployees:查询员工数据
     * @param begin : 开始索引
     * @param employeeName : 员工姓名
     * @param department : 部门
     * @param role :职位
     * @return : 员工数据
     */
    List<Employee> showEmployees(@Param("begin") int begin, @Param("employeeName") String employeeName, @Param("department") String department, @Param("role") String role);

    /**
     * employeesCount:查询员工数据
     * @param begin : 开始索引
     * @param employeeName : 员工姓名
     * @param department : 部门
     * @param role :职位
     * @return : 员工个数
     */
    int employeesCount(@Param("begin") int begin, @Param("employeeName") String employeeName, @Param("department") String department, @Param("role") String role);

    /**
     * showEmployees2:查询员工数据
     * @param begin : 开始索引
     * @param employeeName : 员工姓名
     * @param department : 部门
     * @param role :职位
     * @return : 员工数据
     */
    List<Employee> showEmployees2(@Param("begin") int begin, @Param("employeeName") String employeeName, @Param("department") String department, @Param("role") String role);

    /**
     * employeesCount:查询员工数据
     * @param begin : 开始索引
     * @param employeeName : 员工姓名
     * @param department : 部门
     * @param role :职位
     * @return : 员工个数
     */
    int employeesCount2(@Param("begin") int begin, @Param("employeeName") String employeeName, @Param("department") String department, @Param("role") String role);

    /**
     * selectEmployeeByData:查询员工数据
     * @param employee : 员工对象
     * @return : 员工
     */
    int selectEmployeeByData(Employee employee);

    /**
     * addEmployee:添加培训数据
     * @param employee : 员工
     */
    void addEmployee(Employee employee);

    /**
     * selectEmployee:数据回显
     * @param id : 主键
     * @return 员工数据
     */
    Employee selectEmployee(int id);

    /**
     * updateEmployee:修改数据
     * @param employee : 员工数据
     */
    void updateEmployee(Employee employee);

    /**
     * deleteEmployees:批量删除
     * @param employeesId : id数据
     */
    void deleteEmployees(@Param("employeesId") int[] employeesId);

    /**
     * showLeaves : 展示请假未处理的数据
     * @param begin : 开始索引
     * @param reason : 请假原因
     * @return 展示请假未处理的数据
     * */
    List<Leave> showLeaves(@Param("begin") int begin,@Param("reason") String reason);

    /**
     * leavesCount : 请假未处理的数据个数
     * @param begin : 开始索引
     * @param reason : 请假原因
     * @return 请假未处理的数据个数
     * */
    int leavesCount(@Param("begin") int begin,@Param("reason") String reason);

    /**
     * judgeLeave : 请假处理
     * @param id : id
     * @param leaveStatus : 审核结果
     * */
    void judgeLeave(@Param("id") int id,@Param("leaveStatus") String leaveStatus);

    /**
     * showQuits : 展示离职未处理的数据
     * @param begin : 开始索引
     * @param department : 部门
     * @return 离职未处理的数据
     * */
    List<Quit> showQuits(@Param("begin") int begin,@Param("department") String department);

    /**
     * quitsCount : 展示离职未处理的数据
     * @param begin : 开始索引
     * @param department : 部门
     * @return 离职未处理的数据个数
     * */
    int quitsCount(@Param("begin") int begin,@Param("department") String department);

    /**
     * judgeQuit : 离职处理
     * @param id : id
     * @param quitStatus : 审核结果
     * @param deleteStatus : 软删除结果
     * */
    void judgeQuit(@Param("id") int id,@Param("quitStatus") String quitStatus,@Param("deleteStatus") String deleteStatus);

    /**
     * showScores : 展示培训成绩未设置(-1)的数据
     * @param begin : 开始索引
     * @param trainName : 培训名称
     * @return 为打分的的数据
     * */
    List<Score> showScores(@Param("begin") int begin,@Param("trainName") String trainName);

    /**
     * scoresCount : 展示培训成绩未设置(-1)的数据
     * @param begin : 开始索引
     * @param trainName : 培训名称
     * @return 为打分的的数据
     * */
    int scoresCount(@Param("begin") int begin,@Param("trainName") String trainName);

    /**
     * updateScore : 打分
     * @param id : id主键
     * @param score : 分数
     * */
    void updateScore(@Param("id") int id,@Param("score") double score);

    /**
     * showAdminInfo : 查询管理员
     * @param id : id主键
     * @return 管理员数据
     * */
    Admin showAdminInfo(int id);

    /**
     * updateAdminInfo : 修改管理员
     * @param admin : 管理员
     * */
    void updateAdminInfo(Admin admin);

    /**
     * adminCount : 判断用户名是否重复
     * @param username : 用户名
     * @param phone : 电话
     * @param email : 邮箱
     * @param id : 主键
     * @return  返回重复个数
     * */
    int adminCount(@Param("id") int id,@Param("username") String username,@Param("phone") String phone,@Param("email") String email);

    /**
     * updateAdminPassword : 修爱密码
     * @param id : 主键
     * @param checkPassword : 新密码
     * */
    void updateAdminPassword(@Param("id") int id,@Param("checkPassword") String checkPassword);

    /**
     * showAttendances : 考勤设置
     * @param begin : 开始索引
     * @param department : 部门
     * @return 员工数据
     * */
    List<Employee> showAttendances(@Param("begin") int begin,@Param("department") String department);

    /**
     * attendanceCount : 需要考勤的员工人数
     * @param begin : 开始索引
     * @param department : 部门
     * @return  返回个数
     * */
    int attendanceCount(@Param("begin") int begin,@Param("department") String department);

    /**
     * attendanceJudge : 考勤设置
     * @param attendancesId: 员工id
     * @param absenceRecord : 状态
     * @param name : 姓名
     * */
    void attendanceJudge(@Param("attendancesId") int attendancesId,@Param("absenceRecord") String absenceRecord,@Param("name") String name);

    /**
    * showSalaries:展示薪资信息
    * @param begin：开始索引
    * @param department：部门
    * @return 薪资数据
    * */
    List<Salary> showSalaries(@Param("begin") int begin,@Param("department") String department);

    /**
     * showSalaries:展示薪资信息
     * @param begin：开始索引
     * @param department：部门
     * @return 返回数据个数
     * */
    int salariesCount(@Param("begin") int begin,@Param("department") String department);

    /**
     * updateSalary:修改工资
     * @param id：主键
     * @param money：主键
     */
    void updateSalary(@Param("id") int id,@Param("money") double money);

    /**
     * adminCount : 判断用户名是否重复
     * @param username : 用户名
     * @param phone : 电话
     * @param email : 邮箱
     * @return  返回重复个数
     * */
    int employeeCount(@Param("username") String username,@Param("phone") String phone,@Param("email") String email);

    /**
     * employeeCounts : 判断用户名是否重复
     * @param username : 用户名
     * @param phone : 电话
     * @param email : 邮箱
     * @return  返回重复个数
     * */
    int employeeCounts(@Param("id") int id,@Param("username") String username,@Param("phone") String phone,@Param("email") String email);



}
