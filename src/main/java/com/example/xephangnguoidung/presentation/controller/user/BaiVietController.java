package com.example.xephangnguoidung.presentation.controller.user;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.xephangnguoidung.application.service.BaiVietService;
import com.example.xephangnguoidung.application.service.BinhLuanService;
import com.example.xephangnguoidung.application.service.LuotThichService;
import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.presentation.controller.admin.QuanLyBaiVietController;

@Controller
@RequestMapping("/user/baiviet")
public class BaiVietController {

    private static final Logger logger = LoggerFactory.getLogger(QuanLyBaiVietController.class);
    private final NguoiDungService nguoiDungService;
    private final BaiVietService baiVietService;
    private final LuotThichService luotThichService;
    private final BinhLuanService binhLuanService;


    public BaiVietController(NguoiDungService nguoiDungService, BaiVietService baiVietService,
            LuotThichService luotThichService, BinhLuanService binhLuanService) {
        this.nguoiDungService = nguoiDungService;
        this.baiVietService = baiVietService;
        this.luotThichService = luotThichService;
        this.binhLuanService = binhLuanService;

    }

    @PostMapping("/tao")
    public String taoBaiViet(@RequestParam Long nguoiDungId, BaiViet baiViet) {
        try {
            logger.info("Bắt đầu tạo bài viết với ID người dùng: " + nguoiDungId);

            // Lấy thông tin người dùng từ ID
            NguoiDung nguoiDung = nguoiDungService.layNguoiDungById(nguoiDungId);
            if (nguoiDung == null) {
                logger.error("Không tìm thấy người dùng với ID: " + nguoiDungId);
                throw new RuntimeException("Không tìm thấy người dùng!");
            }

            // Gán người dùng vào bài viết
            baiViet.setNguoiDung(nguoiDung);
            logger.info("Đã gán người dùng vào bài viết");

            // Lưu bài viết vào database
            baiVietService.luuBaiViet(baiViet);
            logger.info("Đã lưu bài viết vào database");

            return "redirect:/user/baiviet/tatca"; // Corrected redirect URL
        } catch (Exception e) {
            logger.error("Lỗi khi tạo bài viết", e);
            return "redirect:/error";
        }
    }

    // sửa
    @GetMapping("/sua/{id}")
    public String hienThiFormSua(@PathVariable Long id, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietById(id);
        model.addAttribute("baiViet", baiViet);
        return "user/sua_baiviet";
    }

    @PostMapping("/sua/{id}")
    public String suaBaiViet(@PathVariable Long id, BaiViet baiViet) {
        BaiViet baiVietHienTai = baiVietService.layBaiVietById(id);
        baiVietHienTai.setTieuDe(baiViet.getTieuDe());
        baiVietHienTai.setNoiDung(baiViet.getNoiDung());
        baiVietService.luuBaiViet(baiVietHienTai);
        return "redirect:/user/baiviet/tatca";
    }

    @PostMapping("/xoa/{id}")
    public String xoaBaiViet(@PathVariable Long id) {
        baiVietService.xoaBaiVietById(id);
        return "redirect:/user/baiviet/tatca";
    }

    @GetMapping("/chitiet/{id}")
    public String hienThiChiTietBaiViet(@PathVariable Long id, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietById(id);
        model.addAttribute("baiViet", baiViet);
        return "user/chitiet_baiviet";
    }

    @GetMapping("/tatca")
    public String hienThiTatCaBaiViet(Model model) {
        List<BaiViet> danhSachBaiViet = this.baiVietService.layTatCaBaiViet();
        for (BaiViet baiViet : danhSachBaiViet) {
            int soLuotThich = this.luotThichService.demSoLuotThich(baiViet.getId());
            int soLuotBinhLuan = this.binhLuanService.demSoLuotBinhLuan(baiViet.getId());

            System.out.println("Bài viết ID: " + baiViet.getId());
            System.out.println("Lượt thích: " + soLuotThich);
            System.out.println("Lượt bình luận: " + soLuotBinhLuan);

            baiViet.setSoLuotThich(soLuotThich);
            baiViet.setSoLuotBinhLuan(soLuotBinhLuan);
        }
       
        model.addAttribute("danhSachBaiViet", danhSachBaiViet);
        return "user/hoatdong_nguoidung";
    }

    @GetMapping("/timkiem")
    public String timKiemBaiViet(@RequestParam("keyword") String keyword, Model model) {
        List<BaiViet> danhSachBaiViet = baiVietService.timKiemBaiViet(keyword);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        model.addAttribute("danhSachBaiViet", danhSachBaiViet);
        model.addAttribute("formatter", formatter);
        model.addAttribute("baiViet", new BaiViet());
        return "user/hoatdong_nguoidung";
    }
    // lấy tất cả bài viết thuộc về USER hiện tại
    @GetMapping("/tatcabaiviethientai")
    public String hienThiTatCaBaiVietHienTai(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        NguoiDung nguoiDung = this.nguoiDungService.getNguoiDungByEmail(username);
        if (nguoiDung != null) {
            Long nguoiDungId = nguoiDung.getId();
            List<BaiViet> danhSachBaiViet = baiVietService.layTatCaBaiVietById(nguoiDungId);
            model.addAttribute("danhSachBaiViet", danhSachBaiViet);
            return "user/danhsach_chitiet_baiviet";
        } else {
            return "error";
        }

    }
}
