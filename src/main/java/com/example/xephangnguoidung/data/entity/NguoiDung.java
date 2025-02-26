package com.example.xephangnguoidung.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.xephangnguoidung.data.enums.CapBac;
import com.example.xephangnguoidung.data.enums.VaiTro;

@Entity
@Table(name = "nguoi_dung")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tenDangNhap;

    @Column(nullable = false)
    private String matKhau;

    @Column(nullable = false, unique = true)
    private String email;

    private int diem = 0;

    @Enumerated(EnumType.STRING)
    private CapBac capBac = CapBac.DONG;

    @Enumerated(EnumType.STRING)
    private VaiTro vaiTro = VaiTro.USER;

    private int soLanDangNhap = 0;

    @Column(updatable = false) // Ngăn không cho cập nhật lại ngày tạo
    private LocalDateTime ngayTao;

    @PrePersist
    protected void onCreate() {
        this.ngayTao = LocalDateTime.now();
    }
}