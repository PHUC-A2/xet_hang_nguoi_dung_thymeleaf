package com.example.xephangnguoidung.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.xephangnguoidung.data.enums.LoaiHoatDong;

/*
id	BIGINT (PK)	Long
nguoi_dung_id	BIGINT (FK)	Long
loai_hoat_dong	VARCHAR(50)	String
diem	INT	int
ngay_tao	TIMESTAMP	LocalDateTime
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DiemNguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //
    @JoinColumn(name = "nguoi_dung_id", nullable = false) // khóa ngoại của NguoiDung, không được null
    private NguoiDung nguoiDung;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // không được null
    private LoaiHoatDong loaiHoatDong = LoaiHoatDong.DANG_NHAP; // mặc định là đăng nhập

    private int diem = 0; // mặc định 0

    @Column(updatable = false) // ngăn chỉnh sửa ngày tạo
    private LocalDateTime ngayTao = LocalDateTime.now(); // lấy ngày hiện tại mặc định

    // ➜ Constructor tùy chỉnh
    public DiemNguoiDung(NguoiDung nguoiDung, int diem) {
        this.nguoiDung = nguoiDung;
        this.diem = diem;
        this.ngayTao = LocalDateTime.now();
    }
}
