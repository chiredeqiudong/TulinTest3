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

    /**a
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
