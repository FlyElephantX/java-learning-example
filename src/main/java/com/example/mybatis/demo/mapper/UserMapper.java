package com.example.mybatis.demo.mapper;

import com.example.mybatis.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Select("select * from user")
    List<UserEntity> findAll();

    @Select("select * from user where id = #{id}")
    UserEntity findUserById(int id);

    @Insert("insert into user(name, phone, telephone, address) values(#{name}, #{phone}, #{telephone}, #{address})")
    void insert(UserEntity user);

    @Update("update user set name=#{name}, phone=#{phone}, telephone=#{telephone}, address=#{address} where id=#{id}")
    void update(UserEntity user);

    @Delete("delete from user where id=#{id}")
    void delete(int id);
}
