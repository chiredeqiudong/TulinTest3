package com.it.mapper;

import com.it.pojo.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * @author zy293
 * EmployeeMapper:员工mapper
 */
public interface EmployeeMapper {
    /**
     * loginSelect:查询登录账户
     * @param username : 用户名
     * @param password : 登录密码
     * @return : 该员工数据
     */
    Employee loginSelect(@Param("username") String username,@Param("password") String password);




}
