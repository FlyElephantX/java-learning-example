package com.example.mybatis.demo.mapper;

import com.example.mybatis.demo.entity.UserEntity;
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
        UserEntity entity1 = new UserEntity();
        entity1.name = "elephant";
        entity1.phone = "123456789";
        entity1.telephone = "110";
        entity1.address = "北京";
        userMapper.insert(entity1);
        UserEntity entity2 = new UserEntity();
        entity2.name = "李白";
        entity2.phone = "123456789";
        entity2.telephone = "119";
        entity2.address = "唐朝";
        userMapper.insert(entity2);
        UserEntity entity3 = new UserEntity();
        entity3.name = "苏轼";
        entity3.phone = "123456789";
        entity3.telephone = "120";
        entity3.address = "宋朝";
        userMapper.insert(entity3);
    }

    @Test
    public void findAll() {
        List<UserEntity> list = userMapper.findAll();
        for (UserEntity entiy : list) {
            System.out.println("学生Id:" + entiy.id + "---学生姓名:" + entiy.name);
        }
    }

    @Test
    public void update() {
        UserEntity entity = userMapper.findUserById(18);
        entity.name = entity.name + "---update";
        entity.phone = entity.phone + "---update";
        entity.telephone = entity.telephone + "---update";
        userMapper.update(entity);
    }

    @Test
    public void delete() {
        userMapper.delete(18);
        List<UserEntity> list = userMapper.findAll();
        for (UserEntity entiy : list) {
            System.out.println("学生Id:" + entiy.id + "---学生姓名:" + entiy.name);
        }
    }
}

