package com.it.pojo;

/**
 * @author zy293
 * Salary:薪水表
 * employeeId:员工id
 * money:工资
 * baseSalary:底薪
 * attendanceBonus:满勤奖
 * performancePay:业绩奖
 */
public class Salary {
    private Integer id;
    private Integer employeeId;
    private double money;
    private double baseSalary;
    private double attendanceBonus;
    private double performancePay;

    public Salary() {
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
