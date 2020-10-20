package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @GetMapping("home")
    public String home() {
        return "欢迎访问Elephant的主页";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // 当前用户信息
    @GetMapping("/info")
    @ResponseBody
    public Object getCurrentUser(Authentication authentication) {
        return authentication;
    }

}
