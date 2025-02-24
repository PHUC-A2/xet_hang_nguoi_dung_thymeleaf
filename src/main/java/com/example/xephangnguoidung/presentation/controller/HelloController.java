package com.example.xephangnguoidung.presentation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;

@Controller
public class HelloController {
    private final NguoiDungService nguoiDungService;

    public HelloController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }
    @GetMapping("/hello")
    public String helloPage() {
        return "admin/quanly_nguoidung";// file html
    }
     // view
    @GetMapping("/view/laytatcanguoidung")
    public String showAllUsers(Model model) {
        List<NguoiDung> danhSachNguoiDung = nguoiDungService.layTatCaNguoiDung();
        model.addAttribute("danhSachNguoiDung", danhSachNguoiDung);
        return "admin/quanly_nguoidung"; // Tên của file HTML
    }
}
