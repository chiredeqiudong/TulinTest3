package com.it.service;

import com.it.mapper.AdminMapper;
import com.it.pojo.*;
import com.it.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;


/**
 * @author zy293
 * AdminServiceImpl:Admin实现类
 */
public class AdminServiceImpl implements AdminService{
    /**
     * sqlFactory:SqlSessionFactory的接口对象,用于创建sqlSession对象
     */
    private final SqlSessionFactory sqlFactory = SqlSessionFactoryUtil.getFactory();


    /**
     * loginSelect:登录
     * @param username :用户名
     * @param password :密码
     * @return Admin对象
     */
    @Override
    public Admin loginSelect(String username, String password) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin = mapper.loginSelect(username, password);
        sqlSession.close();
        return admin;
    }

    /**
     * showAnnounce:查询公告数据
     * @param page :公告数据
     * @return : 公告数据
     */
    @Override
    public PageBean<Announcement> showAnnounce(Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //数据
        int begin = (page.getCurrentPage() - 1) * 10;
        String title = page.getAnnounceTitle();
        if (title!=null && !title.isEmpty()){
            title ="%" + title + "%";
        }else {
            title = "%";
        }
        //mapper
        List<Announcement> announcements = mapper.showAnnounce(begin, title);
        int announceCount = mapper.announceCount(begin, title);
        PageBean<Announcement> announcementPageBean = new PageBean<>();
        announcementPageBean.setTollCount(announceCount);
        announcementPageBean.setRows(announcements);
        sqlSession.close();
        return announcementPageBean;
    }

    /**
     * addAnnounce:添加公告数据
     * @param announcement : 公告
     */
    @Override
    public void addAnnounce(Announcement announcement) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.addAnnounce(announcement);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * deleteAnnounce:删除公告数据
     * @param id : 主键
     */
    @Override
    public void deleteAnnounce(int id) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.deleteAnnounce(id);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * selectAnnounce:数据回显
     * @param id : 主键
     * @return 公告数据
     */
    @Override
    public Announcement selectAnnounce(int id) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Announcement announcement = mapper.selectAnnounce(id);
        sqlSession.close();
        return announcement;
    }

    /**
     * updateAnnounce:修改数据
     * @param announcement : 公告数据
     */
    @Override
    public void updateAnnounce(Announcement announcement) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.updateAnnounce(announcement);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * deleteAnnounces:批量删除
     * @param announcesId : id数据
     */
    @Override
    public void deleteAnnounces(int[] announcesId) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.deleteAnnounces(announcesId);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * showTrain:查询培训数据
     * @param page :培训数据
     * @return : 培训数据
     */
    @Override
    public PageBean<Train> showTrain(Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //数据
        int begin = (page.getCurrentPage() - 1) * 10;
        String trainName = page.getTrainName();
        if (trainName!=null && !trainName.isEmpty()){
            trainName ="%" + trainName + "%";
        }else {
            trainName = "%";
        }
        //mapper
        List<Train> trainList = mapper.showTrain(begin, trainName);
        int trainCount = mapper.trainCount(begin, trainName);
        PageBean<Train> trainPageBean = new PageBean<>();
        trainPageBean.setTollCount(trainCount);
        trainPageBean.setRows(trainList);
        sqlSession.close();
        return trainPageBean;
    }

    /**
     * addTrain:添加培训数据
     * @param train : 培训
     */
    @Override
    public void addTrain(Train train) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.addTrain(train);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * deleteTrain:删除培训数据
     * @param id : 主键
     */
    @Override
    public void deleteTrain(int id) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.deleteTrain(id);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * selectTrain:数据回显
     * @param id : 主键
     * @return 培训数据
     */
    @Override
    public Train selectTrain(int id) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Train train = mapper.selectTrain(id);
        sqlSession.close();
        return train;
    }

    /**
     * updateTrain:修改数据
     * @param train : 培训数据
     */
    @Override
    public void updateTrain(Train train) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.updateTrain(train);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * deleteTrains:批量删除
     * @param trainsId : id数据
     */
    @Override
    public void deleteTrains(int[] trainsId) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.deleteTrains(trainsId);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * showEmployees:查询员工数据
     * @param page :员工数据
     * @return : 员工数据
     */
    @Override
    public PageBean<Employee> showEmployees(Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //数据
        int begin = (page.getCurrentPage() - 1) * 10;
        String employeeName = page.getEmployeeName();
        String department = page.getDepartment();
        String role = page.getRole();
        if (employeeName!=null && !employeeName.isEmpty()){
            employeeName ="%" + employeeName + "%";
        }else {
            employeeName = "%";
        }
        if (department!=null && !department.isEmpty()){
            department ="%" + department + "%";
        }else {
            department = "%";
        }
        if (role!=null && !role.isEmpty()){
            role ="%" + role + "%";
        }else {
            role = "%";
        }
        //mapper
        List<Employee> employeeList = mapper.showEmployees(begin, employeeName,department,role);
        int employeesCount = mapper.employeesCount(begin, employeeName,department,role);
        PageBean<Employee> employeePageBean = new PageBean<>();
        employeePageBean.setTollCount(employeesCount);
        employeePageBean.setRows(employeeList);
        sqlSession.close();
        return employeePageBean;
    }

    /**
     * showEmployees2:查询员工数据
     * @param page :员工数据
     * @return : 员工数据
     */
    @Override
    public PageBean<Employee> showEmployees2(Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //数据
        int begin = (page.getCurrentPage() - 1) * 10;
        String employeeName = page.getEmployeeName();
        String department = page.getDepartment();
        String role = page.getRole();
        if (employeeName == null ||  employeeName.isEmpty()) {
            employeeName = "";
        }
        if (department == null || department.isEmpty()){
            department = "";
        }
        if (role == null || role.isEmpty()) {
            role = "";
        }
        //mapper
        List<Employee> employeeList = mapper.showEmployees2(begin, employeeName,department,role);
        int employeesCount = mapper.employeesCount2(begin, employeeName,department,role);
        PageBean<Employee> employeePageBean = new PageBean<>();
        employeePageBean.setTollCount(employeesCount);
        employeePageBean.setRows(employeeList);
        sqlSession.close();
        return employeePageBean;
    }

    /**
     * selectEmployeeByData:查询员工数据
     * @param employee : 员工对象
     * @return : 员工
     */
    @Override
    public int selectEmployeeByData(Employee employee) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        int count = mapper.selectEmployeeByData(employee);
        sqlSession.close();
        return count;
    }

    /**
     * addEmployee:添加培员工数据
     * @param employee : 培训
     */
    @Override
    public void addEmployee(Employee employee) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.addEmployee(employee);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * selectEmployee:数据回显
     * @param id : 主键
     * @return 员工数据
     */
    @Override
    public Employee selectEmployee(int id) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Employee employee = mapper.selectEmployee(id);
        sqlSession.close();
        return employee;
    }

    /**
     * updateEmployee:修改数据
     * @param employee : 员工数据
     */
    @Override
    public void updateEmployee(Employee employee) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.updateEmployee(employee);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * deleteEmployees:批量删除
     * @param employeesId : id数据
     */
    @Override
    public void deleteEmployees(int[] employeesId) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.deleteEmployees(employeesId);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * leavesCount : 请假未处理的数据个数
     * @param page : 查询对象
     * @return 请假未处理的数据个数
     */
    @Override
    public PageBean<Leave> showLeaves(Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //数据
        int begin = (page.getCurrentPage() - 1) * 10;
        String reason = page.getReason();
        if (reason != null && !reason.isEmpty()) {
            reason  =  "%" + reason + "%";
        }else {
            reason = "%";
        }
        //mapper
        List<Leave> leaveList = mapper.showLeaves(begin,reason);
        int leavesCountCount = mapper.leavesCount(begin, reason);
        PageBean<Leave> leavePageBean = new PageBean<>();
        leavePageBean.setTollCount(leavesCountCount);
        leavePageBean.setRows(leaveList);
        sqlSession.close();
        return leavePageBean;
    }

    /**
     * judgeLeave : 请假处理
     * @param id          : id
     * @param leaveStatus : 审核结果
     */
    @Override
    public void judgeLeave(int id, String leaveStatus) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.judgeLeave(id,leaveStatus);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * showQuits : 离职未处理的数据
     * @param page : 查询对象
     * @return 离职未处理的数据个数
     */
    @Override
    public PageBean<Quit> showQuits(Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //数据
        int begin = (page.getCurrentPage() - 1) * 10;
        String department = page.getDepartment();
        if (department != null && !department.isEmpty()) {
            department  =  "%" + department+ "%";
        }else {
            department = "%";
        }
        //mapper
        List<Quit> quitList = mapper.showQuits(begin,department);
        int quitsCount = mapper.quitsCount(begin, department);
        PageBean<Quit> quitPageBean = new PageBean<>();
        quitPageBean.setTollCount(quitsCount);
        quitPageBean.setRows(quitList);
        sqlSession.close();
        return quitPageBean;
    }

    /**
     * judgeQuit : 离职处理
     * @param id           : id
     * @param quitStatus   : 审核结果
     * @param deleteStatus : 软删除结果
     */
    @Override
    public void judgeQuit(int id, String quitStatus, String deleteStatus) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.judgeQuit(id,quitStatus,deleteStatus);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * showScores : 培训成绩未处理的数据
     * @param page : 查询对象
     * @return 培训未打分的数据
     */
    @Override
    public PageBean<Score> showScores(Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //数据
        int begin = (page.getCurrentPage() - 1) * 10;
        String trainName = page.getTrainName();
        if (trainName != null && !trainName.isEmpty()) {
            trainName  =  "%" + trainName+ "%";
        }else {
            trainName = "%";
        }
        //mapper
        List<Score> scoreList = mapper.showScores(begin,trainName);
        int scoresCount = mapper.scoresCount(begin, trainName);
        PageBean<Score> scorePageBean = new PageBean<>();
        scorePageBean.setTollCount(scoresCount);
        scorePageBean.setRows(scoreList);
        sqlSession.close();
        return scorePageBean;

    }

    /**
     * updateScore : 打分
     * @param id    : id主键
     * @param score : 分数
     */
    @Override
    public void updateScore(int id, double score) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.updateScore(id,score);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * showAdminInfo : 查询管理员
     * @param id : id主键
     * @return 管理员数据
     */
    @Override
    public Admin showAdminInfo(int id) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin = mapper.showAdminInfo(id);
        sqlSession.close();
        return admin;
    }

    /**
     * updateAdminInfo : 修改管理员
     * @param admin : 管理员
     */
    @Override
    public void updateAdminInfo(Admin admin) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.updateAdminInfo(admin);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * adminCount : 判断用户名是否重复
     * @return 返回重复个数
     */
    @Override
    public int adminCount(int id,String username,String phone,String email) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        int count = mapper.adminCount(id,username,phone,email);
        sqlSession.close();
        return count;
    }

    /**
     * updateAdminPassword : 修爱密码
     * @param id            : 主键
     * @param checkPassword : 新密码
     */
    @Override
    public void updateAdminPassword(int id, String checkPassword) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.updateAdminPassword(id,checkPassword);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * showAttendances : 考勤设置
     * @param page : 查询条件
     * @return 员工数据
     */
    @Override
    public PageBean<Employee> showAttendances(Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        int begin = (page.getCurrentPage() - 1) * 10;
        String department = page.getDepartment();
        if (department != null && !department.isEmpty()) {
            department  =  "%" +department+ "%";
        }else {
            department = "%";
        }
        //mapper
        List<Employee> attendanceList = mapper.showAttendances(begin,department);
        int attendancesCount = mapper.attendanceCount(begin, department);
        PageBean<Employee> attendancePageBean = new PageBean<>();
        attendancePageBean.setTollCount(attendancesCount);
        attendancePageBean.setRows(attendanceList);
        sqlSession.close();
        return attendancePageBean;
    }

    /**
     * attendanceJudge : 考勤设置
     * @param attendancesId : 员工id
     * @param absenceRecord : 状态
     */
    @Override
    public void attendanceJudge(int[] attendancesId, String absenceRecord,String name) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //循环添加
        for (int id : attendancesId) {
            mapper.attendanceJudge(id, absenceRecord,name);
            sqlSession.commit();
        }
        sqlSession.close();
    }

    /**
     * showSalaries : 薪资处罚
     * @param page : 查询条件
     * @return 薪资数据
     */
    @Override
    public PageBean<Salary> showSalaries(Page page) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        int begin = (page.getCurrentPage() - 1) * 10;
        String department = page.getDepartment();
        if (department != null && !department.isEmpty()) {
            department  =  "%" +department+ "%";
        }else {
            department = "%";
        }
        //mapper
        List<Salary> salaryList = mapper.showSalaries(begin,department);
        int salariesCount = mapper.salariesCount(begin, department);
        PageBean<Salary> salaryPageBean = new PageBean<>();
        salaryPageBean.setTollCount(salariesCount);
        salaryPageBean.setRows(salaryList);
        sqlSession.close();
        return salaryPageBean;
    }

    /**
     * updateSalary:修改工资
     * @param id    ：主键
     * @param money ：工资
     * @param absenceRecord :考勤记录
     */
    @Override
    public void updateSalary(int id, double money,String absenceRecord) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        switch (absenceRecord) {
            case "迟到" -> money -= 30;
            case "早退" -> money -= 50;
            case "缺勤" -> money -= 100;
            default -> System.out.println("无考勤记录");
        }
        mapper.updateSalary(id,money);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * employeeCount : 判断是否重复
     * @param username : 用户名
     * @param phone    : 电话
     * @param email    : 邮箱
     * @return 返回重复个数
     */
    @Override
    public int employeeCount(String username, String phone, String email) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        int count = mapper.employeeCount(username,phone,email);
        sqlSession.close();
        return count;
    }

    /**
     * employeesCount : 判断用户名是否重复
     * @param id       : 主键
     * @param username : 用户名
     * @param phone    : 电话
     * @param email    : 邮箱
     * @return 返回重复个数
     */
    @Override
    public int employeesCount(int id, String username, String phone, String email) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        int count = mapper.employeeCounts(id,username,phone,email);
        sqlSession.close();
        return count;
    }

    /**
     * adminSelect:管理员数据
     * @param adminId : 管理员id
     * @return : 管理员
     */
    @Override
    public Admin adminSelect(int adminId) {
        SqlSession sqlSession = sqlFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin = mapper.adminSelect(adminId);
        sqlSession.close();
        return admin;
    }
}
