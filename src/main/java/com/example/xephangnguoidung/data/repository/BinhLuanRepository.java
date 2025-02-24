package com.example.xephangnguoidung.data.repository;

import com.example.xephangnguoidung.data.entity.BinhLuan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BinhLuanRepository extends JpaRepository<BinhLuan, Long> {
    List<BinhLuan> findByBaiVietId(Long idBaiViet);
}
