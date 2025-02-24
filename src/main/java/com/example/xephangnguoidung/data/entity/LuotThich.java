package com.example.xephangnguoidung.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
id	BIGINT (PK)	Long
nguoi_dung_id	BIGINT (FK)	Long
bai_viet_id	BIGINT (FK)	Long
thoi_gian	TIMESTAMP	LocalDateTime

 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class LuotThich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // N-1
    @JoinColumn(name = "nguoi_dung_id" , nullable = false) // khóa ngoại NguoiDung , không được null
    private NguoiDung nguoiDung;

    @ManyToOne // N-1
    @JoinColumn(name = "bai_viet_id",nullable = false) // khóa ngoại BaiViet , không được null
    private BaiViet baiViet;

    @Column(updatable = false) // ngăn sửa thời gian
    private LocalDateTime thoiGian = LocalDateTime.now(); // mặc định hiện tại

}
