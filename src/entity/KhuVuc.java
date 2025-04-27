package entity;

import java.util.Objects;

public class KhuVuc {
	private int maKV;
	private String tenKV;
	
	@Override
	public int hashCode() {
		return Objects.hash(maKV);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhuVuc other = (KhuVuc) obj;
		return maKV == other.maKV;
	}

	public KhuVuc(int maKV, String tenKV) {
		super();
		this.maKV = maKV;
		this.tenKV = tenKV;
	}

	public int getMaKV() {
		return maKV;
	}

	public void setMaKV(int maKV) {
		this.maKV = maKV;
	}

	public String getTenKV() {
		return tenKV;
	}

	public void setTenKV(String tenKV) {
		this.tenKV = tenKV;
	}

	@Override
	public String toString() {
		return "KhuVuc [maKV=" + maKV + ", tenKV=" + tenKV + "]";
	}
}
