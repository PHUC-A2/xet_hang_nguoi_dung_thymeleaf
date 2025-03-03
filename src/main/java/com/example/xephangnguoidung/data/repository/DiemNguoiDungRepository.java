package com.example.xephangnguoidung.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.xephangnguoidung.data.entity.DiemNguoiDung;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.enums.LoaiHoatDong;

import java.util.Optional;

public interface DiemNguoiDungRepository extends JpaRepository<DiemNguoiDung, Long> {

    Optional<DiemNguoiDung> findByNguoiDungAndLoaiHoatDong(NguoiDung nguoiDung, LoaiHoatDong loaiHoatDong);

    @Query("SELECT SUM(d.diem) FROM DiemNguoiDung d WHERE d.nguoiDung.id = :nguoiDungId")
    Integer tinhTongDiemByNguoiDungId(@Param("nguoiDungId") Long nguoiDungId);
}
