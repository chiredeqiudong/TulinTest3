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

}
