package com.example.xephangnguoidung.presentation.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.xephangnguoidung.application.service.BaiVietService;
import com.example.xephangnguoidung.application.service.BinhLuanService;
import com.example.xephangnguoidung.application.service.DiemNguoiDungService;
import com.example.xephangnguoidung.application.service.NguoiDungService;

@Controller
public class AdminController {
    private final NguoiDungService nguoiDungService;
    private final BaiVietService baiVietService;
    private final BinhLuanService binhLuanService;
    private final DiemNguoiDungService diemNguoiDungService;

    
    public AdminController(NguoiDungService nguoiDungService, BaiVietService baiVietService,
            BinhLuanService binhLuanService, DiemNguoiDungService diemNguoiDungService) {
        this.nguoiDungService = nguoiDungService;
        this.baiVietService = baiVietService;
        this.binhLuanService = binhLuanService;
        this.diemNguoiDungService = diemNguoiDungService;
    }


    @GetMapping("/admin")
    public String trangChuAdmin(Model model) {

        // lấy số lượng người dùng
        Long slNguoiDung = this.nguoiDungService.soLuongNguoiDung();
        model.addAttribute("soLuongNguoiDung", slNguoiDung);

        // lay so luong bai viet
        Long slBaiViet = this.baiVietService.soLuongBaiViet();
        model.addAttribute("soLuongBaiViet", slBaiViet);

        // lay so luong binh luan
        Long slBinhLuan = this.binhLuanService.soLuongBinhLuan();
        model.addAttribute("soLuongBinhLuan", slBinhLuan);


        // lay tong so diem
        Long tongSoDiem = this.diemNguoiDungService.tongSoDiem();
        model.addAttribute("tongSoDiem", tongSoDiem);

        return "admin/trang_chu_admin";
    }
}