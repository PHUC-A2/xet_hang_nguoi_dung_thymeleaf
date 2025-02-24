package com.example.xephangnguoidung.presentation.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;

@RestController
@RequestMapping("/admin/nguoidung")
public class QuanLyNguoiDungController {
    private final NguoiDungService nguoiDungService;

    public QuanLyNguoiDungController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    // tạo người dùng
    @PostMapping("/tao")
    public NguoiDung taoNguoiDung(@RequestBody NguoiDung nguoiDung) {
        return this.nguoiDungService.luuNguoiDung(nguoiDung);
    }

    // lấy tất cả người dùng
    @GetMapping("/laytatcanguoidung")
    public List<NguoiDung> layTatCaNguoiDung() {
        List<NguoiDung> danhSach = this.nguoiDungService.layTatCaNguoiDung();
        return danhSach;
    }

   

    // lấy người dùng dựa vào ID
    @GetMapping("/laynguoidung/id/{id}")
    public NguoiDung layNguoiDungById(@PathVariable Long id) {
        return this.nguoiDungService.layNguoiDungById(id);
    }

    // lấy người dùng dựa vào tên đăng nhập
    @GetMapping("/laynguoidung/tendangnhap/{tendangnhap}")
    public NguoiDung layNguoiDungByEmail(@PathVariable String tenDangNhap) {
        return this.nguoiDungService.layNguoiDungBangTenDangNhap(tenDangNhap);
    }

    // Sửa người dùng dựa vào id
    @PutMapping("/suanguoidung/id/{id}")
    public ResponseEntity<NguoiDung> suaNguoiDungById(@PathVariable Long id, @RequestBody NguoiDung request) {

        /*
         * id
         * ten_dang_nhap
         * mat_khau
         * email
         * diem
         * cap_bac
         * vai_tro
         * so_lan_dang_nhap
         * ngay_tao
         */
        NguoiDung nguoiDung = this.nguoiDungService.layNguoiDungById(id);

        if (nguoiDung == null) {
            return ResponseEntity.notFound().build(); // trả về 404 nếu không tìm thấy người dùng
        }
        // giữ nguyên ID và Email và ngày tạo
        nguoiDung.setTenDangNhap(request.getTenDangNhap());
        nguoiDung.setMatKhau(request.getMatKhau());
        nguoiDung.setDiem(request.getDiem());
        nguoiDung.setCapBac(request.getCapBac());
        nguoiDung.setVaiTro(request.getVaiTro());
        nguoiDung.setSoLanDangNhap(request.getSoLanDangNhap());

        // Giữ nguyên Email nếu request không có email mới

        if (request.getEmail() != null) {
            nguoiDung.setEmail(request.getEmail());
        }

        NguoiDung nguoiDungMoi = this.nguoiDungService.suaNguoiDung(nguoiDung);
        return ResponseEntity.ok(nguoiDungMoi);
    }

    // xóa người dùng theo ID
    @DeleteMapping("/xoanguoidung/id/{id}")
    public void xoaNguoiDungById(@PathVariable Long id){
        this.nguoiDungService.xoaNguoiDungBangId(id);
    }
}
