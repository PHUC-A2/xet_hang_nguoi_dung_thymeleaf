package com.example.xephangnguoidung.data.repository;

import com.example.xephangnguoidung.data.entity.DiemNguoiDung;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

public interface DiemNguoiDungRepository extends JpaRepository<DiemNguoiDung, Long> {
    // @Query("SELECT COALESCE(SUM(d.diem), 0) FROM DiemNguoiDung d WHERE d.nguoiDung.id = :nguoiDungId")
    // int tinhTongDiem(@Param("nguoiDungId") Long nguoiDungId);


}
