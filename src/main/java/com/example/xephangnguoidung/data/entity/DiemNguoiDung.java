package com.example.xephangnguoidung.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.xephangnguoidung.data.enums.LoaiHoatDong;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DiemNguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id", nullable = false)
    private NguoiDung nguoiDung;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoaiHoatDong loaiHoatDong;

    private int diem = 0;

    @Column(updatable = false)
    private LocalDateTime ngayTao;

    @PrePersist
    protected void onCreate() {
        this.ngayTao = LocalDateTime.now();
    }

    public void tangDiem(int diem) {
        this.diem += diem;

    }
}
