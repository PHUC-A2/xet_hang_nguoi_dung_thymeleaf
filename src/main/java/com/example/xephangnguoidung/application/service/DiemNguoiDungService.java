package com.example.xephangnguoidung.application.service;

// import org.springframework.stereotype.Service;

// import com.example.xephangnguoidung.data.entity.DiemNguoiDung;
// import com.example.xephangnguoidung.data.entity.NguoiDung;
// import com.example.xephangnguoidung.data.enums.CapBac;
// import com.example.xephangnguoidung.data.enums.LoaiHoatDong;
// import com.example.xephangnguoidung.data.repository.DiemNguoiDungRepository;
// import com.example.xephangnguoidung.data.repository.NguoiDungRepository;

// import jakarta.transaction.Transactional;

/*
5️⃣ DiemNguoiDungService (Tính điểm & Cập nhật cấp bậc)
void congDiemDangNhap(Long nguoiDungId) → Cộng +10 điểm khi đăng nhập
void congDiemVietBai(Long nguoiDungId) → Cộng +50 điểm khi viết bài
void congDiemDuocThich(Long nguoiDungId) → Cộng +20 điểm khi bài viết được thích
void congDiemBinhLuan(Long nguoiDungId) → Cộng +30 điểm khi bình luận
void capNhatCapBac(Long nguoiDungId) → Cập nhật cấp bậc dựa trên tổng điểm
*/

// @Service
// @Transactional
public class DiemNguoiDungService {
    // private final NguoiDungRepository nguoiDungRepository;
    // private final DiemNguoiDungRepository diemNguoiDungRepository;
   
    // // constructor
    // public DiemNguoiDungService(NguoiDungRepository nguoiDungRepository,
    //         DiemNguoiDungRepository diemNguoiDungRepository) {
    //     this.nguoiDungRepository = nguoiDungRepository;
    //     this.diemNguoiDungRepository = diemNguoiDungRepository;
    // }

    // // phương thức cổng điểm chung 
    // private void congDiem(Long nguoiDungId, LoaiHoatDong loaiHoatDong) {
    //     NguoiDung nguoiDung = nguoiDungRepository.findById(nguoiDungId)
    //             .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng!"));

    //     DiemNguoiDung diemNguoiDung = new DiemNguoiDung(nguoiDung, loaiHoatDong.getDiem());
    //     diemNguoiDungRepository.save(diemNguoiDung);

    //     capNhatCapBac(nguoiDungId);
    // }


    // // Cộng điểm khi đăng nhập
    // public void congDiemDangNhap(Long nguoiDungId) {
    //     congDiem(nguoiDungId, LoaiHoatDong.DANG_NHAP);
    // }

    // // Cộng điểm khi viết bài
    // public void congDiemVietBai(Long nguoiDungId) {
    //     congDiem(nguoiDungId, LoaiHoatDong.VIET_BAI);
    // }

    // // Cộng điểm khi bài viết được thích
    // public void congDiemDuocThich(Long nguoiDungId) {
    //     congDiem(nguoiDungId, LoaiHoatDong.DUOC_THICH);
    // }

    // // Cộng điểm khi bình luận
    // public void congDiemBinhLuan(Long nguoiDungId) {
    //     congDiem(nguoiDungId, LoaiHoatDong.BINH_LUAN);
    // }
    // private void capNhatCapBac(Long nguoiDungId) {
    //     int tongDiem = diemNguoiDungRepository.tinhTongDiem(nguoiDungId);

    //     CapBac capBac;
    //     if (tongDiem >= 5000) {
    //         capBac = CapBac.VIP;
    //     } else if (tongDiem >= 2000) {
    //         capBac = CapBac.KIM_CUONG;
    //     } else if (tongDiem >= 1000) {
    //         capBac = CapBac.VANG;
    //     } else if (tongDiem >= 500) {
    //         capBac = CapBac.BAC;
    //     } else {
    //         capBac = CapBac.DONG;
    //     }

    //     nguoiDungRepository.updateCapBac(nguoiDungId, capBac);
    // }

}
