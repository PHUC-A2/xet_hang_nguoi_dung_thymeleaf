package com.example.xephangnguoidung.data.repository;

import com.example.xephangnguoidung.data.entity.BaiViet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaiVietRepository extends JpaRepository<BaiViet, Long> {
    Optional<BaiViet> findByTieuDe(String tieuDe);
    List<BaiViet> findByNguoiDungId(Long id);
    List<BaiViet> findByTieuDeContainingIgnoreCase(String keyword);
}