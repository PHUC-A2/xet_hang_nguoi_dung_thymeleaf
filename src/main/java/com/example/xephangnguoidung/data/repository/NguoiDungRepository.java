package com.example.xephangnguoidung.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.xephangnguoidung.data.entity.NguoiDung;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);

    // Optional<NguoiDung> findByEmail(String email);

    boolean existsByTenDangNhap(String tenDangNhap);

    boolean existsByEmail(String email);

    NguoiDung findByEmail(String email);


    List<NguoiDung> findByTenDangNhapContainingIgnoreCaseOrEmailContainingIgnoreCase(String tenDangNhap, String email);
}