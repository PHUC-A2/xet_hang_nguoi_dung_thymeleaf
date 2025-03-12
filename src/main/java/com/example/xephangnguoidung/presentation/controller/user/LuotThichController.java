package com.example.xephangnguoidung.presentation.controller.user;

import com.example.xephangnguoidung.application.service.LuotThichService;
import com.example.xephangnguoidung.application.service.NguoiDungService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/luotthich")
public class LuotThichController {

    private final LuotThichService luotThichService;
    private final NguoiDungService nguoiDungService;

    public LuotThichController(LuotThichService luotThichService, NguoiDungService nguoiDungService) {
        this.luotThichService = luotThichService;
        this.nguoiDungService = nguoiDungService;
    }

    @PostMapping("/{baiVietId}")
    public boolean toggleLike(@PathVariable Long baiVietId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Người dùng chưa đăng nhập.");
        }

        Long nguoiDungId = nguoiDungService.layIdNguoiDungHienTai(authentication.getName());
        boolean daLike = luotThichService.kiemTraDaThich(nguoiDungId, baiVietId);

        if (daLike) {
            luotThichService.xoaLuotThich(nguoiDungId, baiVietId);
            return false; // Trả về false nếu unlike
        } else {
            luotThichService.themLuotThich(nguoiDungId, baiVietId);
            return true; // Trả về true nếu like
        }
    }
}