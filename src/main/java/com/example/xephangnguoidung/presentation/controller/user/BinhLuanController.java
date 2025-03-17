package com.example.xephangnguoidung.presentation.controller.user;

import com.example.xephangnguoidung.application.service.BinhLuanService;
import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.application.service.BaiVietService;
import com.example.xephangnguoidung.data.entity.BinhLuan;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.entity.BaiViet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/binhluan")
public class BinhLuanController {

    private final BinhLuanService binhLuanService;
    private final NguoiDungService nguoiDungService;
    private final BaiVietService baiVietService;

    public BinhLuanController(BinhLuanService binhLuanService, NguoiDungService nguoiDungService,
            BaiVietService baiVietService) {
        this.binhLuanService = binhLuanService;
        this.nguoiDungService = nguoiDungService;
        this.baiVietService = baiVietService;
    }

    // Thêm bình luận
    @PostMapping("/tao")
    public String themBinhLuan(@RequestParam("noiDung") String noiDung,
            @RequestParam("baiVietId") Long baiVietId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap = authentication.getName();
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungByEmail(tenDangNhap); // tên đăng nhập mình dùng email

        BinhLuan binhLuan = new BinhLuan();
        binhLuan.setNoiDung(noiDung);
        binhLuan.setNguoiDung(nguoiDung);

        BaiViet baiViet = baiVietService.timBaiVietTheoId(baiVietId);
        binhLuan.setBaiViet(baiViet);

        binhLuanService.themBinhLuan(nguoiDung.getId(), binhLuan);
        return "redirect:/user/baiviet/tatca";
    }

    // Sửa bình luận
    @PostMapping("/sua/{binhLuanId}")
    public String suaBinhLuan(@PathVariable Long binhLuanId,
            @RequestParam("noiDung") String noiDung) {
        BinhLuan binhLuan = binhLuanService.layBinhLuanById(binhLuanId);
        binhLuan.setNoiDung(noiDung);
        binhLuanService.capNhatBinhLuan(binhLuan);
        return "redirect:/user/baiviet/tatca";
    }

    // Xóa bình luận
    @PostMapping("/xoa/{binhLuanId}")
    public String xoaBinhLuan(@PathVariable Long binhLuanId) {
        this.binhLuanService.xoaBinhLuan(binhLuanId);
        return "redirect:/user/baiviet/tatca";
    }
}