package com.example.mybatis.mapper;

import com.example.mybatis.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User entity1 = new User();
        entity1.name = "elephant";
        entity1.phone = "123456789";
        entity1.telephone = "110";
        entity1.address = "北京";
        userMapper.insert(entity1);
        User entity2 = new User();
        entity2.name = "李白";
        entity2.phone = "123456789";
        entity2.telephone = "119";
        entity2.address = "唐朝";
        userMapper.insert(entity2);
        User entity3 = new User();
        entity3.name = "苏轼";
        entity3.phone = "123456789";
        entity3.telephone = "120";
        entity3.address = "宋朝";
        userMapper.insert(entity3);
    }

    @Test
    public void findAll() {
        List<User> list = userMapper.findAll();
        for (User entiy : list) {
            System.out.println("学生Id:" + entiy.id + "---学生姓名:" + entiy.name);
        }
    }

    @Test
    public void update() {
        User entity = userMapper.findUserById(18);
        entity.name = entity.name + "---update";
        entity.phone = entity.phone + "---update";
        entity.telephone = entity.telephone + "---update";
        userMapper.update(entity);
    }

    @Test
    public void delete() {
        userMapper.delete(18);
        List<User> list = userMapper.findAll();
        for (User entiy : list) {
            System.out.println("学生Id:" + entiy.id + "---学生姓名:" + entiy.name);
        }
    }
}