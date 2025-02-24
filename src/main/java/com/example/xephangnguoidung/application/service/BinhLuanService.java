package com.example.xephangnguoidung.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.entity.BinhLuan;
import com.example.xephangnguoidung.data.repository.BaiVietRepository;
import com.example.xephangnguoidung.data.repository.BinhLuanRepository;

import jakarta.transaction.Transactional;

/*

id	BIGINT (PK)	Long
noi_dung	NVARCHAR(MAX)	String
nguoi_dung_id	BIGINT (FK)	Long
bai_viet_id	BIGINT (FK)	Long
ngay_dang	TIMESTAMP	LocalDateTime

*/
@Service
@Transactional
public class BinhLuanService {
    private final BinhLuanRepository binhLuanRepository; 
    private final BaiVietRepository baiVietRepository;
 
    // constructor
    public BinhLuanService(BinhLuanRepository binhLuanRepository, BaiVietRepository baiVietRepository) {
        this.binhLuanRepository = binhLuanRepository;
        this.baiVietRepository = baiVietRepository;
    }

    // tạo bình luận
    public BinhLuan luuBinhLuan(BinhLuan binhLuan){
        return this.binhLuanRepository.save(binhLuan);
    }

    // lấy bình luận bằng ID
    public BinhLuan layBinhLuanById(Long id){
        return this.binhLuanRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy bình luận nào có id: "+id));
    }

    // lấy tất cả bình luận
    public List<BinhLuan> layTatCaBinhLuan(){
        return this.binhLuanRepository.findAll();
    }


    // lấy tấ cả bình luận theo bài viết 
    public List<BinhLuan> layTatCaBinhLuanTheoBaiViet(BaiViet baiViet){
        if(baiViet.getId() == null){
            throw new IllegalArgumentException("ID bài viết không được để trống!");
        }
        
        // kiểm tra bài viết xem có tồn tại không 
        if(!this.baiVietRepository.existsById(baiViet.getId())){
            throw new RuntimeException("Không tìm thấy bài viết có ID: "+baiViet.getId());
        }
        return this.binhLuanRepository.findByBaiVietId(baiViet.getId());
    }

    // sửa bình luận 
    public BinhLuan suaBinhLuan(BinhLuan binhLuan){
        if(binhLuan.getId() == null){
            throw new IllegalArgumentException("ID bình luận không được để trống khi cập nhật!");
        }if(!this.binhLuanRepository.existsById(binhLuan.getId())){
            throw new RuntimeException("Không tìm thấy bình luận có ID: "+binhLuan.getId());
        }
        return this.binhLuanRepository.save(binhLuan);
    }

    // xóa bình luận 
    public void xoaBinhLuan(Long id){
        this.binhLuanRepository.deleteById(id);
    }
}
