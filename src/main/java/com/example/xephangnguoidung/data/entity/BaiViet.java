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
tieu_de	VARCHAR(255)	String
noi_dung	TEXT	String
nguoi_dung_id	BIGINT (FK)	Long
ngay_dang	TIMESTAMP	LocalDateTime
so_luot_thich	INT	int
so_luot_binh_luan	INT	int

*/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // không được trống
    private String tieuDe;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)") // Hỗ trợ Unicode và dữ liệu lớn
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id", nullable = false) // khóa ngoại trong DB , không được null
    private NguoiDung nguoiDung;

    @Column(updatable = false) // ngăn sửa đổi ngày đăng
    private LocalDateTime ngayDang = LocalDateTime.now(); // lấy ngày hiện tại làm mặc định

    private int soLuotThich = 0;// mặc định 0

    private int soLuotBinhLuan = 0;

}
