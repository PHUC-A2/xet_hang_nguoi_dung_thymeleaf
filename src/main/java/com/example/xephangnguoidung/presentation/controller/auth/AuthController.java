package com.example.xephangnguoidung.presentation.controller.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.entity.dto.RegisterDTO;
import com.example.xephangnguoidung.data.enums.VaiTro;

@Controller
public class AuthController {

    private NguoiDungService nguoiDungService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(NguoiDungService nguoiDungService, PasswordEncoder passwordEncoder) {
        this.nguoiDungService = nguoiDungService;
        this.passwordEncoder = passwordEncoder;
    }

    // register(đăng ký)
    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("registerNguoiDung", new RegisterDTO());
        model.addAttribute("danhSachVaiTro", VaiTro.values());
        return "auth/register"; // trả về trang đăng ký
    }

    @PostMapping("/register")
    public String getRegister(@ModelAttribute("registerNguoiDung") RegisterDTO registerDTO) {
        NguoiDung nguoiDung = this.nguoiDungService.registerDTOtoNguoiDung(registerDTO); // xử dụng DTO

        // hash code
        String hashPassword = this.passwordEncoder.encode(nguoiDung.getMatKhau());
        nguoiDung.setMatKhau(hashPassword);

        nguoiDung.setVaiTro(nguoiDung.getVaiTro());
        this.nguoiDungService.luuNguoiDung(nguoiDung);

        System.out.println("Thong tin nguoi dung la:");
        System.out.println(nguoiDung.toString());
        System.out.println("Mat khau la: " + hashPassword);
        return "redirect:/login"; // trả về trang đăng nhập nếu đăng ký thành công
    }

    // Đăng nhập
    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("danhSachVaiTro", VaiTro.values()); // Gửi danh sách vai trò xuống trang login.html
        return "auth/login"; // Trả về trang đăng nhập
    }
}
