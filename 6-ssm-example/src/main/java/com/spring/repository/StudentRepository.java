package com.spring.repository;

import com.spring.entity.Student;

import java.util.List;

public interface StudentRepository {

    List<Student> findAll();

    Student findById(Integer id);

    int insertStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(Integer id);
}
