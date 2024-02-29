package com.it.pojo;

import java.sql.Date;

/**
 * @author zy293
 * Salary:薪水表
 * id:主键
 * employeeId:员工id
 * money:工资
 * baseSalary:底薪
 * attendanceBonus:满勤奖
 * performancePay:业绩奖
 */
public class Salary {
    private Integer id;
    private Integer employeeId;
    private String name;
    private String department;
    private String sendDate;
    private double money;
    private double baseSalary;
    private double attendanceBonus;
    private double performancePay;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getAttendanceBonus() {
        return attendanceBonus;
    }

    public void setAttendanceBonus(double attendanceBonus) {
        this.attendanceBonus = attendanceBonus;
    }

    public double getPerformancePay() {
        return performancePay;
    }

    public void setPerformancePay(double performancePay) {
        this.performancePay = performancePay;
    }
}
