package com.example.xephangnguoidung.application.service;

import com.example.xephangnguoidung.data.entity.DiemNguoiDung;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.enums.LoaiHoatDong;
import com.example.xephangnguoidung.data.repository.DiemNguoiDungRepository;
import com.example.xephangnguoidung.data.repository.NguoiDungRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        NguoiDung nguoiDung = nguoiDungRepository.findById(nguoiDungId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        int diem = loaiHoatDong.getDiem();

        // Thêm điểm vào bảng DiemNguoiDung
        DiemNguoiDung diemNguoiDung = new DiemNguoiDung();
        diemNguoiDung.setNguoiDung(nguoiDung);
        diemNguoiDung.setLoaiHoatDong(loaiHoatDong);
        diemNguoiDung.setDiem(diem);
        diemNguoiDungRepository.save(diemNguoiDung);

        // Cập nhật tổng điểm của người dùng
        nguoiDung.setDiem(nguoiDung.getDiem() + diem);
        nguoiDungRepository.save(nguoiDung);
        nguoiDungRepository.flush(); // Đảm bảo điểm được cập nhật ngay

        // Kiểm tra log
        System.out.println("Điểm sau khi cập nhật: " + nguoiDung.getDiem());

        // Cập nhật cấp bậc
        nguoiDungService.capNhatCapBac(nguoiDung);
    }

}