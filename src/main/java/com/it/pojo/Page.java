package com.it.pojo;

/**
 * @author zy293
 * dateValue:当前日期
 * currentPage：当前页码
 * announceTitle:公告标题
 * trainName:培训名称
*/
public class Page {
   private String dateValue;
   private int currentPage;
   private String announceTitle;
   private String trainName;
    public String getAnnounceTitle() {
        return announceTitle;
    }
    public void setAnnounceTitle(String announceTitle) {
        this.announceTitle = announceTitle;
    }
    public String getDateValue() {
        return dateValue;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}

