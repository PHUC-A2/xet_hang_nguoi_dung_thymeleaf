package com.example.xephangnguoidung.presentation.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class TrangChuController {
    @GetMapping("")
    public String trangChu() {
        return "user/trang_chu";
    }
}
