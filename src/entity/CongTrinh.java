package entity;

import java.time.LocalDate;
import java.util.Objects;

public class CongTrinh {
	private String maCongTrinh;
	private String tenCongTrinh;
	private String diaDiem;
	private LocalDate ngayCapGP;
	private LocalDate ngayKhoiCong;
	private LocalDate ngayHT;

	public CongTrinh(String maCongTrinh, String tenCongTrinh, String diaDiem, LocalDate ngayCapGP,
			LocalDate ngayKhoiCong, LocalDate ngayHT) {
		super();
		this.maCongTrinh = maCongTrinh;
		this.tenCongTrinh = tenCongTrinh;
		this.diaDiem = diaDiem;
		this.ngayCapGP = ngayCapGP;
		this.ngayKhoiCong = ngayKhoiCong;
		this.ngayHT = ngayHT;
	}

	public CongTrinh(String maCongTrinh) {
		super();
		this.maCongTrinh = maCongTrinh;
	}

	public CongTrinh() {
		super();
	}

	public String getMaCongTrinh() {
		return maCongTrinh;
	}

	public void setMaCongTrinh(String maCongTrinh) {
		this.maCongTrinh = maCongTrinh;
	}

	public String getTenCongTrinh() {
		return tenCongTrinh;
	}

	public void setTenCongTrinh(String tenCongTrinh) {
		this.tenCongTrinh = tenCongTrinh;
	}

	public String getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}
	
	

	public LocalDate getNgayCapGP() {
		return ngayCapGP;
	}

	public void setNgayCapGP(LocalDate ngayCapGP) {
		this.ngayCapGP = ngayCapGP;
	}

	public LocalDate getNgayKhoiCong() {
		return ngayKhoiCong;
	}

	public void setNgayKhoiCong(LocalDate ngayKhoiCong) {
		this.ngayKhoiCong = ngayKhoiCong;
	}

	public LocalDate getNgayHT() {
		return ngayHT;
	}

	public void setNgayHT(LocalDate ngayHT) {
		this.ngayHT = ngayHT;
	}



	@Override
	public String toString() {
		return "CongTrinh [maCongTrinh=" + maCongTrinh + ", tenCongTrinh=" + tenCongTrinh + ", diaDiem=" + diaDiem
				+ ", ngayCapGP=" + ngayCapGP + ", ngayKhoiCong="
				+ ngayKhoiCong + ", ngayHT=" + ngayHT + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCongTrinh);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongTrinh other = (CongTrinh) obj;
		return Objects.equals(maCongTrinh, other.maCongTrinh);
	}

}
