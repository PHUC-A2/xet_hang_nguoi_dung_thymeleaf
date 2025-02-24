package com.example.xephangnguoidung.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.repository.NguoiDungRepository;


/*
1️⃣ NguoiDungService (Quản lý người dùng)
List<NguoiDung> layDanhSachNguoiDung() → Lấy danh sách tất cả người dùng
NguoiDung timNguoiDungTheoId(Long id) → Tìm người dùng theo ID
void capNhatThongTinNguoiDung(Long id, NguoiDung nguoiDung) → Cập nhật thông tin người dùng
void xoaNguoiDung(Long id) → Xóa người dùng
List<NguoiDung> layBangXepHang() → Lấy danh sách xếp hạng người dùng*/
@Service
public class NguoiDungService {
    private final NguoiDungRepository nguoiDungRepository;

    public NguoiDungService(NguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    // tạo người dùng
    public NguoiDung luuNguoiDung(NguoiDung nguoiDung) {
        return this.nguoiDungRepository.save(nguoiDung);
    }

    // lấy người dùng dựa vào ID
    public NguoiDung layNguoiDungById(Long id) {
        return this.nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + id));
    }

    // lấy người dùng bằng tên đăng nhập 
    public NguoiDung layNguoiDungBangTenDangNhap(String tenDangNhap) {
        return this.nguoiDungRepository.findByTenDangNhap(tenDangNhap).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng có tên đăng nhập là: " + tenDangNhap));
    }

    // lấy tất cả người dùng
    public List<NguoiDung> layTatCaNguoiDung() {
        return this.nguoiDungRepository.findAll();
    }

    // sửa người dùng dựa vào ID
    public NguoiDung suaNguoiDung(NguoiDung nguoiDung) {
        if (nguoiDung.getId() == null) {
            throw new IllegalArgumentException("ID không được để trống khi cập nhật!");
        }
        if (!this.nguoiDungRepository.existsById(nguoiDung.getId())) {
            throw new RuntimeException("Không tìm thấy người dùng với ID: " + nguoiDung.getId());
        }
        return this.nguoiDungRepository.save(nguoiDung);
    }

    // xóa người dùng
    public void xoaNguoiDungBangId(Long id) {
        this.nguoiDungRepository.deleteById(id);
    }

}