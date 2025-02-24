package com.example.xephangnguoidung.data.repository;

import com.example.xephangnguoidung.data.entity.NguoiDung;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {

    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);
}
