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
public class BinhLuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id", nullable = false)
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "bai_viet_id", nullable = false)
    private BaiViet baiViet;

    @Column(updatable = false) // Không cho phép sửa ngày đăng
    private LocalDateTime ngayDang;

    // Tự động gán thời gian khi tạo bình luận mới
    @PrePersist
    protected void prePersist() {
        this.ngayDang = LocalDateTime.now();
    }
}

/*
 * id BIGINT (PK) Long
 * noi_dung NVARCHAR(MAX) String
 * nguoi_dung_id BIGINT (FK) Long
 * bai_viet_id BIGINT (FK) Long
 * ngay_dang TIMESTAMP LocalDateTime
 * 
 */