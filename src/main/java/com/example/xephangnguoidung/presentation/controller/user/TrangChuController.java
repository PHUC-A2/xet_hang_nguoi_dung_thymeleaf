// package com.example.xephangnguoidung.presentation.controller.user;

// import com.example.xephangnguoidung.application.service.NguoiDungService;
// import com.example.xephangnguoidung.data.entity.NguoiDung;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/nguoidung")
// public class TrangChuController {
//     private final NguoiDungService nguoiDungService;

//     public TrangChuController(NguoiDungService nguoiDungService) {
//         this.nguoiDungService = nguoiDungService;
//     }

//     @GetMapping("/gioithieu")
//     public String gioiThieu() {
//         return this.nguoiDungService.hello();
//     }

//     @PostMapping("/tao")
//     public NguoiDung taoNguoiDung(@RequestBody NguoiDung nguoiDung) {
//         return this.nguoiDungService.taoNguoiDung(nguoiDung);
//     }

//     @GetMapping("/dsnguoidung")
//     public List<NguoiDung> danhSachNguoiDung() {
//         return this.nguoiDungService.layDanhSachNguoiDung();
//     }
// }
