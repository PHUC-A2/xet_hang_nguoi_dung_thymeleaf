package com.example.xephangnguoidung.application.service;

import com.example.xephangnguoidung.data.entity.DiemNguoiDung;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.enums.LoaiHoatDong;
import com.example.xephangnguoidung.data.repository.DiemNguoiDungRepository;
import com.example.xephangnguoidung.data.repository.NguoiDungRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DiemNguoiDungService {

    private final DiemNguoiDungRepository diemNguoiDungRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final NguoiDungService nguoiDungService;

    public DiemNguoiDungService(DiemNguoiDungRepository diemNguoiDungRepository,
            NguoiDungRepository nguoiDungRepository,
            NguoiDungService nguoiDungService) {
        this.diemNguoiDungRepository = diemNguoiDungRepository;
        this.nguoiDungRepository = nguoiDungRepository;
        this.nguoiDungService = nguoiDungService;
    }

    @Transactional
    public void tinhDiem(Long nguoiDungId, LoaiHoatDong loaiHoatDong) {
        System.out.println("✅ Bắt đầu tính điểm cho nguoiDungId=" + nguoiDungId + ", Hoạt động: " + loaiHoatDong);

        NguoiDung nguoiDung = nguoiDungRepository.findById(nguoiDungId)
                .orElseThrow(() -> new RuntimeException("❌ Không tìm thấy người dùng với ID: " + nguoiDungId));
        System.out.println("✅ Tìm thấy người dùng: " + nguoiDung.getTenDangNhap());

        Optional<DiemNguoiDung> diemOptional = diemNguoiDungRepository.findByNguoiDungAndLoaiHoatDong(nguoiDung,
                loaiHoatDong);
        System.out.println("🔍 Tìm thấy điểm trong DB? " + diemOptional.isPresent());

        DiemNguoiDung diemNguoiDung = diemOptional.orElse(new DiemNguoiDung());

        if (diemNguoiDung.getId() == null) {
            System.out.println("🆕 Tạo mới điểm người dùng!");
            diemNguoiDung.setNguoiDung(nguoiDung);
            diemNguoiDung.setLoaiHoatDong(loaiHoatDong);
            diemNguoiDung.setDiem(0);
        }

        System.out.println("🎯 Điểm trước khi cập nhật: " + diemNguoiDung.getDiem());
        diemNguoiDung.setDiem(diemNguoiDung.getDiem() + loaiHoatDong.getDiem());
        System.out.println("📌 Điểm sau khi cập nhật: " + diemNguoiDung.getDiem());

        try {
            diemNguoiDungRepository.save(diemNguoiDung);
            diemNguoiDungRepository.flush(); // Ép lưu ngay
            System.out.println("✅ Điểm đã lưu thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi khi lưu điểm: " + e.getMessage());
        }

        int tongDiem = tinhTongDiemByNguoiDungId(nguoiDungId);
        System.out.println("🔹 Tổng điểm sau cập nhật: " + tongDiem);
    }

    // Tổng số điểm
    public Long tongSoDiem() {
        return diemNguoiDungRepository.count();
    }

    // Thêm phương thức tính tổng điểm với kiểm tra null
    public int tinhTongDiemByNguoiDungId(Long nguoiDungId) {
        return Optional.ofNullable(diemNguoiDungRepository.tinhTongDiemByNguoiDungId(nguoiDungId)).orElse(0);
    }
}