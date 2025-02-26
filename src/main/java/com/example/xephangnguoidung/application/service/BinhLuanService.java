package com.example.xephangnguoidung.application.service;

import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.entity.BinhLuan;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.repository.BaiVietRepository;
import com.example.xephangnguoidung.data.repository.BinhLuanRepository;
import com.example.xephangnguoidung.data.repository.NguoiDungRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BinhLuanService {

    private final BinhLuanRepository binhLuanRepository;
    private final BaiVietRepository baiVietRepository;
    private final NguoiDungRepository nguoiDungRepository;

    public BinhLuanService(BinhLuanRepository binhLuanRepository, BaiVietRepository baiVietRepository,
            NguoiDungRepository nguoiDungRepository) {
        this.binhLuanRepository = binhLuanRepository;
        this.baiVietRepository = baiVietRepository;
        this.nguoiDungRepository = nguoiDungRepository;
    }

    // ✅ 1️⃣ Thêm bình luận
    public void themBinhLuan(BinhLuan binhLuan) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(binhLuan.getNguoiDung().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        BaiViet baiViet = baiVietRepository.findById(binhLuan.getBaiViet().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));

        binhLuan.setNguoiDung(nguoiDung);
        binhLuan.setBaiViet(baiViet);

        binhLuanRepository.save(binhLuan);

        // Cập nhật số lượng bình luận trong bảng BaiViet
        baiViet.setSoLuotBinhLuan(baiViet.getSoLuotBinhLuan() + 1);
        baiVietRepository.save(baiViet);
    }

    // ✅ 2️⃣ Xóa bình luận
    public void xoaBinhLuan(Long binhLuanId) {
        BinhLuan binhLuan = binhLuanRepository.findById(binhLuanId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bình luận"));

        BaiViet baiViet = binhLuan.getBaiViet();
        binhLuanRepository.delete(binhLuan);

        // ✅ Cập nhật số lượt bình luận trong bảng BaiViet
        baiViet.setSoLuotBinhLuan(Math.max(0, baiViet.getSoLuotBinhLuan() - 1));
        baiVietRepository.save(baiViet);
    }

    // ✅ 3️⃣ Lấy danh sách bình luận của bài viết
    public List<BinhLuan> layDanhSachBinhLuan(Long baiVietId) {
        BaiViet baiViet = baiVietRepository.findById(baiVietId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));

        return binhLuanRepository.findByBaiViet(baiViet);
    }
}
