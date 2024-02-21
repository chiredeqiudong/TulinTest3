package com.it.service;

import com.it.pojo.Employee;

/**
 * @author zy293
 * EmployeeService:Employee接口
 */
public interface EmployeeService {
    /**
     * loginSelect:登录
     * @param username:用户名
     * @param password:密码
     * @return Employee对象
     */
    Employee loginSelect(String username,String password);

}
