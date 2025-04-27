package entity;

import java.time.LocalDate;
import java.util.Objects;

public class LichLamViec {
	private int maLLV;
	private NhanVien nv;
	private CaLamViec ca;
	private LocalDate ngayLamViec;
	public int getMaLLV() {
		return maLLV;
	}
	public void setMaLLV(int maLLV) {
		this.maLLV = maLLV;
	}
	public NhanVien getNv() {
		return nv;
	}
	public void setNv(NhanVien nv) {
		this.nv = nv;
	}
	public CaLamViec getCa() {
		return ca;
	}
	public void setCa(CaLamViec ca) {
		this.ca = ca;
	}
	public LocalDate getNgayLamViec() {
		return ngayLamViec;
	}
	public void setNgayLamViec(LocalDate ngayLamViec) {
		this.ngayLamViec = ngayLamViec;
	}
	public LichLamViec(int maLLV, NhanVien nv, CaLamViec ca, LocalDate ngayLamViec) {
		super();
		this.maLLV = maLLV;
		this.nv = nv;
		this.ca = ca;
		this.ngayLamViec = ngayLamViec;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLLV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LichLamViec other = (LichLamViec) obj;
		return maLLV == other.maLLV;
	}
	@Override
	public String toString() {
		return "LichLamViec [maLLV=" + maLLV + ", nv=" + nv + ", ca=" + ca + ", ngayLamViec=" + ngayLamViec + "]";
	}
	
	
	
}
