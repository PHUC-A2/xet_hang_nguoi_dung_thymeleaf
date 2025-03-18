package com.example.xephangnguoidung.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.enums.VaiTro;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    NguoiDung findByTenDangNhap(String tenDangNhap);

    boolean existsByTenDangNhap(String tenDangNhap);

    boolean existsByEmail(String email);

    NguoiDung findByEmail(String email);

    boolean existsByVaiTro(VaiTro vaiTro);

    List<NguoiDung> findByTenDangNhapContainingIgnoreCaseOrEmailContainingIgnoreCase(String tenDangNhap, String email);
}