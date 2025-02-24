package com.example.xephangnguoidung.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.xephangnguoidung.data.enums.CapBac;
import com.example.xephangnguoidung.data.enums.VaiTro;

/*
id	BIGINT (PK)	Long
ten_dang_nhap	VARCHAR(50)	String
mat_khau	VARCHAR(255)	String
email	VARCHAR(100)	String
diem	INT	int
cap_bac	ENUM
vai_tro	ENUM('USER', 'ADMIN')	String
so_lan_dang_nhap	INT	int
ngay_tao	TIMESTAMP	LocalDateTime
*/
@AllArgsConstructor // constructor đủ
@NoArgsConstructor // constructor không tham số
@Data // get,set,toString tự động
@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // không được null, không được trùng nhau
    private String tenDangNhap;

    @Column(nullable = false) // không được null
    private String matKhau;

    @Column(nullable = false, unique = true) // không được null, không được trùng nhau
    private String email;

    private int diem = 0; // mặc từ 0

    @Enumerated(EnumType.STRING) // Lưu enum dưới dạng String trong DB
    private CapBac capBac = CapBac.DONG; // mặc định Đồng , vế sau sẽ là Vàng,Bạc,Kim Cương,Bạch Kim,Vip

    @Enumerated(EnumType.STRING) // Lưu enum dưới dạng String trong DB
    private VaiTro vaiTro = VaiTro.USER; // mặc định là user

    private int soLanDangNhap = 0; // mặc định là 0

    private LocalDateTime ngayTao = LocalDateTime.now(); //

}
