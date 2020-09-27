package com.spring.service.impl;

import com.spring.entity.Student;
import com.spring.repository.StudentRepository;
import com.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id);
    }

    public int insert(Student student) {
        return studentRepository.insertStudent(student);
    }

    public int update(Student student) {
        return studentRepository.updateStudent(student);
    }

    public int delete(Integer id) {
        return studentRepository.deleteStudent(id);
    }

}
