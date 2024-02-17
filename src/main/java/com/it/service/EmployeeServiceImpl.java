package com.it.service;


import com.it.mapper.EmployeeMapper;
import com.it.pojo.Employee;
import com.it.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author zy293
 * EmployeeServiceImpl:Employee实现类
 */
public class EmployeeServiceImpl implements EmployeeService {
    /**
     * sqlFactory:SqlSessionFactory的接口对象,用于创建sqlSession对象
     */
    private final SqlSessionFactory sqlFactory = SqlSessionFactoryUtil.getFactory();


    /**
     * loginSelect:登录
     * @param account:账号
     * @param password:密码
     */
    @Override
    public Employee loginSelect(String account, String password) {
        SqlSession sqlSession = sqlFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.loginSelect(account, password);
        sqlSession.close();
        return employee;
    }
}