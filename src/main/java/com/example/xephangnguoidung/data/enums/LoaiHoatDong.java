package com.example.xephangnguoidung.data.enums;

public enum LoaiHoatDong {
    DANG_NHAP(10), // Đăng nhập
    VIET_BAI(20), // Viết bài
    BINH_LUAN(30), // Bình luận
    DUOC_THICH(50); // Được thích

    private final int diem;

    private LoaiHoatDong(int diem) {
        this.diem = diem;
    }

    public int getDiem() {
        return diem;
    }

}
