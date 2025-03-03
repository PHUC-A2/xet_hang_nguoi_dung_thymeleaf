package com.example.xephangnguoidung.data.repository;

import com.example.xephangnguoidung.data.entity.DiemNguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiemNguoiDungRepository extends JpaRepository<DiemNguoiDung, Long> {

    @Query("SELECT SUM(d.diem) FROM DiemNguoiDung d WHERE d.nguoiDung.id = :nguoiDungId")
    Integer tinhTongDiemByNguoiDungId(Long nguoiDungId);
}