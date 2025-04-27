package entity;

import java.util.Objects;

public class MonNuoc {
	private String maMon;
	private String tenMon;
	private String donViTinh;
	private double donGia;
	private String danhMuc;
	public String getMaMon() {
		return maMon;
	}
	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}
	public String getTenMon() {
		return tenMon;
	}
	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public String getDanhMuc() {
		return danhMuc;
	}
	public void setDanhMuc(String danhMuc) {
		this.danhMuc = danhMuc;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maMon);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonNuoc other = (MonNuoc) obj;
		return Objects.equals(maMon, other.maMon);
	}
	public MonNuoc(String maMon, String tenMon, String donViTinh, double donGia, String danhMuc) {
		super();
		this.maMon = maMon;
		this.tenMon = tenMon;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
		this.danhMuc = danhMuc;
	}
	public MonNuoc() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MonNuoc [maMon=" + maMon + ", tenMon=" + tenMon + ", donViTinh=" + donViTinh + ", donGia=" + donGia
				+ ", danhMuc=" + danhMuc + "]\n";
	}
	
	
}
