package com.example.xephangnguoidung.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tieuDe;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id", nullable = false)
    private NguoiDung nguoiDung;

    @Column(updatable = false)
    private LocalDateTime ngayDang;

    private int soLuotThich = 0;
    private int soLuotBinhLuan = 0;

    @PrePersist
    protected void onCreate() {
        this.ngayDang = LocalDateTime.now();
    }
}
