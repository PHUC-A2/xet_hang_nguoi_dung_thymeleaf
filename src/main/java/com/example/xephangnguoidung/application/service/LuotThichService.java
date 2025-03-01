package com.example.xephangnguoidung.application.service;

import com.example.xephangnguoidung.data.entity.BaiViet;
import com.example.xephangnguoidung.data.entity.LuotThich;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.repository.BaiVietRepository;
import com.example.xephangnguoidung.data.repository.LuotThichRepository;
import com.example.xephangnguoidung.data.repository.NguoiDungRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LuotThichService {

        private final LuotThichRepository luotThichRepository;
        private final BaiVietRepository baiVietRepository;
        private final NguoiDungRepository nguoiDungRepository;

        public LuotThichService(LuotThichRepository luotThichRepository, BaiVietRepository baiVietRepository,
                        NguoiDungRepository nguoiDungRepository) {
                this.luotThichRepository = luotThichRepository;
                this.baiVietRepository = baiVietRepository;
                this.nguoiDungRepository = nguoiDungRepository;
        }

        // Thêm lượt thích
        public void themLuotThich(Long nguoiDungId, Long baiVietId) {
                NguoiDung nguoiDung = nguoiDungRepository.findById(nguoiDungId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

                BaiViet baiViet = baiVietRepository.findById(baiVietId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));

                // Kiểm tra nếu người dùng đã thích bài viết
                if (luotThichRepository.existsByNguoiDungAndBaiViet(nguoiDung, baiViet)) {
                        throw new RuntimeException("Người dùng đã thích bài viết này");
                }

                LuotThich luotThich = new LuotThich();
                luotThich.setNguoiDung(nguoiDung);
                luotThich.setBaiViet(baiViet);
                luotThichRepository.save(luotThich);

                // Cập nhật số lượt thích trong bảng BaiViet
                baiViet.setSoLuotThich(baiViet.getSoLuotThich() + 1);
                baiVietRepository.save(baiViet);
        }

        // Xóa lượt thích
        public void xoaLuotThich(Long nguoiDungId, Long baiVietId) {
                NguoiDung nguoiDung = nguoiDungRepository.findById(nguoiDungId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

                BaiViet baiViet = baiVietRepository.findById(baiVietId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));

                LuotThich luotThich = luotThichRepository.findByNguoiDungAndBaiViet(nguoiDung, baiViet)
                                .orElseThrow(() -> new RuntimeException("Lượt thích không tồn tại"));

                luotThichRepository.delete(luotThich);

                // Cập nhật số lượt thích trong bảng BaiViet
                baiViet.setSoLuotThich(Math.max(0, baiViet.getSoLuotThich() - 1));
                baiVietRepository.save(baiViet);
        }

        // Lấy danh sách ID người dùng thích bài viết
        public List<Long> layDanhSachNguoiThich(Long baiVietId) {
                BaiViet baiViet = baiVietRepository.findById(baiVietId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));

                return luotThichRepository.findByBaiViet(baiViet)
                                .stream()
                                .map(luotThich -> luotThich.getNguoiDung().getId())
                                .collect(Collectors.toList());
        }

        // Đếm tổng số lượt thích trên hệ thống
        public int demTongLuotThich() {
                return luotThichRepository.countAllBy();
        }

        // Lấy tất cả lượt thích
        public List<LuotThich> layTatCaLuotThich() {
                return luotThichRepository.findAll();
        }

        // Tìm kiếm lượt thích
        public List<LuotThich> timKiemLuotThich(String keyword) {
                return luotThichRepository
                                .findByNguoiDung_TenDangNhapContainingIgnoreCaseOrBaiViet_TieuDeContainingIgnoreCase(
                                                keyword, keyword);
        }
}