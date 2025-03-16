package com.example.xephangnguoidung.presentation.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.xephangnguoidung.application.service.BaiVietService;
import com.example.xephangnguoidung.application.service.BinhLuanService;
import com.example.xephangnguoidung.application.service.LuotThichService;
import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.entity.BinhLuan;

@Controller
@RequestMapping("/user")
public class TestController {
    private final BaiVietService baiVietService;
    private final LuotThichService luotThichService;
    private final BinhLuanService binhLuanService;

    public TestController(BaiVietService baiVietService, LuotThichService luotThichService,
            BinhLuanService binhLuanService) {
        this.baiVietService = baiVietService;
        this.luotThichService = luotThichService;
        this.binhLuanService = binhLuanService;
    }

    @GetMapping("/test/baiviet")
    public String hienThiTatCaBaiViet(Model model) {
        List<BaiViet> danhSachBaiViet = baiVietService.layTatCaBaiViet();
        for (BaiViet baiViet : danhSachBaiViet) {
            int soLuotThich = luotThichService.demSoLuotThich(baiViet.getId());
            baiViet.setSoLuotThich(soLuotThich);
        }
        model.addAttribute("danhSachBaiViet", danhSachBaiViet);
        return "user/testds_baiviet_binhluan";
    }

    @GetMapping("/test/binhluan")
    public String hienThiTatCaBinhLuan(Model model, BaiViet baiViet) {
        List<BinhLuan> danhSachBinhLuan = this.binhLuanService.layDanhSachBinhLuan(baiViet.getId());
        model.addAttribute("danhSachBinhLuan", danhSachBinhLuan);
        return "user/testds_baiviet_binhluan";
    }

}
