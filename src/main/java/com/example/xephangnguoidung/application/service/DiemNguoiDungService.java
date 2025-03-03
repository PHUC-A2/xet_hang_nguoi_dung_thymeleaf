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
        NguoiDung nguoiDung = nguoiDungRepository.findById(nguoiDungId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        int diem = loaiHoatDong.getDiem();

        // Thêm điểm vào bảng DiemNguoiDung
        DiemNguoiDung diemNguoiDung = new DiemNguoiDung();
        diemNguoiDung.setNguoiDung(nguoiDung);
        diemNguoiDung.setLoaiHoatDong(loaiHoatDong);
        diemNguoiDung.setDiem(diem);
        diemNguoiDungRepository.save(diemNguoiDung);

        // Tính tổng điểm từ bảng DiemNguoiDung
        int tongDiem = tinhTongDiemByNguoiDungId(nguoiDungId);
        System.out.println("Tổng điểm sau khi cập nhật: " + tongDiem);

        // Cập nhật cấp bậc
        nguoiDungService.capNhatCapBac(nguoiDung);
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