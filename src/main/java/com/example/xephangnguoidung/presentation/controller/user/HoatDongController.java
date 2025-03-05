package com.example.xephangnguoidung.presentation.controller.user;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class HoatDongController {

    @GetMapping("/hoatdong")
    public String hienThiHoatDong(Model model) {
        return "user/hoatdong_nguoidung";
    }
}