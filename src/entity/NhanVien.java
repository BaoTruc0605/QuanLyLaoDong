package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String ho;
	private String ten;
	private LocalDate ngaySinh;
	private Boolean gioiTinh;
	private String diaChiThuongTru;
	private String diaChiTamTru;
	private String soDienThoai;
	private ChuyenMon chuyenMon;
	private PhongBan phongBan;
	private String chucVu;

	public NhanVien(String maNhanVien, String ho, String ten, LocalDate ngaySinh, Boolean gioiTinh,
			String diaChiThuongTru, String diaChiTamTru, String soDienThoai, ChuyenMon chuyenMon, PhongBan phongBan,
			String chucVu) {
		super();
		this.maNhanVien = maNhanVien;
		this.ho = ho;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChiThuongTru = diaChiThuongTru;
		this.diaChiTamTru = diaChiTamTru;
		this.soDienThoai = soDienThoai;
		this.chuyenMon = chuyenMon;
		this.phongBan = phongBan;
		this.chucVu = chucVu;
	}

	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}

	public NhanVien() {
		super();
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}



	public Boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChiThuongTru() {
		return diaChiThuongTru;
	}

	public void setDiaChiThuongTru(String diaChiThuongTru) {
		this.diaChiThuongTru = diaChiThuongTru;
	}

	public String getDiaChiTamTru() {
		return diaChiTamTru;
	}

	public void setDiaChiTamTru(String diaChiTamTru) {
		this.diaChiTamTru = diaChiTamTru;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public ChuyenMon getChuyenMon() {
		return chuyenMon;
	}

	public void setChuyenMon(ChuyenMon chuyenMon) {
		this.chuyenMon = chuyenMon;
	}

	public PhongBan getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", ho=" + ho + ", ten=" + ten + ", ngaySinh=" + ngaySinh
				+ ", gioiTinh=" + gioiTinh + ", diaChiThuongTru=" + diaChiThuongTru + ", diaChiTamTru=" + diaChiTamTru
				+ ", soDienThoai=" + soDienThoai + ", chuyenMon=" + chuyenMon + ", phongBan=" + phongBan + ", chucVu="
				+ chucVu + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
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
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

}
