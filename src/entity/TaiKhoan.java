package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class TaiKhoan {
	private int maTK;
	private CaLamViec ca;
	private String username;
    private String password;
    private String moTa;
	public int getMaTK() {
		return maTK;
	}
	public void setMaTK(int maTK) {
		this.maTK = maTK;
	}
	public CaLamViec getCa() {
		return ca;
	}
	public void setCa(CaLamViec ca) {
		this.ca = ca;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public TaiKhoan(int maTK, CaLamViec ca, String username, String password, String moTa) {
		super();
		this.maTK = maTK;
		this.ca = ca;
		this.username = username;
		this.password = password;
		this.moTa = moTa;
	}
	public TaiKhoan(String tenDN, String mK) {
		this.username = tenDN;
		this.password = mK;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maTK);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return maTK == other.maTK;
	}
	@Override
	public String toString() {
		return "TaiKhoan [maTK=" + maTK + ", ca=" + ca + ", username=" + username + ", password=" + password + ", moTa="
				+ moTa +"]";
	}
    
    
}
