package com.example.xephangnguoidung.presentation.controller.user;

import com.example.xephangnguoidung.application.service.LuotThichService;
import com.example.xephangnguoidung.application.service.NguoiDungService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Integer> toggleLike(@PathVariable Long baiVietId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long nguoiDungId = nguoiDungService.layIdNguoiDungHienTai(authentication.getName());
        boolean daLike = luotThichService.kiemTraDaThich(nguoiDungId, baiVietId);

        if (daLike) {
            luotThichService.xoaLuotThich(nguoiDungId, baiVietId);
        } else {
            luotThichService.themLuotThich(nguoiDungId, baiVietId);
        }

        // Lấy lại số lượt thích sau khi cập nhật
        int soLuotThich = luotThichService.demSoLuotThich(baiVietId);
        return ResponseEntity.ok(soLuotThich);
    }

}