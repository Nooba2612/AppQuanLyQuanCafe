package entity;

import java.util.Objects;

public class ChucVu {
	private int maChucVu;
	private String tenChucVu;
	private double luongTheoGio;
	
	
	
	public ChucVu(int maChucVu, String tenChucVu, double luongTheoGio) {
		super();
		this.maChucVu = maChucVu;
		this.tenChucVu = tenChucVu;
		this.luongTheoGio = luongTheoGio;
	}
	public int getMaChucVu() {
		return maChucVu;
	}
	public void setMaChucVu(int maChucVu) {
		this.maChucVu = maChucVu;
	}
	public String getTenChucVu() {
		return tenChucVu;
	}
	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}
	public double getLuongTheoGio() {
		return luongTheoGio;
	}
	public void setLuongTheoGio(double luongTheoGio) {
		this.luongTheoGio = luongTheoGio;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maChucVu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChucVu other = (ChucVu) obj;
		return maChucVu == other.maChucVu;
	}
	public ChucVu(int maChucVu) {
		super();
		this.maChucVu = maChucVu;
	}

	
	
}
