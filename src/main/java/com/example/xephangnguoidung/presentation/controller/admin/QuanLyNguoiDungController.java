package com.example.xephangnguoidung.presentation.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.application.service.DiemNguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;

@Controller
@RequestMapping("/admin/nguoidung")
public class QuanLyNguoiDungController {
    private final NguoiDungService nguoiDungService;
    private final DiemNguoiDungService diemNguoiDungService;

    public QuanLyNguoiDungController(NguoiDungService nguoiDungService, DiemNguoiDungService diemNguoiDungService) {
        this.nguoiDungService = nguoiDungService;
        this.diemNguoiDungService = diemNguoiDungService;
    }

    // Trả về trang HTML hiển thị danh sách người dùng
    @GetMapping("/laytatcanguoidung")
    public String showAllUsers(Model model) {
        List<NguoiDung> danhSachNguoiDung = nguoiDungService.layTatCaNguoiDung();
        model.addAttribute("danhSachNguoiDung", danhSachNguoiDung);
        model.addAttribute("nguoiDung", new NguoiDung()); // Đảm bảo không null
        model.addAttribute("diemNguoiDungService", diemNguoiDungService);
        return "admin/quanly_nguoidung";
    }

    // Thêm người dùng mới
    @PostMapping("/tao")
    public String addUser(@ModelAttribute NguoiDung nguoiDung) {
        nguoiDungService.luuNguoiDung(nguoiDung);
        return "redirect:/admin/nguoidung/laytatcanguoidung";
    }

    // Xóa người dùng theo ID
    @PostMapping("/xoanguoidung/id/{id}")
    public String deleteUser(@PathVariable Long id) {
        nguoiDungService.xoaNguoiDungBangId(id);
        return "redirect:/admin/nguoidung/laytatcanguoidung";
    }

    // Lấy người dùng theo ID
    @GetMapping("/laynguoidung/id/{id}")
    public ResponseEntity<NguoiDung> layNguoiDungById(@PathVariable Long id) {
        return ResponseEntity.ok(nguoiDungService.layNguoiDungById(id));
    }

    // Sửa thông tin người dùng
    @PostMapping("/sua/id/{id}")
    public String suaNguoiDungById(@PathVariable Long id, @ModelAttribute NguoiDung request,
            RedirectAttributes redirectAttributes) {
        NguoiDung nguoiDung = nguoiDungService.layNguoiDungById(id);
        nguoiDung.setTenDangNhap(request.getTenDangNhap());
        nguoiDung.setMatKhau(request.getMatKhau());
        nguoiDung.setVaiTro(request.getVaiTro());
        if (request.getEmail() != null) {
            nguoiDung.setEmail(request.getEmail());
        }

        // Lưu người dùng trước, sau đó cập nhật cấp bậc
        nguoiDungService.suaNguoiDung(id, nguoiDung);
        nguoiDungService.capNhatCapBac(nguoiDung);

        // Thêm thông báo thành công
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật người dùng thành công!");

        return "redirect:/admin/nguoidung/laytatcanguoidung";
    }

    // Tìm kiếm người dùng
    @GetMapping("/timkiem")
    public String timKiemNguoiDung(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return "redirect:/admin/nguoidung/laytatcanguoidung";
        }
        List<NguoiDung> danhSachNguoiDung = nguoiDungService.timKiemNguoiDung(keyword);
        model.addAttribute("danhSachNguoiDung", danhSachNguoiDung);
        model.addAttribute("keyword", keyword);
        model.addAttribute("diemNguoiDungService", diemNguoiDungService); // Add this line to pass service to the model
        return "admin/quanly_nguoidung";
    }
}