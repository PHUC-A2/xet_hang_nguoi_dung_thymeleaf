package com.example.xephangnguoidung.application.config;

import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.enums.VaiTro;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final NguoiDungService nguoiDungService;

    public CustomAuthenticationFilter(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
        // Thêm FailureHandler để xử lý lỗi khi vai trò không hợp lệ
        this.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=true"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String roleString = request.getParameter("vaiTro");

        // Lấy người dùng từ DB
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungByEmail(username);
        if (nguoiDung == null) {
            throw new RuntimeException("Người dùng không tồn tại!");
        }

        // Xác thực mật khẩu trước khi kiểm tra vai trò
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        authentication = this.getAuthenticationManager().authenticate(authentication);

        // Chuyển từ String sang Enum (mặc định USER nếu lỗi)
        VaiTro selectedRole;
        try {
            selectedRole = VaiTro.valueOf(roleString);
        } catch (IllegalArgumentException | NullPointerException e) {
            selectedRole = VaiTro.USER;
        }

        // Kiểm tra vai trò có hợp lệ không
        if (!nguoiDung.getVaiTro().equals(selectedRole)) {
            throw new BadCredentialsException("Vai trò không hợp lệ!"); // Dừng đăng nhập ngay lập tức
        }

        // Nếu đúng, tiếp tục xác thực với quyền
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + selectedRole.name()));
        return new UsernamePasswordAuthenticationToken(username, password, authorities);

    }

}