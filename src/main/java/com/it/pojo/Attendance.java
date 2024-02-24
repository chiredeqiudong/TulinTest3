package com.it.pojo;

import java.sql.Date;

/**
 * @author zy293
 * Attendance:考勤表
 * id:主键
 * employeeId:员工id
 * name：员工姓名
 * date:当天日期
 * absenceRecord:考勤记录 迟到、早退、缺勤、正常
 */
public class Attendance {
    private Integer id;
    private Integer employeeId;
    private String name;
    private String date;
    private String absenceRecord;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAbsenceRecord() {
        return absenceRecord;
    }

    public void setAbsenceRecord(String absenceRecord) {
        this.absenceRecord = absenceRecord;
    }
}
