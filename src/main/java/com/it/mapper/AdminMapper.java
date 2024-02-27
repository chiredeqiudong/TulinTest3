package com.it.mapper;

import com.it.pojo.Admin;
import com.it.pojo.Announcement;
import com.it.pojo.PageBean;
import com.it.pojo.Train;
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


}
