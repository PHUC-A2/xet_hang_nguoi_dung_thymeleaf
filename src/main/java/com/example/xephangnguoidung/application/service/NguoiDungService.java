package com.example.xephangnguoidung.application.service;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.repository.NguoiDungRepository;

@Service
public class NguoiDungService {
    private final NguoiDungRepository nguoiDungRepository;

    public NguoiDungService(NguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    // ✅ 1️⃣ Tạo người dùng
    public NguoiDung luuNguoiDung(NguoiDung nguoiDung) {
        if (nguoiDungRepository.findByTenDangNhap(nguoiDung.getTenDangNhap()).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại!");
        }
        if (nguoiDungRepository.findByEmail(nguoiDung.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại!");
        }
        return nguoiDungRepository.save(nguoiDung);
    }

    // ✅ 2️⃣ Lấy người dùng theo ID
    public NguoiDung layNguoiDungById(Long id) {
        return nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + id));
    }

    // ✅ 3️⃣ Lấy người dùng theo tên đăng nhập
    public NguoiDung layNguoiDungBangTenDangNhap(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng có tên đăng nhập: " + tenDangNhap));
    }

    // ✅ 4️⃣ Lấy tất cả người dùng
    public List<NguoiDung> layTatCaNguoiDung() {
        return nguoiDungRepository.findAll();
    }

    // ✅ 5️⃣ Sửa thông tin người dùng
    @Transactional
    public NguoiDung suaNguoiDung(Long id, NguoiDung nguoiDung) {
        NguoiDung nguoiDungHienTai = layNguoiDungById(id);

        if (!nguoiDung.getEmail().equals(nguoiDungHienTai.getEmail()) &&
                nguoiDungRepository.findByEmail(nguoiDung.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại!");
        }

        nguoiDungHienTai.setTenDangNhap(nguoiDung.getTenDangNhap());
        nguoiDungHienTai.setMatKhau(nguoiDung.getMatKhau());
        nguoiDungHienTai.setEmail(nguoiDung.getEmail());
        nguoiDungHienTai.setDiem(nguoiDung.getDiem());
        nguoiDungHienTai.setCapBac(nguoiDung.getCapBac());
        nguoiDungHienTai.setVaiTro(nguoiDung.getVaiTro());
        nguoiDungHienTai.setSoLanDangNhap(nguoiDung.getSoLanDangNhap());

        return nguoiDungRepository.save(nguoiDungHienTai);
    }

    // ✅ 6️⃣ Xóa người dùng
    @Transactional
    public void xoaNguoiDungBangId(Long id) {
        if (!nguoiDungRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy người dùng để xóa!");
        }
        nguoiDungRepository.deleteById(id);
    }

    // ✅ 7️⃣ Lấy bảng xếp hạng theo điểm
    public List<NguoiDung> layBangXepHang() {
        return nguoiDungRepository.findAllByOrderByDiemDesc();
    }
}
