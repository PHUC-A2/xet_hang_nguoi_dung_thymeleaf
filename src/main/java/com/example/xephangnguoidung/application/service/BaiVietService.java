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
    
    // tạo bài viết
    public BaiViet luuBaiViet(BaiViet baiViet){
        return this.baiVietRepository.save(baiViet);
    }

    // lấy bài viết bằng ID
    public BaiViet layBaiVietById(Long id){
        return this.baiVietRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết có id: " + id));
    }

    // lấy bài viết bằng ID
    public BaiViet layBaiVietByTieuDe(String tieuDe) {
        return this.baiVietRepository.findByTieuDe(tieuDe)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết có tiêu đề là: " + tieuDe));
    }

    // lấy tất cả bài viết 
    public List<BaiViet> layTatCaBaiViet(){
        return this.baiVietRepository.findAll();
    }

    // sửa bài viết bằng id
    public BaiViet suaBaiVietById(BaiViet baiVietMoi) {
        if (baiVietMoi.getId() == null) {
            throw new IllegalArgumentException("ID không được để trống khi cập nhật!");
        }

        // lấy bài viết hiện tại từ database
        BaiViet baiVietCu = this.baiVietRepository.findById(baiVietMoi.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết có ID: " + baiVietMoi.getId()));

        // giữ nguyên ngày đăng cũ
        baiVietMoi.setNgayDang(baiVietCu.getNgayDang());

        return this.baiVietRepository.save(baiVietMoi);
    }

    // xóa bài viết bằng id
    public void xoaBaiVietById(Long id){
        this.baiVietRepository.deleteById(id);
    } 
    
}
