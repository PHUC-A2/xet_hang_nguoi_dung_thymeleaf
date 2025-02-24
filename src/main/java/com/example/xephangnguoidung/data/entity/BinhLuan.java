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
noi_dung	NVARCHAR(MAX)	String
nguoi_dung_id	BIGINT (FK)	Long
bai_viet_id	BIGINT (FK)	Long
ngay_dang	TIMESTAMP	LocalDateTime

*/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BinhLuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , columnDefinition = "NVARCHAR(MAX)") // không được null,hỗ trợ Unicode và dữ liệu lớn
    private String noiDung;

    @ManyToOne // nhiều bình luận thuộc về 1 người dùng
    @JoinColumn(name = "nguoi_dung_id",nullable = false) // khóa ngoại , không đuọc null
    private NguoiDung nguoiDung;
    
    @ManyToOne // nhiều bình luận thuộc về 1 bài viết
    @JoinColumn(name = "bai_viet_id" , nullable = false) // khóa ngoại của bài viết , không được null
    private BaiViet baiViet;

    @Column(updatable = false) // ngăn sửa đổi ngày đăng
    private LocalDateTime ngayDang = LocalDateTime.now(); // lấy ngày hiện tại mặc định

}
