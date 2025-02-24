package com.example.xephangnguoidung.presentation.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.xephangnguoidung.application.service.BinhLuanService;
import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.entity.BinhLuan;

@RestController
@RequestMapping("/admin/binhluan") // link
public class QuanLyBinhLuanController {
    private final BinhLuanService binhLuanService;

    public QuanLyBinhLuanController(BinhLuanService binhLuanService) {
        this.binhLuanService = binhLuanService;
    }

    // tạo bình luận
    @PostMapping("/tao")
    public BinhLuan taoBinhLuan(@RequestBody BinhLuan binhLuan){
        return this.binhLuanService.luuBinhLuan(binhLuan);
    }

    // lấy toàn bộ bình luận 
    @GetMapping("/laytatcabinhluan")
    public List<BinhLuan> layTatCaBinhLuan(){
        return this.binhLuanService.layTatCaBinhLuan();
    }

    // lấy bình luận theo id
    @GetMapping("/laybinhluan/id/{id}")
    public BinhLuan layBinhLuanById(@PathVariable Long id){
        return this.binhLuanService.layBinhLuanById(id);
    }


    // Lấy tất cả bình luận theo bài viết ID
    @GetMapping("/laybinhluan/baiviet/id/{id}")
    public ResponseEntity<List<BinhLuan>> layBinhLuanBaiVietId(@PathVariable Long id) {
        try {
            BaiViet baiViet = new BaiViet();
            baiViet.setId(id); // Tạo đối tượng bài viết với ID

            List<BinhLuan> binhLuans = binhLuanService.layTatCaBinhLuanTheoBaiViet(baiViet);
            return ResponseEntity.ok(binhLuans);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // sửa bình luận 
    @PutMapping("/suabinhluan/id/{id}")
    public ResponseEntity<BinhLuan> suaBinhLuanId(@PathVariable Long id, @RequestBody BinhLuan request) {
        BinhLuan binhLuan = this.binhLuanService.layBinhLuanById(id);

        if (binhLuan == null) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy bình luận
        }

        if (request.getNoiDung() == null || request.getNoiDung().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Trả về 400 nếu nội dung mới rỗng
        }

        binhLuan.setNoiDung(request.getNoiDung());
        BinhLuan binhLuanMoi = this.binhLuanService.suaBinhLuan(binhLuan);

        return ResponseEntity.ok(binhLuanMoi);
    }

    // xóa bình luận 
    @DeleteMapping("/xoabinhluan/id/{id}")
    public void xoaBinhLuanId(@PathVariable Long id){
        this.binhLuanService.xoaBinhLuan(id);
    }
}
