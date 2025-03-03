package com.example.xephangnguoidung.presentation.controller.admin;

import com.example.xephangnguoidung.application.service.DiemNguoiDungService;
import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.enums.LoaiHoatDong;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
        System.out.println(
                "✅ Gọi API /admin/diem/tinhdiem với nguoiDungId=" + nguoiDungId + " và loaiHoatDong=" + loaiHoatDong);

        diemNguoiDungService.tinhDiem(nguoiDungId, loaiHoatDong);

        // Lấy lại người dùng sau khi điểm đã cập nhật
        NguoiDung nguoiDungCapNhat = nguoiDungService.layNguoiDungById(nguoiDungId);
        nguoiDungService.capNhatCapBac(nguoiDungCapNhat);

        return "redirect:/admin";
    }

    @GetMapping("/bangxephang")
    public String bangXepHangNguoiDung(Model model) {
        List<NguoiDung> danhSachNguoiDung = nguoiDungService.layTatCaNguoiDung();
        if (danhSachNguoiDung.isEmpty()) {
            model.addAttribute("danhSachNguoiDung", Collections.emptyList());
        } else {
            // Sắp xếp danh sách người dùng theo tổng điểm từ cao đến thấp
            Collections.sort(danhSachNguoiDung, (nd1, nd2) -> {
                int tongDiem1 = diemNguoiDungService.tinhTongDiemByNguoiDungId(nd1.getId());
                int tongDiem2 = diemNguoiDungService.tinhTongDiemByNguoiDungId(nd2.getId());
                return Integer.compare(tongDiem2, tongDiem1);
            });
            model.addAttribute("danhSachNguoiDung", danhSachNguoiDung);
        }
        // Đảm bảo rằng diemNguoiDungService không bị null trong template
        model.addAttribute("diemNguoiDungService", diemNguoiDungService);
        return "admin/bang_xep_hang_nguoidung";
    }
}