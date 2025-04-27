package entity;

import java.util.Objects;

public class ChiTietHoaDon {
	private int maCTHD;
	private HoaDon hd;
	private MonNuoc mon;
	private int soLuong;
	private double donGia;
	private double thanhTien;
	
	
	
	public ChiTietHoaDon(int maCTHD, HoaDon hd, MonNuoc mon, int soLuong, double donGia, double thanhTien) {
		super();
		this.maCTHD = maCTHD;
		this.hd = hd;
		this.mon = mon;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
	}
	public ChiTietHoaDon() {
		// TODO Auto-generated constructor stub
	}
	//tạo chi tiết hóa đơn ban đầu
	public ChiTietHoaDon(HoaDon hd, MonNuoc mon, int soLuong) {
		this.hd = hd;
		this.mon = mon;
		this.soLuong = soLuong;
	}
	public int getMaCTHD() {
		return maCTHD;
	}
	public void setMaCTHD(int maCTHD) {
		this.maCTHD = maCTHD;
	}
	public HoaDon getHd() {
		return hd;
	}
	public void setHd(HoaDon hd) {
		this.hd = hd;
	}
	public MonNuoc getMon() {
		return mon;
	}
	public void setMon(MonNuoc mon) {
		this.mon = mon;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maCTHD);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		return Objects.equals(maCTHD, other.maCTHD);
	}
	@Override
	public String toString() {
		return "ChiTienHoaDon [maCTHD=" + maCTHD + ", hd=" + hd + ", mon=" + mon + ", soLuong=" + soLuong + ", donGia="
				+ donGia + ", thanhTien=" + thanhTien + "]";
	}
	

	
}
