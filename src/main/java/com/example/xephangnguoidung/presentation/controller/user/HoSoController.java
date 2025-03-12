package com.example.xephangnguoidung.presentation.controller.user;

import com.example.xephangnguoidung.application.service.DiemNguoiDungService;
import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HoSoController {
    private final NguoiDungService nguoiDungService;
    private final DiemNguoiDungService diemNguoiDungService;

    public HoSoController(NguoiDungService nguoiDungService, DiemNguoiDungService diemNguoiDungService) {
        this.nguoiDungService = nguoiDungService;
        this.diemNguoiDungService = diemNguoiDungService;
    }

    @GetMapping("/user/hoso")
    public String getHoSo(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        NguoiDung nguoiDung = this.nguoiDungService.getNguoiDungByEmail(username);
        if (nguoiDung != null) {
            Long nguoiDungId = nguoiDung.getId();
            System.out.println("ID Nguoi Dung: " + nguoiDungId); // Kiểm tra ID trên console

            model.addAttribute("nguoiDung", nguoiDung);
            model.addAttribute("nguoiDungId", nguoiDungId); // Thêm ID vào model

            int tongDiem = diemNguoiDungService.tinhTongDiemByNguoiDungId(nguoiDung.getId());
            model.addAttribute("tongDiem", tongDiem);
            return "user/hoso_nguoidung";
        } else {
            return "error";
        }
    }

}