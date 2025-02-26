package com.example.xephangnguoidung.presentation.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;

@Controller
@RequestMapping("/admin/nguoidung")
public class QuanLyNguoiDungController {
    private final NguoiDungService nguoiDungService;

    public QuanLyNguoiDungController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    // Trả về trang HTML hiển thị danh sách người dùng
    @GetMapping("/laytatcanguoidung")
    public String showAllUsers(Model model) {
        List<NguoiDung> danhSachNguoiDung = nguoiDungService.layTatCaNguoiDung();
        model.addAttribute("danhSachNguoiDung", danhSachNguoiDung);
        model.addAttribute("nguoiDung", new NguoiDung()); // Thêm dòng này để đảm bảo nguoiDung không null
        return "admin/quanly_nguoidung"; // Tên file HTML hiển thị danh sách người dùng
    }

    // Thêm người dùng mới
    @PostMapping("/tao")
    public String addUser(@ModelAttribute NguoiDung nguoiDung) {
        nguoiDungService.luuNguoiDung(nguoiDung);
        return "redirect:/admin/nguoidung/laytatcanguoidung"; // Redirect to the list of users
    }

    // Xóa người dùng theo ID
    @PostMapping("/xoanguoidung/id/{id}")
    public String deleteUser(@PathVariable Long id) {
        nguoiDungService.xoaNguoiDungBangId(id);
        return "redirect:/admin/nguoidung/laytatcanguoidung"; // Redirect to the list of users
    }

    // Lấy người dùng theo ID
    @GetMapping("/laynguoidung/id/{id}")
    public ResponseEntity<NguoiDung> layNguoiDungById(@PathVariable Long id) {
        return ResponseEntity.ok(nguoiDungService.layNguoiDungById(id));
    }

    // Sửa thông tin người dùng
    @PostMapping("/sua/id/{id}")
    public String suaNguoiDungById(@PathVariable Long id, @ModelAttribute NguoiDung request) {
        NguoiDung nguoiDung = nguoiDungService.layNguoiDungById(id);

        // Giữ nguyên ID, Email và ngày tạo
        nguoiDung.setTenDangNhap(request.getTenDangNhap());
        nguoiDung.setMatKhau(request.getMatKhau());
        nguoiDung.setDiem(request.getDiem());
        nguoiDung.setCapBac(request.getCapBac());
        nguoiDung.setVaiTro(request.getVaiTro());
        nguoiDung.setSoLanDangNhap(request.getSoLanDangNhap());

        // Cập nhật Email nếu có
        if (request.getEmail() != null) {
            nguoiDung.setEmail(request.getEmail());
        }

        nguoiDungService.suaNguoiDung(id, nguoiDung);
        return "redirect:/admin/nguoidung/laytatcanguoidung"; // Redirect to the list of users
    }

    // Tìm kiếm người dùng
    @GetMapping("/timkiem")
    public String timKiemNguoiDung(@RequestParam("keyword") String keyword, Model model) {
        List<NguoiDung> danhSachNguoiDung = nguoiDungService.timKiemNguoiDung(keyword);
        model.addAttribute("danhSachNguoiDung", danhSachNguoiDung);
        model.addAttribute("keyword", keyword);
        return "admin/quanly_nguoidung"; // Tên file HTML hiển thị danh sách người dùng
    }
}