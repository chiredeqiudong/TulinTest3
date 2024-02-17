package com.it.pojo;

import java.sql.Date;

/**
 * @author zy293
 * train:培训表
 * id:主键
 * trainName:培训名称
 * trainLocation:培训地点
 * description:培训描述
 * startDate:培训开始时间
 * endDate:培训结束时间
 */
public class Train {
    private Integer id;
    private String trainName;
    private String trainLocation;
    private String description;
    private Date startDate;
    private Date endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainLocation() {
        return trainLocation;
    }

    public void setTrainLocation(String trainLocation) {
        this.trainLocation = trainLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
