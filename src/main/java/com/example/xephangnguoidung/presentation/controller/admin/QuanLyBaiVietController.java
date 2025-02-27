package com.example.xephangnguoidung.presentation.controller.admin;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.xephangnguoidung.application.service.BaiVietService;
import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.entity.NguoiDung;

@Controller
@RequestMapping("/admin/baiviet")
public class QuanLyBaiVietController {
    private final BaiVietService baiVietService;
    private final NguoiDungService nguoiDungService;

    public QuanLyBaiVietController(BaiVietService baiVietService, NguoiDungService nguoiDungService) {
        this.baiVietService = baiVietService;
        this.nguoiDungService = nguoiDungService;
    }

    @GetMapping
    public String hienThiDanhSachBaiViet(Model model) {
        List<BaiViet> danhSachBaiViet = baiVietService.layTatCaBaiViet();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        model.addAttribute("danhSachBaiViet", danhSachBaiViet);
        model.addAttribute("formatter", formatter);
        model.addAttribute("baiViet", new BaiViet());
        return "admin/quanly_baiviet";
    }

    @PostMapping("/tao")
    public String taoBaiViet(BaiViet baiViet) {
        // Giả sử bạn có một người dùng mặc định hoặc lấy người dùng từ session
        NguoiDung nguoiDung = nguoiDungService.layNguoiDungById(1L); // Thay thế 1L bằng ID người dùng hợp lệ
        baiViet.setNguoiDung(nguoiDung);
        baiVietService.luuBaiViet(baiViet);
        return "redirect:/admin/baiviet";
    }

    @GetMapping("/sua/{id}")
    public String hienThiFormSua(@PathVariable Long id, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietById(id);
        model.addAttribute("baiViet", baiViet);
        return "admin/sua_baiviet";
    }

    @PostMapping("/sua/{id}")
    public String suaBaiViet(@PathVariable Long id, BaiViet baiViet) {
        BaiViet baiVietHienTai = baiVietService.layBaiVietById(id);
        baiVietHienTai.setTieuDe(baiViet.getTieuDe());
        baiVietHienTai.setNoiDung(baiViet.getNoiDung());
        baiVietService.luuBaiViet(baiVietHienTai);
        return "redirect:/admin/baiviet";
    }

    @PostMapping("/xoa/{id}")
    public String xoaBaiViet(@PathVariable Long id) {
        baiVietService.xoaBaiVietById(id);
        return "redirect:/admin/baiviet";
    }

    @GetMapping("/timkiem")
    public String timKiemBaiViet(@RequestParam("keyword") String keyword, Model model) {
        List<BaiViet> danhSachBaiViet = baiVietService.timKiemBaiViet(keyword);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        model.addAttribute("danhSachBaiViet", danhSachBaiViet);
        model.addAttribute("formatter", formatter);
        model.addAttribute("baiViet", new BaiViet());
        return "admin/quanly_baiviet";
    }

    @GetMapping("/chitiet/{id}")
    public String hienThiChiTietBaiViet(@PathVariable Long id, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietById(id);
        model.addAttribute("baiViet", baiViet);
        return "admin/chitiet_baiviet";
    }
}