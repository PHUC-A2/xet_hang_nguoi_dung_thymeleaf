package com.example.xephangnguoidung.presentation.controller.admin;

import com.example.xephangnguoidung.application.service.LuotThichService;
import com.example.xephangnguoidung.data.entity.LuotThich;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/luotthich")
public class QuanLyLuotThichController {

    private final LuotThichService luotThichService;

    public QuanLyLuotThichController(LuotThichService luotThichService) {
        this.luotThichService = luotThichService;
    }

    // Thêm lượt thích
    @PostMapping("/tao")
    public String themLuotThich(@RequestParam Long nguoiDungId, @RequestParam Long baiVietId, Model model) {
        try {
            luotThichService.themLuotThich(nguoiDungId, baiVietId);
            model.addAttribute("message", "Đã thêm lượt thích thành công.");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/admin/luotthich";
    }

    // Xem tất cả lượt thích của một bài viết
    @GetMapping("/baiviet/{baiVietId}")
    public ResponseEntity<List<Long>> layDanhSachNguoiThich(@PathVariable Long baiVietId) {
        List<Long> danhSachNguoiThich = luotThichService.layDanhSachNguoiThich(baiVietId);
        return ResponseEntity.ok(danhSachNguoiThich);
    }

    // Xóa lượt thích của một người dùng với bài viết
    @PostMapping("/xoa/{nguoiDungId}/{baiVietId}")
    public String xoaLuotThich(@PathVariable Long nguoiDungId, @PathVariable Long baiVietId, Model model) {
        try {
            luotThichService.xoaLuotThich(nguoiDungId, baiVietId);
            model.addAttribute("message", "Đã xóa lượt thích thành công.");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/admin/luotthich";
    }

    // Xem tổng số lượt thích trên toàn hệ thống
    @GetMapping("/tongluotthich")
    public ResponseEntity<Integer> tongLuotThich() {
        int tongSoLuotThich = luotThichService.demTongLuotThich();
        return ResponseEntity.ok(tongSoLuotThich);
    }

    // Hiển thị danh sách lượt thích
    @GetMapping
    public String hienThiDanhSachLuotThich(Model model) {
        List<LuotThich> danhSachLuotThich = luotThichService.layTatCaLuotThich();
        int tongSoLuotThich = luotThichService.demTongLuotThich();
        model.addAttribute("danhSachLuotThich", danhSachLuotThich);
        model.addAttribute("tongSoLuotThich", tongSoLuotThich);
        return "admin/quanly_luotthich";
    }

    // Tìm kiếm lượt thích
    @GetMapping("/timkiem")
    public String timKiemLuotThich(@RequestParam("keyword") String keyword, Model model) {
        List<LuotThich> danhSachLuotThich = luotThichService.timKiemLuotThich(keyword);
        int tongSoLuotThich = luotThichService.demTongLuotThich();
        model.addAttribute("danhSachLuotThich", danhSachLuotThich);
        model.addAttribute("tongSoLuotThich", tongSoLuotThich);
        return "admin/quanly_luotthich";
    }
}