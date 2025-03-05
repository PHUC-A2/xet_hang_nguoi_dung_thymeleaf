package com.example.xephangnguoidung.presentation.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.xephangnguoidung.data.enums.VaiTro;

@Controller
public class DangNhapController {
    @GetMapping("/dangnhap")
    public String dangNhap(Model model) {
        model.addAttribute("danhSachVaiTro",VaiTro.values());
        return "auth/dang_nhap";
    }
}
