package com.example.xephangnguoidung.presentation.controller.admin;

import com.example.xephangnguoidung.application.service.DiemNguoiDungService;
import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.enums.LoaiHoatDong;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin/diem")
public class QuanLyDiemController {

    private final DiemNguoiDungService diemNguoiDungService;
    private final NguoiDungService nguoiDungService;

    public QuanLyDiemController(DiemNguoiDungService diemNguoiDungService, NguoiDungService nguoiDungService) {
        this.diemNguoiDungService = diemNguoiDungService;
        this.nguoiDungService = nguoiDungService;
    }

    @PostMapping("/tinhdiem")
    public String tinhDiem(@RequestParam Long nguoiDungId, @RequestParam LoaiHoatDong loaiHoatDong) {
        diemNguoiDungService.tinhDiem(nguoiDungId, loaiHoatDong);

        // Lấy lại người dùng sau khi điểm đã cập nhật
        NguoiDung nguoiDungCapNhat = nguoiDungService.layNguoiDungById(nguoiDungId);

        // Cập nhật cấp bậc nếu cần
        nguoiDungService.capNhatCapBac(nguoiDungCapNhat);

        return "redirect:/admin";
    }

    @GetMapping("/bangxephang")
    public String bangXepHangNguoiDung(Model model) {
        List<NguoiDung> danhSachNguoiDung = nguoiDungService.layTatCaNguoiDung();
        Collections.sort(danhSachNguoiDung, Comparator.comparingInt(NguoiDung::getDiem)); // Sắp xếp từ thấp 
                                                                                                     // đến cao
        model.addAttribute("danhSachNguoiDung", danhSachNguoiDung);
        return "admin/bang_xep_hang_nguoidung";
    }
}