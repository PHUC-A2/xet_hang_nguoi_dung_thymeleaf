package com.example.xephangnguoidung.data.entity.dto;

import com.example.xephangnguoidung.data.enums.VaiTro;

public class RegisterDTO {
    private String tenDangNhap;
    private String email;
    private String matKhau;
    private String xacNhanMatKhau;
    private VaiTro vaiTro;

    public RegisterDTO() {
    }

    public RegisterDTO(String tenDangNhap, String email, String matKhau, String xacNhanMatKhau, VaiTro vaiTro) {
        this.tenDangNhap = tenDangNhap;
        this.email = email;
        this.matKhau = matKhau;
        this.xacNhanMatKhau = xacNhanMatKhau;
        this.vaiTro = vaiTro;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getXacNhanMatKhau() {
        return xacNhanMatKhau;
    }

    public void setXacNhanMatKhau(String xacNhanMatKhau) {
        this.xacNhanMatKhau = xacNhanMatKhau;
    }

    public VaiTro getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = vaiTro;
    }

    @Override
    public String toString() {
        return "RegisterDTO [tenDangNhap=" + tenDangNhap + ", email=" + email + ", matKhau=" + matKhau
                + ", xacNhanMatKhau=" + xacNhanMatKhau + ", vaiTro=" + vaiTro + "]";
    }

}
