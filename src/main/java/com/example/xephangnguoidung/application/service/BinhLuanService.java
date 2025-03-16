package com.example.xephangnguoidung.application.service;

import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.entity.BinhLuan;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.enums.LoaiHoatDong;
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
    private final DiemNguoiDungService diemNguoiDungService;

    public BinhLuanService(BinhLuanRepository binhLuanRepository, BaiVietRepository baiVietRepository,
            NguoiDungRepository nguoiDungRepository, DiemNguoiDungService diemNguoiDungService) {
        this.binhLuanRepository = binhLuanRepository;
        this.baiVietRepository = baiVietRepository;
        this.nguoiDungRepository = nguoiDungRepository;
        this.diemNguoiDungService = diemNguoiDungService;
    }

    // Thêm bình luận doi voi admin
    @Transactional
    public BinhLuan themBinhLuan(Long nguoiDungId, BinhLuan binhLuan) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(nguoiDungId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + nguoiDungId));

        binhLuan.setNguoiDung(nguoiDung);
        BinhLuan binhLuanMoi = binhLuanRepository.save(binhLuan);

        // Gọi phương thức tinhDiem để cập nhật điểm và cấp bậc
        diemNguoiDungService.tinhDiem(nguoiDungId, LoaiHoatDong.BINH_LUAN);

        return binhLuanMoi;
    }

    // // Thêm bình luận đối với user
    // @Transactional
    // public BinhLuan themBinhLuanDoiVoi(Long nguoiDungId, Long baiVietId, BinhLuan binhLuan)

    // Xóa bình luận
    public void xoaBinhLuan(Long binhLuanId) {
        BinhLuan binhLuan = binhLuanRepository.findById(binhLuanId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bình luận"));

        BaiViet baiViet = binhLuan.getBaiViet();
        binhLuanRepository.delete(binhLuan);

        // Cập nhật số lượt bình luận trong bảng BaiViet
        baiViet.setSoLuotBinhLuan(Math.max(0, baiViet.getSoLuotBinhLuan() - 1));
        baiVietRepository.save(baiViet);

        // Trừ điểm khi xóa bình luận
        diemNguoiDungService.tinhDiem(binhLuan.getNguoiDung().getId(), LoaiHoatDong.BINH_LUAN, -1);
    }

    // Lấy danh sách bình luận của bài viết
    public List<BinhLuan> layDanhSachBinhLuan(Long baiVietId) {
        BaiViet baiViet = baiVietRepository.findById(baiVietId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));

        return binhLuanRepository.findByBaiViet(baiViet);
    }

    // Lấy tất cả bình luận
    public List<BinhLuan> layTatCaBinhLuan() {
        return binhLuanRepository.findAll();
    }

    // Tìm kiếm bình luận
    public List<BinhLuan> timKiemBinhLuan(String keyword) {
        return binhLuanRepository.findByNoiDungContainingIgnoreCase(keyword);
    }

    // Lấy bình luận theo ID
    public BinhLuan layBinhLuanById(Long binhLuanId) {
        return binhLuanRepository.findById(binhLuanId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bình luận"));
    }

    // Cập nhật bình luận
    public void capNhatBinhLuan(BinhLuan binhLuan) {
        binhLuanRepository.save(binhLuan);
    }

    // Lấy số lượng bình luận
    public Long soLuongBinhLuan() {
        return binhLuanRepository.count();
    }
}