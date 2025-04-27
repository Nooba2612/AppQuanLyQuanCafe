package entity;

import java.time.LocalDate;
import java.util.Objects;


public class NhanVien {
	private String maNV;
	private String tenNV;
	private String trangThaiLamViec;
	private LocalDate ngayVaoLam;	
	private String sdt;
	private String email;
	private ChucVu chucVu;
	
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getTrangThaiLamViec() {
		return trangThaiLamViec;
	}
	public void setTrangThaiLamViec(String trangThaiLamViec) {
		this.trangThaiLamViec = trangThaiLamViec;
	}
	public LocalDate getNgayVaoLam() {
		return ngayVaoLam;
	}
	public void setNgayVaoLam(LocalDate ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ChucVu getChucVu() {
		return chucVu;
	}
	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}
	public NhanVien() {
	}
	public NhanVien(String maNV, String tenNV, String trangThaiLamViec, LocalDate ngayVaoLam, String sdt, String email,
			ChucVu chucVu) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.trangThaiLamViec = trangThaiLamViec;
		this.ngayVaoLam = ngayVaoLam;
		this.sdt = sdt;
		this.email = email;
		this.chucVu = chucVu;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return maNV == other.maNV;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", trangThaiLamViec=" + trangThaiLamViec
				+ ", ngayVaoLam=" + ngayVaoLam + ", sdt=" + sdt + ", email=" + email + ", chucVu=" + chucVu + "]";
	}

	
	
	
	
}
