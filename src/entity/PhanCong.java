package entity;

import java.time.LocalDate;
import java.util.Objects;

public class PhanCong {
	private String maPhanCong;
	private NhanVien nhanVien;
	private CongViec congViec;
	private CongTrinh congTrinh;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private String ghiChu;
	private int TongNgayCong;

	public PhanCong(String maPhanCong, NhanVien nhanVien, CongViec congViec, CongTrinh congTrinh, LocalDate ngayBatDau,
			LocalDate ngayKetThuc, String ghiChu, int tongNgayCong) {
		super();
		this.maPhanCong = maPhanCong;
		this.nhanVien = nhanVien;
		this.congViec = congViec;
		this.congTrinh = congTrinh;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.ghiChu = ghiChu;
		TongNgayCong = tongNgayCong;
	}

	public PhanCong(String maPhanCong) {
		super();
		this.maPhanCong = maPhanCong;
	}

	public PhanCong() {
		super();
	}

	public String getMaPhanCong() {
		return maPhanCong;
	}

	public void setMaPhanCong(String maPhanCong) {
		this.maPhanCong = maPhanCong;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setnhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public CongViec getCongViec() {
		return congViec;
	}

	public void setCongViec(CongViec congViec) {
		this.congViec = congViec;
	}

	public CongTrinh getCongTrinh() {
		return congTrinh;
	}

	public void setCongTrinh(CongTrinh congTrinh) {
		this.congTrinh = congTrinh;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public int getTongNgayCong() {
		return TongNgayCong;
	}

	public void setTongNgayCong(int tongNgayCong) {
		TongNgayCong = tongNgayCong;
	}

	@Override
	public String toString() {
		return "PhanCong [maPhanCong=" + maPhanCong + ", nhanVien=" + nhanVien + ", congViec=" + congViec
				+ ", congTrinh=" + congTrinh + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc
				+ ", ghiChu=" + ghiChu + ", TongNgayCong=" + TongNgayCong + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhanCong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhanCong other = (PhanCong) obj;
		return Objects.equals(maPhanCong, other.maPhanCong);
	}

}
