package com.example.xephangnguoidung.presentation.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuenMatKhauController {
    @GetMapping("/quenmatkhau")
    public String quenMatKhau() {
        return "auth/quen_matkhau";
    }
}
