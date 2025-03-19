package com.example.xephangnguoidung.presentation.controller.user;

import com.example.xephangnguoidung.application.service.DiemNguoiDungService;
import com.example.xephangnguoidung.application.service.NguoiDungService;
import com.example.xephangnguoidung.data.entity.NguoiDung;
import com.example.xephangnguoidung.data.enums.VaiTro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user/diem")
public class XepHangController {

    private final DiemNguoiDungService diemNguoiDungService;
    private final NguoiDungService nguoiDungService;

    public XepHangController(DiemNguoiDungService diemNguoiDungService, NguoiDungService nguoiDungService) {
        this.diemNguoiDungService = diemNguoiDungService;
        this.nguoiDungService = nguoiDungService;
    }

    @GetMapping("/bangxephang")
    public String bangXepHangNguoiDung(Model model) {
        List<NguoiDung> danhSachNguoiDung = nguoiDungService.layTatCaNguoiDung();
        if (danhSachNguoiDung.isEmpty()) {
            model.addAttribute("danhSachNguoiDung", Collections.emptyList());
        } else {
            // Lọc bỏ người dùng có vai trò là ADMIN
            List<NguoiDung> danhSachNguoiDungUser = danhSachNguoiDung.stream()
                    .filter(nguoiDung -> nguoiDung.getVaiTro() != VaiTro.ADMIN)
                    .collect(Collectors.toList());

            // Sắp xếp danh sách người dùng theo tổng điểm từ cao đến thấp
            Collections.sort(danhSachNguoiDungUser, (nd1, nd2) -> {
                int tongDiem1 = diemNguoiDungService.tinhTongDiemByNguoiDungId(nd1.getId());
                int tongDiem2 = diemNguoiDungService.tinhTongDiemByNguoiDungId(nd2.getId());
                return Integer.compare(tongDiem2, tongDiem1);
            });
            model.addAttribute("danhSachNguoiDung", danhSachNguoiDungUser);
        }
        // Đảm bảo rằng diemNguoiDungService không bị null trong template
        model.addAttribute("diemNguoiDungService", diemNguoiDungService);
        return "user/bang_xep_hang_nguoidung";
    }
}