package com.example.xephangnguoidung.presentation.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.xephangnguoidung.application.service.BaiVietService;
import com.example.xephangnguoidung.data.entity.BaiViet;

@RestController
@RequestMapping("/admin/baiviet")
public class QuanLyBaiVietController {
    private final BaiVietService baiVietService;

    public QuanLyBaiVietController(BaiVietService baiVietService) {
        this.baiVietService = baiVietService;
    }

    // tạo bài viết
    @PostMapping("/tao")
    public BaiViet taoBaiViet(@RequestBody BaiViet baiViet) {
        return this.baiVietService.luuBaiViet(baiViet);
    }

    // lấy tất cả bài viết
    @GetMapping("/laytatcabaiviet")
    public List<BaiViet> layTatCaBaiViet() {
        return this.baiVietService.layTatCaBaiViet();
    }

    // lấy bài viết bằng id
    @GetMapping("/laybaiviet/id/{id}")
    public BaiViet layBaiVietById(@PathVariable Long id) {
        return this.baiVietService.layBaiVietById(id);
    }

    // lấy bài viết bằng tiêu đề
    @GetMapping("/laybaiviet/tieuDe/{tieuDe}")
    public BaiViet layBaiVietByTieuDe(@PathVariable String tieuDe) {
        return this.baiVietService.layBaiVietByTieuDe(tieuDe);
    }

    // sửa bài viết bằng id
    /*
     * 
     * id BIGINT (PK) Long
     * tieu_de VARCHAR(255) String
     * noi_dung TEXT String
     * nguoi_dung_id BIGINT (FK) Long
     * ngay_dang TIMESTAMP LocalDateTime
     * so_luot_thich INT int
     * so_luot_binh_luan INT int
     * 
     */

     @PutMapping("/suabaiviet/id/{id}")
     public ResponseEntity<BaiViet> suaBaiVietById(@PathVariable Long id ,@RequestBody BaiViet request){

        BaiViet baiViet = this.layBaiVietById(id);

        if(baiViet == null){
            return ResponseEntity.notFound().build(); // trả về 404 nếu không tìm thấy bài viết
        }
        baiViet.setTieuDe(request.getTieuDe());
        baiViet.setNoiDung(request.getNoiDung());

        BaiViet baiVietMoi = this.baiVietService.suaBaiVietById(baiViet);
        return ResponseEntity.ok(baiVietMoi);
     }

    // xóa bài viết bằng id
    @DeleteMapping("/xoabaiviet/id/{id}")
    public void xoaBaiVietById(@PathVariable Long id) {
        this.baiVietService.xoaBaiVietById(id);
    }
}
