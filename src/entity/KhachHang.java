package entity;

import java.util.Objects;

public class KhachHang {
	private int maKH;
	private String tenKH;
	private String sdt;
	private int diemTichLuy;//diemTichLuy= diemTichLuy + tổng tiền hóa đơn chia 10.000 lấy phần nguyên
	
	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return maKH == other.maKH;
	}
//tạo kh
	public KhachHang(String tenKH, String sdt) {
		super();
		this.tenKH = tenKH;
		this.sdt = sdt;
	}

	public KhachHang(int maKH, String tenKH, String sdt, int diemTichLuy) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.diemTichLuy = diemTichLuy;
	}
//cập nhật kh theo sdt
	public KhachHang(String tenKH, String sdt, int diemTichLuy) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.diemTichLuy = diemTichLuy;
	}

	public KhachHang() {
		// TODO Auto-generated constructor stub
	}

	public int getMaKH() {
		return maKH;
	}

	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}


	public int getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", sdt=" + sdt + ", diemTichLuy="
				+ diemTichLuy + "]";
	}
	
}
