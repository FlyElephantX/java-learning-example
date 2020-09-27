package com.spring.service;

import com.spring.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(Integer id);

    int insert(Student student);

    int update(Student student);

    int delete(Integer id);
}

