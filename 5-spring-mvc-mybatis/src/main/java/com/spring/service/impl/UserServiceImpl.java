package com.spring.service.impl;

import com.spring.mapper.UserMapper;
import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //    @Override
//    public User findUser(Integer id) {
////        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring-mybatis.xml");
////        try {
////            DriverManagerDataSource source = (DriverManagerDataSource)context.getBean("dataSource");
////            System.out.println("密码:" + source.getPassword());
////            userMapper = (UserMapper)context.getBean("UserMapper");
////            return userMapper.findUser(id);
////        } catch (Exception ex) {
////            System.out.println("异常信息:" + ex);
////        }
////        return null;
//       return userMapper.findUser(id);
//    }
//
    @Autowired
    UserMapper userMapper;

    public User findUser(Integer id) {
        return userMapper.findUser(id);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }
}
