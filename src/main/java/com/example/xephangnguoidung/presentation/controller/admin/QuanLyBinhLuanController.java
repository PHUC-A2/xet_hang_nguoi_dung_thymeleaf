package com.example.xephangnguoidung.presentation.controller.admin;

import com.example.xephangnguoidung.application.service.BinhLuanService;
import com.example.xephangnguoidung.data.entity.BinhLuan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/binhluan")
public class QuanLyBinhLuanController {
    private final BinhLuanService binhLuanService;

    public QuanLyBinhLuanController(BinhLuanService binhLuanService) {
        this.binhLuanService = binhLuanService;
    }

    // ✅ 1️⃣ Thêm bình luận
    @PostMapping("/tao")
    public ResponseEntity<String> themBinhLuan(@RequestBody BinhLuan binhLuan) {
        binhLuanService.themBinhLuan(binhLuan);
        return ResponseEntity.ok("Thêm bình luận thành công!");
    }

    // ✅ 2️⃣ Xóa bình luận
    @DeleteMapping("/xoabinhluan/{binhLuanId}")
    public ResponseEntity<String> xoaBinhLuan(@PathVariable Long binhLuanId) {
        binhLuanService.xoaBinhLuan(binhLuanId);
        return ResponseEntity.ok("Xóa bình luận thành công!");
    }

    // ✅ 3️⃣ Lấy danh sách bình luận của bài viết
    @GetMapping("/baiviet/{baiVietId}")
    public ResponseEntity<List<BinhLuan>> layDanhSachBinhLuan(@PathVariable Long baiVietId) {
        List<BinhLuan> danhSachBinhLuan = binhLuanService.layDanhSachBinhLuan(baiVietId);
        return ResponseEntity.ok(danhSachBinhLuan);
    }
}
