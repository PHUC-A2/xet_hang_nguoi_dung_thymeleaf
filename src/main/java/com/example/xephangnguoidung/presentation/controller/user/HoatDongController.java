// package com.example.xephangnguoidung.presentation.controller.user;

// import com.example.xephangnguoidung.application.service.BaiVietService;
// import com.example.xephangnguoidung.application.service.BinhLuanService;
// import com.example.xephangnguoidung.application.service.LuotThichService;
// import com.example.xephangnguoidung.data.entity.BaiViet;
// import com.example.xephangnguoidung.data.entity.BinhLuan;
// import com.example.xephangnguoidung.data.entity.NguoiDung;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;

// import java.security.Principal;
// import java.util.List;

// @Controller
// @RequestMapping("/user")
// public class HoatDongController {

//     private final BaiVietService baiVietService;
//     private final BinhLuanService binhLuanService;
//     private final LuotThichService luotThichService;

//     public HoatDongController(BaiVietService baiVietService, BinhLuanService binhLuanService,
//             LuotThichService luotThichService) {
//         this.baiVietService = baiVietService;
//         this.binhLuanService = binhLuanService;
//         this.luotThichService = luotThichService;
//     }

//     @GetMapping("/hoatdong")
//     public String hienThiHoatDong(Model model) {
//         List<BaiViet> danhSachBaiViet = baiVietService.layTatCaBaiViet();
//         model.addAttribute("danhSachBaiViet", danhSachBaiViet);
//         return "view/user/hoatdong_nguoidung";
//     }

//     @PostMapping("/baiviet/tao")
//     public String taoBaiViet(@ModelAttribute BaiViet baiViet, Principal principal) {
//         NguoiDung nguoiDung = (NguoiDung) ((Authentication) principal).getPrincipal();
//         baiViet.setNguoiDung(nguoiDung);
//         baiVietService.luuBaiViet(baiViet);
//         return "redirect:/user/hoatdong";
//     }

//     @PostMapping("/binhluan/tao")
//     public String taoBinhLuan(@RequestParam Long baiVietId, @RequestParam String noiDung, Principal principal) {
//         NguoiDung nguoiDung = (NguoiDung) ((Authentication) principal).getPrincipal();
//         BinhLuan binhLuan = new BinhLuan();
//         binhLuan.setNoiDung(noiDung);
//         binhLuan.setBaiViet(baiVietService.layBaiVietById(baiVietId));
//         binhLuan.setNguoiDung(nguoiDung);
//         binhLuanService.luuBinhLuan(binhLuan);
//         return "redirect:/user/hoatdong";
//     }

//     @PostMapping("/baiviet/xoa")
//     public String xoaBaiViet(@RequestParam Long baiVietId) {
//         baiVietService.xoaBaiVietById(baiVietId);
//         return "redirect:/user/hoatdong";
//     }

//     @PostMapping("/baiviet/sua")
//     public String suaBaiViet(@ModelAttribute BaiViet baiViet) {
//         baiVietService.luuBaiViet(baiViet);
//         return "redirect:/user/hoatdong";
//     }
// }