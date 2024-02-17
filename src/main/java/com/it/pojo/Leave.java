package com.it.pojo;

import java.sql.Date;

/**
 * @author zy293
 * Leave:请假表
 * id:主键
 * employeeId:员工id
 * leaveType:请假类型
 * startDate:请假开始日期
 * endDate:请假结束日期
 * reason:请假原因
 * leaveStatus:请假状态：待审批、已批准、已拒绝
 */
public class Leave {
    private Integer id;
    private Integer employeeId;
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String leaveStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
