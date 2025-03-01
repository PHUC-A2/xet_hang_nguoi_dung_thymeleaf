package com.example.xephangnguoidung.application.service;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.enums.CapBac;
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
        nguoiDungHienTai.setVaiTro(nguoiDung.getVaiTro());
        nguoiDungHienTai.setSoLanDangNhap(nguoiDung.getSoLanDangNhap());

        // ✅ Cập nhật cấp bậc nếu cần
        capNhatCapBac(nguoiDungHienTai);

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

    // ✅ 8️⃣ Tìm kiếm người dùng
    public List<NguoiDung> timKiemNguoiDung(String keyword) {
        return nguoiDungRepository.findByTenDangNhapContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }

    // ✅ 9️⃣ Cập nhật cấp bậc
    @Transactional
    public void capNhatCapBac(NguoiDung nguoiDung) {
        if (nguoiDung == null) {
            throw new RuntimeException("Người dùng không hợp lệ!");
        }

        int diem = nguoiDung.getDiem();
        CapBac capBacMoi;

        if (diem >= 10000) {
            capBacMoi = CapBac.VIP;
        } else if (diem >= 5000) {
            capBacMoi = CapBac.KIM_CUONG;
        } else if (diem >= 2000) {
            capBacMoi = CapBac.BACH_KIM;
        } else if (diem >= 1000) {
            capBacMoi = CapBac.VANG;
        } else if (diem >= 500) {
            capBacMoi = CapBac.BAC;
        } else {
            capBacMoi = CapBac.DONG;
        }

        // Debug trước khi cập nhật
        System.out.println("🚀 Trước cập nhật: " + nguoiDung.getTenDangNhap() + " - Điểm: " + diem + " - Cấp bậc: "
                + nguoiDung.getCapBac());

        if (!capBacMoi.equals(nguoiDung.getCapBac())) {
            nguoiDung.setCapBac(capBacMoi);
            nguoiDungRepository.save(nguoiDung);
            System.out.println("✅ Đã cập nhật cấp bậc mới: " + capBacMoi);
        } else {
            System.out.println("⚠️ Cấp bậc không thay đổi, không cần cập nhật.");
        }
    }

    // số lượng người dùng
    public long soLuongNguoiDung() {
        return this.nguoiDungRepository.count();
    }
}
