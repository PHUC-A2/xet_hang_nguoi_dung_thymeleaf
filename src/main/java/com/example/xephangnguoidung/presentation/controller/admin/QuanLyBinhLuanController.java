package com.example.xephangnguoidung.presentation.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.xephangnguoidung.application.service.BinhLuanService;
import com.example.xephangnguoidung.data.entity.BinhLuan;

@RestController
@RequestMapping("/admin/binhluan")
public class QuanLyBinhLuanController {
    private final BinhLuanService binhLuanService;

    public QuanLyBinhLuanController(BinhLuanService binhLuanService) {
        this.binhLuanService = binhLuanService;
    }

    // Tạo bình luận
    @PostMapping("/tao")
    public ResponseEntity<BinhLuan> taoBinhLuan(@RequestBody BinhLuan binhLuan) {
        if (binhLuan.getNoiDung() == null || binhLuan.getNoiDung().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        BinhLuan newBinhLuan = this.binhLuanService.luuBinhLuan(binhLuan);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBinhLuan);
    }

    // Lấy toàn bộ bình luận
    @GetMapping("/laytatcabinhluan")
    public ResponseEntity<List<BinhLuan>> layTatCaBinhLuan() {
        List<BinhLuan> danhSachBinhLuan = this.binhLuanService.layTatCaBinhLuan();
        return ResponseEntity.ok(danhSachBinhLuan);
    }

    // Lấy bình luận theo ID
    @GetMapping("/laybinhluan/id/{id}")
    public ResponseEntity<BinhLuan> layBinhLuanById(@PathVariable Long id) {
        try {
            BinhLuan binhLuan = this.binhLuanService.layBinhLuanById(id);
            return ResponseEntity.ok(binhLuan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Lấy tất cả bình luận theo bài viết ID
    @GetMapping("/laybinhluan/baiviet/id/{id}")
    public ResponseEntity<List<BinhLuan>> layBinhLuanBaiVietId(@PathVariable Long id) {
        try {
            List<BinhLuan> binhLuans = binhLuanService.layTatCaBinhLuanTheoBaiViet(id);
            return ResponseEntity.ok(binhLuans);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Sửa bình luận
    @PutMapping("/suabinhluan/id/{id}")
    public ResponseEntity<BinhLuan> suaBinhLuanId(@PathVariable Long id, @RequestBody BinhLuan request) {
        try {
            if (request.getNoiDung() == null || request.getNoiDung().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(null); // Trả về 400 nếu nội dung rỗng
            }

            BinhLuan binhLuan = this.binhLuanService.layBinhLuanById(id);
            binhLuan.setNoiDung(request.getNoiDung());

            BinhLuan binhLuanMoi = this.binhLuanService.suaBinhLuan(binhLuan);
            return ResponseEntity.ok(binhLuanMoi);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Xóa bình luận
    @DeleteMapping("/xoabinhluan/id/{id}")
    public ResponseEntity<Void> xoaBinhLuanId(@PathVariable Long id) {
        try {
            this.binhLuanService.xoaBinhLuan(id);
            return ResponseEntity.noContent().build(); // Trả về 204 No Content nếu xóa thành công
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Trả về 404 nếu không tìm thấy
        }
    }
}
