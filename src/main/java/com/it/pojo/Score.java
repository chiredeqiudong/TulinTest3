package com.it.pojo;

/**
 * @author zy293
 * Score:员工成绩表
 * id:主键
 * employeeId:员工id
 * trainName:培训名称
 * score:培训成绩
 */
public class Score {
    private Integer id;
    private Integer employeeId;
    private String trainName;
    private String score;

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

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
