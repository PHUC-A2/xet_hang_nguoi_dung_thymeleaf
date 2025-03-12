package com.example.xephangnguoidung.data.repository;

import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.entity.LuotThich;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LuotThichRepository extends JpaRepository<LuotThich, Long> {
    boolean existsByNguoiDungAndBaiViet(NguoiDung nguoiDung, BaiViet baiViet);

    Optional<LuotThich> findByNguoiDungAndBaiViet(NguoiDung nguoiDung, BaiViet baiViet);

    List<LuotThich> findByBaiViet(BaiViet baiViet);

    int countAllBy();

    List<LuotThich> findByNguoiDung_TenDangNhapContainingIgnoreCaseOrBaiViet_TieuDeContainingIgnoreCase(
            String tenDangNhap, String tieuDe);

    boolean existsByNguoiDungIdAndBaiVietId(Long nguoiDungId, Long baiVietId);

}