package com.spring.controller;

import com.spring.model.ResultBean;
import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        System.out.println("登录页");
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        System.out.println("注册页");
        return "register";
    }

    @ResponseBody
    @GetMapping("/users")
    public ResultBean<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResultBean<>(users);
    }

    @ResponseBody
    @GetMapping("/insert")
    public ResultBean insertUser() {
        User user = new User();
        SimpleDateFormat format = new SimpleDateFormat();// 格式化时间
        format.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        user.name = "elephant-" + format.format(date);
        user.phone = "123456789";
        user.telephone = "110";
        user.address = "中国";
        int result = userService.insertUser(user);
        if (result > 0) {
            return new ResultBean();
        } else {
            return new ResultBean("新增失败");
        }
    }

    @ResponseBody
    @GetMapping("/users/{id}")
    public ResultBean<User> findUser(@PathVariable Integer id) {
        User user = userService.findUser(id);
        return new ResultBean<>(user);
    }

    @ResponseBody
    @GetMapping("/update/{id}")
    public ResultBean updateUser(@PathVariable Integer id) {
        User user = userService.findUser(id);
        user.name = user.name + "---update";
        user.phone = user.phone + "---update";
        user.telephone = user.telephone + "---update";
        user.address = user.address + "---update";
        int result = userService.updateUser(user);
        if (result > 0) {
            return new ResultBean();
        } else {
            return new ResultBean("修改成功");
        }
    }

    @ResponseBody
    @GetMapping("/delete/{id}")
    public ResultBean deleteUser(@PathVariable Integer id) {
        int result = userService.deleteUser(id);
        if (result > 0) {
            return new ResultBean();
        } else {
            return new ResultBean("删除失败");
        }
    }

}
