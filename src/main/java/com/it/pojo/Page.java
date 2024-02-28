package com.it.pojo;

/**
 * @author zy293
 * dateValue:当前日期
 * currentPage：当前页码
 * announceTitle:公告标题
 * trainName:培训名称
 * employeeName:员工姓名
 * department：部门
 * role:身份
*/
public class Page {
   private String dateValue;
   private int currentPage;
   private String announceTitle;
   private String trainName;
   private String employeeName;
   private String department;
   private String role;
   private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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

