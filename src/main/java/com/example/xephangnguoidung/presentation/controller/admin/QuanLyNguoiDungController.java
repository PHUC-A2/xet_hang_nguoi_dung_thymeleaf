package com.example.xephangnguoidung.presentation.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;

@RestController
@RequestMapping("/admin/nguoidung")
public class QuanLyNguoiDungController {
    private final NguoiDungService nguoiDungService;

    public QuanLyNguoiDungController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    // ✅ 1️⃣ Tạo người dùng
    @PostMapping("/tao")
    public ResponseEntity<NguoiDung> taoNguoiDung(@RequestBody NguoiDung nguoiDung) {
        return ResponseEntity.ok(nguoiDungService.luuNguoiDung(nguoiDung));
    }

    // ✅ 2️⃣ Lấy tất cả người dùng
    @GetMapping("/laytatcanguoidung")
    public ResponseEntity<List<NguoiDung>> layTatCaNguoiDung() {
        return ResponseEntity.ok(nguoiDungService.layTatCaNguoiDung());
    }

    // ✅ 3️⃣ Lấy người dùng theo ID
    @GetMapping("/laynguoidung/id/{id}")
    public ResponseEntity<NguoiDung> layNguoiDungById(@PathVariable Long id) {
        return ResponseEntity.ok(nguoiDungService.layNguoiDungById(id));
    }

    // ✅ 4️⃣ Lấy người dùng theo tên đăng nhập
    @GetMapping("/laynguoidung/tendangnhap/{tendangnhap}")
    public ResponseEntity<NguoiDung> layNguoiDungByTenDangNhap(@PathVariable String tenDangNhap) {
        return ResponseEntity.ok(nguoiDungService.layNguoiDungBangTenDangNhap(tenDangNhap));
    }

    // ✅ 5️⃣ Sửa thông tin người dùng
    @PutMapping("/suanguoidung/id/{id}")
    public ResponseEntity<NguoiDung> suaNguoiDungById(@PathVariable Long id, @RequestBody NguoiDung request) {
        NguoiDung nguoiDung = nguoiDungService.layNguoiDungById(id);

        // Giữ nguyên ID, Email và ngày tạo
        nguoiDung.setTenDangNhap(request.getTenDangNhap());
        nguoiDung.setMatKhau(request.getMatKhau());
        nguoiDung.setDiem(request.getDiem());
        nguoiDung.setCapBac(request.getCapBac());
        nguoiDung.setVaiTro(request.getVaiTro());
        nguoiDung.setSoLanDangNhap(request.getSoLanDangNhap());

        // Cập nhật Email nếu có
        if (request.getEmail() != null) {
            nguoiDung.setEmail(request.getEmail());
        }

        NguoiDung nguoiDungMoi = nguoiDungService.suaNguoiDung(id, nguoiDung);
        return ResponseEntity.ok(nguoiDungMoi);
    }

    // ✅ 6️⃣ Xóa người dùng theo ID
    @DeleteMapping("/xoanguoidung/id/{id}")
    public ResponseEntity<String> xoaNguoiDungById(@PathVariable Long id) {
        nguoiDungService.xoaNguoiDungBangId(id);
        return ResponseEntity.ok("Người dùng đã bị xóa.");
    }
}
