package com.spring.controller;

import com.spring.entity.Student;
import com.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @GetMapping(value = "/findAll")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @ResponseBody
    @GetMapping(value = "findById/{id}")
    public Student findById(@PathVariable("id") Integer id) {
        return studentService.findById(id);
    }

    @ResponseBody
    @GetMapping(value = "add")
    public String add() {
        Student student = new Student();
        student.name = "Java";
        student.age = 22;
        student.birthday = new Date();
        int res = studentService.insert(student);
        if (res > 0) {
            return "新增成功";
        } else {
            return "新增失败";
        }
    }

    @ResponseBody
    @GetMapping(value = "update/{id}")
    public String updateById(@PathVariable("id") Integer id) {
        Student student = studentService.findById(id);
        student.name = student.name + "---update";
        student.age = student.age + 1;
        int res = studentService.update(student);
        if (res > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @ResponseBody
    @GetMapping(value = "delete/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        int res = studentService.delete(id);
        if (res > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

}