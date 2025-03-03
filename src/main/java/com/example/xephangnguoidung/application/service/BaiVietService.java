package com.example.xephangnguoidung.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.enums.LoaiHoatDong;
import com.example.xephangnguoidung.data.repository.BaiVietRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BaiVietService {
    private final BaiVietRepository baiVietRepository;
    private final DiemNguoiDungService diemNguoiDungService;

    public BaiVietService(BaiVietRepository baiVietRepository, DiemNguoiDungService diemNguoiDungService) {
        this.baiVietRepository = baiVietRepository;
        this.diemNguoiDungService = diemNguoiDungService;
    }

    public List<BaiViet> layTatCaBaiViet() {
        return baiVietRepository.findAll();
    }

    public BaiViet layBaiVietById(Long id) {
        return baiVietRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết với ID: " + id));
    }

    public BaiViet luuBaiViet(BaiViet baiViet) {
        BaiViet saveBaiViet = this.baiVietRepository.save(baiViet);
        this.diemNguoiDungService.tinhDiem(baiViet.getNguoiDung().getId(), LoaiHoatDong.VIET_BAI);
        return saveBaiViet;
    }

    public void xoaBaiVietById(Long id) {
        BaiViet baiViet = layBaiVietById(id);
        baiVietRepository.deleteById(id);
        // Trừ điểm khi xóa bài viết
        this.diemNguoiDungService.tinhDiem(baiViet.getNguoiDung().getId(), LoaiHoatDong.VIET_BAI, -1);
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