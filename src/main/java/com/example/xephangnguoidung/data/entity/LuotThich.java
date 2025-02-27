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
public class LuotThich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // N-1
    @JoinColumn(name = "nguoi_dung_id", nullable = false) // khóa ngoại NguoiDung, không được null
    private NguoiDung nguoiDung;

    @ManyToOne // N-1
    @JoinColumn(name = "bai_viet_id", nullable = false) // khóa ngoại BaiViet, không được null
    private BaiViet baiViet;

    @Column(updatable = false) // ngăn sửa đổi sau khi tạo
    private LocalDateTime thoiGian;

    @PrePersist
    protected void onCreate() {
        this.thoiGian = LocalDateTime.now(); // Tự động đặt ngày khi tạo mới
    }
}