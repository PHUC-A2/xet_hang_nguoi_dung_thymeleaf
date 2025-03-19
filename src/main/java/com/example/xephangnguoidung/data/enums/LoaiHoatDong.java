package com.example.xephangnguoidung.data.enums;

public enum LoaiHoatDong {
    DANG_NHAP(2000), // Đăng nhập
    VIET_BAI(3000), // Viết bài
    BINH_LUAN(1000), // Bình luận
    DUOC_THICH(400); // Được thích

    private final int diem;

    private LoaiHoatDong(int diem) {
        this.diem = diem;
    }

    public int getDiem() {
        return diem;
    }

}
