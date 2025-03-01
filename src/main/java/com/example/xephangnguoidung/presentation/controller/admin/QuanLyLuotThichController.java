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
    @PostMapping("/tao/{nguoiDungId}/{baiVietId}")
    public ResponseEntity<String> themLuotThich(@PathVariable Long nguoiDungId, @PathVariable Long baiVietId) {
        luotThichService.themLuotThich(nguoiDungId, baiVietId);
        return ResponseEntity.ok("Đã thêm lượt thích thành công.");
    }

    // Xem tất cả lượt thích của một bài viết
    @GetMapping("/baiviet/{baiVietId}")
    public ResponseEntity<List<Long>> layDanhSachNguoiThich(@PathVariable Long baiVietId) {
        List<Long> danhSachNguoiThich = luotThichService.layDanhSachNguoiThich(baiVietId);
        return ResponseEntity.ok(danhSachNguoiThich);
    }

    // Xóa lượt thích của một người dùng với bài viết
    @PostMapping("/xoa/{nguoiDungId}/{baiVietId}")
    public ResponseEntity<String> xoaLuotThich(@PathVariable Long nguoiDungId, @PathVariable Long baiVietId) {
        luotThichService.xoaLuotThich(nguoiDungId, baiVietId);
        return ResponseEntity.ok("Đã xóa lượt thích thành công.");
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