package com.example.xephangnguoidung.presentation.controller.admin;

import com.example.xephangnguoidung.application.service.BinhLuanService;
import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.application.service.BaiVietService;
import com.example.xephangnguoidung.data.entity.BinhLuan;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.entity.BaiViet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/binhluan")
public class QuanLyBinhLuanController {

    private final BinhLuanService binhLuanService;
    private final NguoiDungService nguoiDungService;
    private final BaiVietService baiVietService;

    public QuanLyBinhLuanController(BinhLuanService binhLuanService, NguoiDungService nguoiDungService,
            BaiVietService baiVietService) {
        this.binhLuanService = binhLuanService;
        this.nguoiDungService = nguoiDungService;
        this.baiVietService = baiVietService;
    }

    // Thêm bình luận
    @PostMapping("/tao")
    public String themBinhLuan(@RequestParam("noiDung") String noiDung,
            @RequestParam("nguoiDungId") Long nguoiDungId,
            @RequestParam("baiVietId") Long baiVietId) {
        BinhLuan binhLuan = new BinhLuan();
        binhLuan.setNoiDung(noiDung);

        NguoiDung nguoiDung = nguoiDungService.layNguoiDungById(nguoiDungId);
        binhLuan.setNguoiDung(nguoiDung);

        BaiViet baiViet = baiVietService.timBaiVietTheoId(baiVietId);
        binhLuan.setBaiViet(baiViet);

        binhLuanService.themBinhLuan(binhLuan);
        return "redirect:/admin/binhluan";
    }

    // Sửa bình luận
    @PostMapping("/sua/id/{binhLuanId}")
    public String suaBinhLuan(@PathVariable Long binhLuanId,
            @RequestParam("noiDung") String noiDung) {
        BinhLuan binhLuan = binhLuanService.layBinhLuanById(binhLuanId);
        binhLuan.setNoiDung(noiDung);
        binhLuanService.capNhatBinhLuan(binhLuan);
        return "redirect:/admin/binhluan";
    }

    // Xóa bình luận
    @PostMapping("/xoabinhluan/{binhLuanId}")
    public String xoaBinhLuan(@PathVariable Long binhLuanId) {
        binhLuanService.xoaBinhLuan(binhLuanId);
        return "redirect:/admin/binhluan";
    }

    // Lấy danh sách bình luận của bài viết
    @GetMapping("/baiviet/{baiVietId}")
    public ResponseEntity<List<BinhLuan>> layDanhSachBinhLuan(@PathVariable Long baiVietId) {
        List<BinhLuan> danhSachBinhLuan = binhLuanService.layDanhSachBinhLuan(baiVietId);
        return ResponseEntity.ok(danhSachBinhLuan);
    }

    // Hiển thị danh sách bình luận
    @GetMapping
    public String hienThiDanhSachBinhLuan(Model model) {
        List<BinhLuan> danhSachBinhLuan = binhLuanService.layTatCaBinhLuan();
        model.addAttribute("danhSachBinhLuan", danhSachBinhLuan);
        return "admin/quanly_binhluan";
    }

    // Tìm kiếm bình luận
    @GetMapping("/timkiem")
    public String timKiemBinhLuan(@RequestParam("keyword") String keyword, Model model) {
        List<BinhLuan> danhSachBinhLuan = binhLuanService.timKiemBinhLuan(keyword);
        model.addAttribute("danhSachBinhLuan", danhSachBinhLuan);
        return "admin/quanly_binhluan";
    }

    // ---- THÊM ENDPOINT LẤY BÌNH LUẬN THEO ID CHO MODAL SỬA ----
    @GetMapping("/laybinhluan/id/{binhLuanId}")
    @ResponseBody
    public BinhLuan layBinhLuanTheoId(@PathVariable Long binhLuanId) {
        return binhLuanService.layBinhLuanById(binhLuanId);
    }
}
