package com.example.xephangnguoidung.data.repository;

import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.entity.BinhLuan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BinhLuanRepository extends JpaRepository<BinhLuan, Long> {
    List<BinhLuan> findByBaiViet(BaiViet baiViet);
}
