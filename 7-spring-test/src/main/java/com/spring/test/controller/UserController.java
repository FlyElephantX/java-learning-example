package com.spring.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/register")
    public String register() {
        System.out.println("注册页");
        return "register";
    }
}
