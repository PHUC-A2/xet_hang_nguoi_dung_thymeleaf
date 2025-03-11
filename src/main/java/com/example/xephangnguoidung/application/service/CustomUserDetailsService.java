package com.example.xephangnguoidung.application.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.xephangnguoidung.data.entity.NguoiDung;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final NguoiDungService nguoiDungService;

    public CustomUserDetailsService(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDung nguoiDung = this.nguoiDungService.getNguoiDungByEmail(username);

        if (nguoiDung == null) {
            throw new UsernameNotFoundException("Người dùng không tồn tại");
        }

        // Lấy vai trò
        String role = "ROLE_" + nguoiDung.getVaiTro().name();
        System.out.println("Người dùng đăng nhập: " + username + " có quyền: " + role);

        return new User(nguoiDung.getEmail(), nguoiDung.getMatKhau(),
                Collections.singletonList(new SimpleGrantedAuthority(role)));
    }

}