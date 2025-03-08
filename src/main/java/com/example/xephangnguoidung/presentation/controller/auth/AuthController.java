package com.example.xephangnguoidung.presentation.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/register")
    public String getRegister() {
        return "auth/register"; // trả về trang đăng ký
    }
    
    @GetMapping("/login")
    public String getLogin() {
        return "auth/login"; // trả về trang đăng nhập
    }
}
