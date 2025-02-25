package com.example.xephangnguoidung.presentation.controller.admin;

import com.example.xephangnguoidung.application.service.LuotThichService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/luotthich") // Định nghĩa URL cho admin
public class QuanLyLuotThichController {

    private final LuotThichService luotThichService;

    public QuanLyLuotThichController(LuotThichService luotThichService) {
        this.luotThichService = luotThichService;
    }

    // ✅ 4️⃣ Thêm lượt thích
    @PostMapping("/tao/{nguoiDungId}/{baiVietId}")
    public ResponseEntity<String> themLuotThich(@PathVariable Long nguoiDungId, @PathVariable Long baiVietId) {
        luotThichService.themLuotThich(nguoiDungId, baiVietId);
        return ResponseEntity.ok("Đã thêm lượt thích thành công.");
    }

    // ✅ 1️⃣ Xem tất cả lượt thích của một bài viết
    @GetMapping("/baiviet/{baiVietId}")
    public ResponseEntity<List<Long>> layDanhSachNguoiThich(@PathVariable Long baiVietId) {
        List<Long> danhSachNguoiThich = luotThichService.layDanhSachNguoiThich(baiVietId);
        return ResponseEntity.ok(danhSachNguoiThich);
    }

    // ✅ 2️⃣ Xóa lượt thích của một người dùng với bài viết
    @DeleteMapping("/xoa/{nguoiDungId}/{baiVietId}")
    public ResponseEntity<String> xoaLuotThich(@PathVariable Long nguoiDungId, @PathVariable Long baiVietId) {
        luotThichService.xoaLuotThich(nguoiDungId, baiVietId);
        return ResponseEntity.ok("Đã xóa lượt thích thành công.");
    }

    // ✅ 3️⃣ Xem tổng số lượt thích trên toàn hệ thống
    @GetMapping("/tongluotthich")
    public ResponseEntity<Integer> tongLuotThich() {
        int tongSoLuotThich = luotThichService.demTongLuotThich();
        return ResponseEntity.ok(tongSoLuotThich);
    }

}
