package com.it.service;


import com.it.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zy293
 * AdminService:Admin接口
 */
public interface AdminService {
    /**
     * loginSelect:登录
     * @param username:用户名
     * @param password:密码
     * @return Admin对象
     */
    Admin loginSelect(String username, String password);
    /**
     * adminSelect:管理员数据
     * @param adminId : 管理员id
     * @return : 管理员
     */
    Admin adminSelect(int adminId);

    /**
     * showAnnounce:查询公告数据
     * @param  page:公告数据
     * @return : 公告数据
     */
    PageBean<Announcement> showAnnounce(Page page);

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
    void deleteAnnounces(int[] announcesId);

    /**
     * showTrain:查询培训数据
     * @param  page:培训数据
     * @return : 培训数据
     */
    PageBean<Train> showTrain(Page page);

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
    void deleteTrains(int[] trainsId);

    /**
     * showEmployees:查询员工数据
     * @param  page:员工数据
     * @return : 员工数据
     */
    PageBean<Employee> showEmployees(Page page);
    /**
     * showEmployees2:查询员工数据
     * @param  page:员工数据
     * @return : 员工数据
     */
    PageBean<Employee> showEmployees2(Page page);

    /**
     * selectEmployeeByData:查询员工数据
     * @param employee : 员工对象
     * @return : 员工个数
     */
    int selectEmployeeByData(Employee employee);

    /**
     * addEmployee:添加培员工数据
     * @param employee : 培训
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
    void deleteEmployees(int[] employeesId);


    /**
     * leavesCount : 请假未处理的数据个数
     * @param page : 查询对象
     * @return 请假未处理的数据个数
     * */
    PageBean<Leave> showLeaves(Page page);

    /**
     * judgeLeave : 请假处理
     * @param id : id
     * @param leaveStatus : 审核结果
     * */
    void judgeLeave(int id,String leaveStatus);

    /**
     * showQuits : 离职未处理的数据
     * @param page : 查询对象
     * @return 离职未处理的数据个数
     * */
    PageBean<Quit> showQuits(Page page);

    /**
     * judgeQuit : 离职处理
     * @param id : id
     * @param quitStatus : 审核结果
     * @param deleteStatus : 软删除结果
     * */
    void judgeQuit(int id,String quitStatus,String deleteStatus);

    /**
     * showScores : 培训成绩未处理的数据
     * @param page : 查询对象
     * @return 培训未打分的数据
     * */
    PageBean<Score> showScores(Page page);


}
