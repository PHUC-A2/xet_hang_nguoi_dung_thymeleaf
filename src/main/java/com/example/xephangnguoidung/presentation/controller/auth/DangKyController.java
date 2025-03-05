package com.example.xephangnguoidung.presentation.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DangKyController {
    @GetMapping("/dangky")
    public String DangKy() {
        return "auth/dang_ky";
    }
}
