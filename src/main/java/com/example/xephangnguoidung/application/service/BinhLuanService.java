package com.example.xephangnguoidung.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.xephangnguoidung.data.entity.BinhLuan;
import com.example.xephangnguoidung.data.repository.BaiVietRepository;
import com.example.xephangnguoidung.data.repository.BinhLuanRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BinhLuanService {
    private final BinhLuanRepository binhLuanRepository;
    private final BaiVietRepository baiVietRepository;

    // Constructor
    public BinhLuanService(BinhLuanRepository binhLuanRepository, BaiVietRepository baiVietRepository) {
        this.binhLuanRepository = binhLuanRepository;
        this.baiVietRepository = baiVietRepository;
    }

    // Tạo bình luận
    public BinhLuan luuBinhLuan(BinhLuan binhLuan) {
        if (binhLuan.getNoiDung() == null || binhLuan.getNoiDung().trim().isEmpty()) {
            throw new IllegalArgumentException("Nội dung bình luận không được để trống!");
        }
        return this.binhLuanRepository.save(binhLuan);
    }

    // Lấy bình luận bằng ID
    public BinhLuan layBinhLuanById(Long id) {
        return this.binhLuanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bình luận có ID: " + id));
    }

    // Lấy tất cả bình luận
    public List<BinhLuan> layTatCaBinhLuan() {
        return this.binhLuanRepository.findAll();
    }

    // Lấy tất cả bình luận theo bài viết
    public List<BinhLuan> layTatCaBinhLuanTheoBaiViet(Long idBaiViet) {
        if (idBaiViet == null) {
            throw new IllegalArgumentException("ID bài viết không được để trống!");
        }

        // Kiểm tra bài viết có tồn tại không
        if (!this.baiVietRepository.existsById(idBaiViet)) {
            throw new RuntimeException("Không tìm thấy bài viết có ID: " + idBaiViet);
        }
        return this.binhLuanRepository.findByBaiVietId(idBaiViet);
    }

    // Sửa bình luận
    public BinhLuan suaBinhLuan(BinhLuan binhLuan) {
        if (binhLuan.getId() == null) {
            throw new IllegalArgumentException("ID bình luận không được để trống khi cập nhật!");
        }

        BinhLuan binhLuanHienTai = this.binhLuanRepository.findById(binhLuan.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bình luận có ID: " + binhLuan.getId()));

        // Kiểm tra nội dung mới không rỗng rồi mới cập nhật
        if (binhLuan.getNoiDung() != null && !binhLuan.getNoiDung().trim().isEmpty()) {
            binhLuanHienTai.setNoiDung(binhLuan.getNoiDung());
        }

        return this.binhLuanRepository.save(binhLuanHienTai);
    }

    // Xóa bình luận
    public void xoaBinhLuan(Long id) {
        if (!this.binhLuanRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy bình luận có ID: " + id);
        }
        this.binhLuanRepository.deleteById(id);
    }
}
