package com.example.xephangnguoidung.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.repository.BaiVietRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BaiVietService {
    private final BaiVietRepository baiVietRepository;

    public BaiVietService(BaiVietRepository baiVietRepository) {
        this.baiVietRepository = baiVietRepository;
    }

    public List<BaiViet> layTatCaBaiViet() {
        return baiVietRepository.findAll();
    }

    public BaiViet layBaiVietById(Long id) {
        return baiVietRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết với ID: " + id));
    }

    public BaiViet luuBaiViet(BaiViet baiViet) {
        return baiVietRepository.save(baiViet);
    }

    public void xoaBaiVietById(Long id) {
        baiVietRepository.deleteById(id);
    }

    public List<BaiViet> timKiemBaiViet(String keyword) {
        return baiVietRepository.findByTieuDeContainingIgnoreCase(keyword);
    }

    // Thêm phương thức timBaiVietTheoId
    public BaiViet timBaiVietTheoId(Long id) {
        return layBaiVietById(id);
    }

    // lay so luong bai viet
    public Long soLuongBaiViet() {
        return baiVietRepository.count();
    }
}