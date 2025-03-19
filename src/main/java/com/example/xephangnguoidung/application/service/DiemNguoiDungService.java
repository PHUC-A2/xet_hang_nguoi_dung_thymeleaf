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
            NguoiDungRepository nguoiDungRepository, NguoiDungService nguoiDungService) {
        this.diemNguoiDungRepository = diemNguoiDungRepository;
        this.nguoiDungRepository = nguoiDungRepository;
        this.nguoiDungService = nguoiDungService;
    }

    @Transactional
    public void tinhDiem(Long nguoiDungId, LoaiHoatDong loaiHoatDong) {
        tinhDiem(nguoiDungId, loaiHoatDong, 1);
    }

    @Transactional
    public void tinhDiem(Long nguoiDungId, LoaiHoatDong loaiHoatDong, int heSo) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(nguoiDungId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + nguoiDungId));

        Optional<DiemNguoiDung> diemOptional = diemNguoiDungRepository.findByNguoiDungAndLoaiHoatDong(nguoiDung,
                loaiHoatDong);
        DiemNguoiDung diemNguoiDung = diemOptional.orElse(new DiemNguoiDung());

        if (diemNguoiDung.getId() == null) {
            diemNguoiDung.setNguoiDung(nguoiDung);
            diemNguoiDung.setLoaiHoatDong(loaiHoatDong);
            diemNguoiDung.setDiem(0);
        }

        diemNguoiDung.setDiem(diemNguoiDung.getDiem() + loaiHoatDong.getDiem() * heSo);
        diemNguoiDungRepository.save(diemNguoiDung);

        // Cập nhật tổng điểm và cấp bậc sau khi thay đổi điểm
        nguoiDungService.capNhatCapBac(nguoiDungId);
    }

    // Tổng số điểm của một người dùng
    public Integer tongSoDiem(Long nguoiDungId) {
        return this.diemNguoiDungRepository.tinhTongDiemByNguoiDungId(nguoiDungId);
    }

    // Tổng số điểm của tất cả người dùng
    public Integer tongSoDiemTatCaNguoiDung() {
        return this.diemNguoiDungRepository.tinhTongDiemTatCaNguoiDung();
    }

    // Thêm phương thức tính tổng điểm với kiểm tra null
    public int tinhTongDiemByNguoiDungId(Long nguoiDungId) {
        return Optional.ofNullable(diemNguoiDungRepository.tinhTongDiemByNguoiDungId(nguoiDungId)).orElse(0);
    }
}