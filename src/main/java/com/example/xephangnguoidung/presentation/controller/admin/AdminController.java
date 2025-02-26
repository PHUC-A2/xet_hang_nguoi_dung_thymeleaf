package com.example.xephangnguoidung.presentation.controller.admin;

import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class AdminController {

    private final NguoiDungService nguoiDungService;

    public AdminController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    @GetMapping("/admin")
    public String trangChuAdmin(Model model) {
        List<NguoiDung> danhSachNguoiDung = nguoiDungService.layTatCaNguoiDung();
        Collections.sort(danhSachNguoiDung, Comparator.comparingInt(NguoiDung::getDiem)); // Sắp xếp từ thấp đến cao

        model.addAttribute("danhSachNguoiDung", danhSachNguoiDung);
        return "admin/trang_chu_admin";
    }
}