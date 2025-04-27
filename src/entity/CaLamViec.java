package entity;

import java.sql.Time;
import java.util.Objects;

public class CaLamViec {
	private int maCa;
	private String tenCa;
	private Time gioVaoLam;
	private Time gioKetThuc;
	
	@Override
	public int hashCode() {
		return Objects.hash(maCa);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaLamViec other = (CaLamViec) obj;
		return maCa == other.maCa;
	}

	public CaLamViec(int maCa, String tenCa, Time gioVaoLam, Time gioKetThuc) {
		super();
		this.maCa = maCa;
		this.tenCa = tenCa;
		this.gioVaoLam = gioVaoLam;
		this.gioKetThuc = gioKetThuc;
	}

	public int getMaCa() {
		return maCa;
	}

	public void setMaCa(int maCa) {
		this.maCa = maCa;
	}

	public String getTenCa() {
		return tenCa;
	}

	public void setTenCa(String tenCa) {
		this.tenCa = tenCa;
	}

	public Time getgioVaoLam() {
		return gioVaoLam;
	}

	public void setgioVaoLam(Time gioVaoLam) {
		this.gioVaoLam = gioVaoLam;
	}

	public Time getGioKetThuc() {
		return gioKetThuc;
	}

	public void setGioKetThuc(Time gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}

	@Override
	public String toString() {
		return "CaLamViec [maCa=" + maCa + ", tenCa=" + tenCa + ", gioVaoLam=" + gioVaoLam + ", gioKetThuc="
				+ gioKetThuc + "]";
	}
}
