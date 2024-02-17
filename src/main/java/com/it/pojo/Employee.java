package com.it.pojo;

import java.sql.Date;

/**
 * @author zy293
 * Employee:员工表数据
 * id:员工编号
 * account:账户
 * password:密码
 * name:姓名
 * age:年龄
 * gender:性别
 * role:角色
 * phone:电话号码
 * status:在职、离职
 * joinDate:入职日期
 */
public class Employee {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private Integer age;
    private String gender;
    private String role;
    private Integer phone;
    private String status;
    private Date joinDate;

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
