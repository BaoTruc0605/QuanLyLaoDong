-- Xóa database
USE master
DROP DATABASE QuanLyLaoDong
--Tạo database
USE master

CREATE DATABASE QuanLyLaoDong
ON PRIMARY
(
NAME = 'QLLD_Data',
FILENAME = 'D:\IUH\DB\QLLD\QLLD_Data.mdf',
SIZE = 5MB, MAXSIZE = 15MB, FILEGROWTH=2MB
)
LOG ON
(
NAME = 'QLLD_Log',
FILENAME = 'D:\IUH\DB\QLLD\QLLD_Log.ldf',
SIZE = 5MB, MAXSIZE = 15MB, FILEGROWTH=2MB
)
go
use QuanLyLaoDong
--Tạo table
CREATE TABLE ChuyenMon (
	MaChuyenMon varchar(10) not null constraint PK_ChuyenMon primary key,
	TenChuyenMon nvarchar(50) not null
)
go
CREATE TABLE PhongBan (
	MaPhongBan varchar(10) not null constraint PK_PhongBan primary key,
	TenPhongBan nvarchar(50) not null,
)
go
CREATE TABLE NhanVien(
	MaNV Varchar(20) not null constraint PK_NhanVien primary key,
	HoNV Nvarchar(30) not null,
	TenNV Nvarchar(30) not null,
	NgaySinh date not null constraint CK_NV_NgaySinh  check (YEAR(GETDATE()) - YEAR(ngaySinh) >= 18), -- phải đủ 18 tuổi
	GioiTinh bit not null,
	DiaChiThuongTru nvarchar(100) not null,
	DiaChiTamTru nvarchar(100) not null,
	SoDienThoai varchar(15) NOT NULL constraint CK_NV_SDT check (SoDienThoai LIKE '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	MaChuyenMon varchar(10) not null constraint FK_NhanVien_ChuyenMon foreign key references ChuyenMon(MaChuyenMon) on delete cascade,
	MaPhongBan varchar(10) not null constraint FK_NhanVien_PhongBan foreign key references PhongBan(MaPhongBan) on delete cascade,
	ChucVu nvarchar(40) not null
)

go
CREATE TABLE CongTrinh(
	MaCT Varchar(20) not null constraint PK_CongTrinh primary key,
	TenCT Nvarchar(40) not null,
	DiaDiem Nvarchar(40) not null,
	NgayCapGP date not null,
	NgayKhoiCong date not null,
	NgayHT date not null-- check (NgayHT > NgayKhoiCong)
)

ALTER TABLE CongTrinh
ADD CONSTRAINT CK_CongTrinh_NgayHT CHECK (NgayHT > NgayKhoiCong)
go
CREATE TABLE CongViec(
	MaCongViec varchar(10) not null constraint PK_CongViec primary key,
	TenCongViec nvarchar(50) not null
)
go
CREATE TABLE PhanCong(
	MaPC Varchar(20) not null constraint PK_PhanCong primary key,
	MaNV Varchar(20) not null constraint FK_PhanCong_NhanVien foreign key references NhanVien(MaNV) on delete cascade,
	MaCongViec varchar(10) not null constraint FK_PhanCong_CongViec foreign key references CongViec(MaCongViec) on delete cascade,
	MaCT varchar(20) not null constraint FK_PhanCong_CongTrinh foreign key references CongTrinh(MaCT) on delete cascade,
	NgayBatDau date not null,
	NgayKetThuc date not null, --check (NgayKetThuc > NgayBatDau),
	GhiChu Nvarchar(50),
	TongNgayCong int --= DATEDIFF(day, NgayBatDau, NgayKetThuc)
)
go
create trigger trg_TongNgayCong
on dbo.PhanCong
for insert,update
as 
begin
	declare @mapc Varchar(20), @tnc int
	if exists (select * from inserted)
		 begin
			select @mapc = MaPC from inserted
			set @tnc = ( DATEDIFF(day, (select  NgayBatDau from PhanCong where MaPC = @mapc),  (select  NgayKetThuc from PhanCong where MaPC = @mapc)))
			update PhanCong
			set TongNgayCong = @tnc
			where MaPC = @mapc
		 end
	if exists (select * from deleted)
		 begin
			select @mapc = MaPC from deleted
			set @tnc = ( DATEDIFF(day, (select  NgayBatDau from PhanCong where MaPC = @mapc),  (select  NgayKetThuc from PhanCong where MaPC = @mapc)))
			update PhanCong
			set TongNgayCong = @tnc
			where MaPC = @mapc
		 end
end
go


GO

CREATE FUNCTION getNgayKhoiCong(@maCongTrinh varchar(10))
RETURNS varchar(10)
AS
BEGIN 
	DECLARE @ngayKhoiCong DATE

	SELECT @ngayKhoiCong = ngayKhoiCong FROM CongTrinh
	WHERE MaCT = @maCongTrinh

	RETURN @ngayKhoiCong
END
GO

ALTER TABLE PhanCong
ADD CONSTRAINT CK_PhanCong_NgayKetThuc CHECK (NgayKetThuc > NgayBatDau)

ALTER TABLE PhanCong
ADD CONSTRAINT CK_PhanCong_NgayBatDau CHECK (NgayBatDau >= [dbo].[getNgayKhoiCong](MaCT))

GO

CREATE TABLE NguoiDung(
	TenDangNhap Varchar(20) not null constraint PK_NguoiDung primary key,
	MatKhau varchar(30) not null
)
go
ALTER TABLE dbo.NguoiDung ADD CONSTRAINT FK_NguoiDung  FOREIGN KEY (TenDangNhap) REFERENCES dbo.NhanVien(MaNV) on delete cascade

go
	create proc login_app @username nchar(10), @password nchar(20), @result int output
	as
	begin 
		if exists (select * from NguoiDung 
				where TenDangNhap = @username and MatKhau = @password)
				set @result = 1
		else 
				set @result = 0
	end

go

create trigger trg_NhanVien_NguoiDung
on dbo.NhanVien
for insert,delete
as 
begin
	declare @manv Varchar(20)
	if exists (select * from inserted)
		 begin
			select @manv = MaNV from inserted
			--SELECT * FROM dbo.PhanCong
			INSERT dbo.NguoiDung (TenDangNhap, MatKhau)
			 values (@manv, '12345')
		 end
	if exists (select * from deleted)
		 begin
			select @manv = MaNV from deleted

			delete from NguoiDung where TenDangNhap = @manv
		 end
end

go
use QuanLyLaoDong

INSERT ChuyenMon
VALUES 
	('CMDM', N'Đào móng'),
	('CMLN', N'Lót nền'),
	('CMST', N'Sơn tường'),
	('CMLG', N'Lát gạch'),
	('CMLK', N'Lắp kính'),
	('CMTK', N'Thiết kế'),
	('CMLD', N'Lắp điện'),
	('CMDV', N'Đi vôi')

select * from ChuyenMon


go
INSERT PhongBan
VALUES 
	('PBKT', N'Phòng kế toán'),
	('PBKD', N'Phòng kinh doanh'),
	('PBKTh', N'Phòng kĩ thuật'),
	('PBTC', N'Phòng tổ chức'),
	('PBDA', N'Phòng dự án'),
	('PBCM', N'Phòng chuyên môn'),
	('PBPV', N'Phòng phục vụ')

SELECT * FROM PhongBan
go

INSERT NhanVien VALUES
('NV001',N'Trần', N'Thị A', '1989-05-27', '1', N'Tây Ninh', N'Tây Ninh', '0338030540', 'CMLK', 'PBKTh', N'Nhân Viên')
INSERT NhanVien VALUES
('NV002',N'Nguyễn', N'Cẩm Tuyên', '1979-07-25', '1', N'Đà Nẵng', N'Đà Nẵng', '0123456789', 'CMLN', 'PBKTh', N'Nhân Viên')
INSERT NhanVien VALUES
('NV003',N'Phạm', N'Tố Như', '1984-05-23', '1', N'Hà Nội', N'Hà Nội', '0338030546', 'CMDM', 'PBKTh', N'Nhân viên phòng dự án')
INSERT NhanVien VALUES
('NV004',N'Trần', N'Thanh Tú', '1999-03-10', '1', N'Vũng Tàu', N'Vũng Tàu', '0338480540', 'CMST', 'PBPV', N'Nhân Viên')
INSERT NhanVien VALUES
('NV005',N'Võ', N'Minh Trí', '1981-12-07', '0', N'Phú Yên', N'Phú Yên', '0368038541', 'CMLD', 'PBCM', N'Nhân Viên')
INSERT NhanVien VALUES
('NV006',N'Dương', N'Công Minh', '1992-06-17', '0', N'TP.HCM', N'Tp.HCM', '0337230548', 'CMDV', 'PBKTh', N'Nhân Viên')
INSERT NhanVien VALUES
('NV007',N'Hồ', N'Đăng Khoa', '1993-06-17', '0', N'TP.HCM', N'Tp.HCM', '0337230548', 'CMDV', 'PBKTh', N'Trưởng phòng ban')
INSERT NhanVien VALUES
('NV008',N'Võ', N'Quang Linh', '1992-04-17', '0', N'TP.HCM', N'Tp.HCM', '0337230548', 'CMDV', 'PBCM', N'Trưởng phòng ban')
INSERT NhanVien VALUES
('NV009',N'Dương', N'Minh Trường', '1992-06-17', '0', N'TP.HCM', N'Tp.HCM', '0337230548', 'CMDV', 'PBKTh', N'Nhân viên phòng dự án')
go
SELECT * FROM dbo.NhanVien	

go
INSERT dbo.CongTrinh VALUES
('CT001', N'Tòa Nhà Nhân Ái', N'Long An', '2020-12-07','2021-12-07', '2023-06-07' ), 
('CT002', N'Tòa Nhà Số 8 Quận 12', N'Quận 12', '2019-06-06','2020-11-11', '2024-11-11' ), 
('CT003', N'Khách sạn Lâm Đồng', N'Lâm Đồng', '2021-07-07','2021-12-18', '2023-12-18' ) 

SELECT * FROM dbo.CongTrinh

GO
INSERT dbo.CongViec VALUES
	('CV001', N'Đào móng'),
	('CV002', N'Lát gạch'),
	('CV003', N'Sơn tường'),
	('CV004', N'Lắp kính'),
	('CV005', N'Lắp điện'),
	('CV006', N'Xây tường')

GO
SELECT * FROM dbo.CongViec
go
INSERT dbo.PhanCong VALUES
	('PC001','NV001','CV002', 'CT001','2022-12-12','2023-05-07', N'',0)
INSERT dbo.PhanCong VALUES
	('PC002','NV003','CV001', 'CT002','2022-11-11','2023-05-07', N'',0)
INSERT dbo.PhanCong VALUES
	('PC003','NV005','CV006', 'CT002','2022-11-18','2023-12-10', N'',0)
INSERT dbo.PhanCong VALUES
	('PC004','NV004','CV001', 'CT001','2022-11-10','2025-12-10', N'',0)
INSERT dbo.PhanCong VALUES
	('PC005','NV005','CV006', 'CT001','2022-12-11','2024-12-10', N'',0)
go
/*
SELECT * FROM dbo.PhanCong

INSERT dbo.NguoiDung (TenDangNhap, MatKhau)
SELECT MaNV, '12345' FROM dbo.NhanVien
GO*/
--delete from NhanVien where MaNV = 'NV008'
SELECT * FROM dbo.NguoiDung
SELECT * FROM dbo.NhanVien


