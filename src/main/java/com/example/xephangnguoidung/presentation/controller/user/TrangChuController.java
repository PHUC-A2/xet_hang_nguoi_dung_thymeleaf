package com.example.xephangnguoidung.presentation.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {
    @GetMapping("/user")
    public String trangChu() {
        return "user/trang_chu";
    }
}
