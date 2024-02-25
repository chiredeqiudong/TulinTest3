package com.it.pojo;

/**
 * @author zy293
 * Quit:离职表
 * id:主键
 * employeeId:员工id
 * name:姓名
 * department:部门
 * quitReason:离职原因
 * quitStatus:审核状态
 */
public class Quit {
    private Integer id;
    private Integer employeeId;
    private String name;
    private String department;
    private String quitReason;
    private String quitStatus;

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

  public String getQuitReason() {
    return quitReason;
  }

  public void setQuitReason(String quitReason) {
    this.quitReason = quitReason;
  }

  public String getQuitStatus() {
    return quitStatus;
  }

  public void setQuitStatus(String quitStatus) {
    this.quitStatus = quitStatus;
  }
}
