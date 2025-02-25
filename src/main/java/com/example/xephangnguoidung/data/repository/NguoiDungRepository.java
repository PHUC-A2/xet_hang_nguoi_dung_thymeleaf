package com.example.xephangnguoidung.data.repository;

import com.example.xephangnguoidung.data.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {

    // ✅ Tìm người dùng theo tên đăng nhập
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);

    // ✅ Tìm người dùng theo email
    Optional<NguoiDung> findByEmail(String email);

    // ✅ Kiểm tra xem email đã tồn tại hay chưa
    boolean existsByEmail(String email);

    // ✅ Kiểm tra xem tên đăng nhập đã tồn tại hay chưa
    boolean existsByTenDangNhap(String tenDangNhap);

    // ✅ Lấy danh sách người dùng theo điểm (bảng xếp hạng)
    List<NguoiDung> findAllByOrderByDiemDesc();
}
