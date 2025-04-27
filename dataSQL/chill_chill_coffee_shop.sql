
USE [master]
GO

CREATE DATABASE [QuanLyQuanCaPhe]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLyQuanCaPhe', FILENAME = N'D:\QuanLyQuanCaPhe.mdf' , SIZE = 8192KB , MAXSIZE = 51200KB , FILEGROWTH = 10%)
 LOG ON 
( NAME = N'QuanLyQuanCaPhe_log', FILENAME = N'D:\QuanLyQuanCaPhe_log.ldf' , SIZE = 8192KB , MAXSIZE = 51200KB , FILEGROWTH = 10%)

GO
USE [QuanLyQuanCaPhe]

GO

CREATE TABLE ChucVu(
    MaChucVu int  NOT NULL PRIMARY KEY,
	TenChucVu nvarchar(50) NULL,
    LuongTheoGio money NULL
)
GO
INSERT INTO ChucVu ([MaChucVu], [TenChucVu], [LuongTheoGio])
VALUES (1, N'Thu ngân', 25000),
       (2, N'Pha chế', 25000),
	   (3, N'Phục vụ', 25000),
	   (4, N'Quản lý', 30000)
GO
CREATE TABLE KhuVuc(
    MaKV int  NOT NULL PRIMARY KEY,
	TenKV nvarchar(50) NULL
)
GO
INSERT INTO KhuVuc (MaKV, TenKV)
VALUES (1, N'Tầng 1'),
       (2, N'Tầng 2'),
	   (3, N'Khu ngoài trời'),
	   (4, N'Mang về')

GO

CREATE TABLE KhachHang(
    MaKH nvarchar(10) NOT NULL PRIMARY KEY,
	TenKH nvarchar(50),
	SoDienThoai nvarchar(15),
	DiemTichLuy int DEFAULT 0 -- giá trị mặc định ban đầu là 0
)
GO
-- tạo khách hàng chỉ có tên với số điện thoại
INSERT INTO KhachHang (TenKH, SoDienThoai)
VALUES (N'ac', '0934861057'),
(N'chi tam', '1244444'),
(N'anh năm', '15444333')
GO
SET IDENTITY_INSERT KhachHang ON;
INSERT INTO KhachHang (MaKH, TenKH, SoDienThoai)
VALUES (0, N'Khách hàng lẻ', '0000000000')
GO
CREATE TABLE CaLamViec (
    MaCa int NOT NULL PRIMARY KEY,
    TenCa nvarchar(50) NOT NULL,
    GioVaoLam time NOT NULL,
    GioKetThuc time NOT NULL
)
Go
INSERT INTO CaLamViec (MaCa, TenCa, GioVaoLam, GioKetThuc)
VALUES ( 1 ,N'Ca sáng', '06:00:00', '10:00:00'),
		( 2 ,N'Ca trưa', '10:00:00', '14:00:00'),
		( 3 ,N'Ca chiều', '14:00:00', '18:00:00'),
		( 4 ,N'Ca tối', '18:00:00', '22:00:00')
GO
CREATE TABLE NhanVien (
    MaNV nvarchar(10) NOT NULL PRIMARY KEY,
    Ten nvarchar(50) NOT NULL,
    TrangThaiLamViec nvarchar(20) NOT NULL, -- Nghỉ việc, Đang làm việc
    NgayVaoLam date NOT NULL,
    SoDienThoai nvarchar(15) NULL,
    Email nvarchar(50) NULL,
    MaChucVu int NOT NULL,
    FOREIGN KEY (MaChucVu) REFERENCES ChucVu(MaChucVu)
)
GO
CREATE TABLE LichLamViec (
    MaLLV int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	MaNV nvarchar(10) NOT NULL,
	MaCa int NOT NULL,
    NgayLamViec date,
	FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
	FOREIGN KEY (MaCa) REFERENCES CaLamViec(MaCa)
)
go
CREATE TABLE TaiKhoan (
    MaTK int PRIMARY KEY,
	MaCa int NOT NULL,
    TenDN nvarchar(10) NOT NULL ,-- tên đăng nhập duy nhất không trùng
    MatKhau varchar(50) NOT NULL,
	Mota nvarchar(100) null, -- mô tả về ca làm việc
	FOREIGN KEY (MaCa) REFERENCES CaLamViec(MaCa),
	FOREIGN KEY (TenDN) REFERENCES NhanVien(MaNV)
)

go
INSERT INTO TaiKhoan (MaTK, MaCa, TenDN, MatKhau, Mota)
VALUES 
    (1, 1, 'NV01', N'sang123', N'Ca làm việc buổi sáng'),
    (2, 2, 'NV02', N'trua123', N'Ca làm việc buổi trưa'),
    (3, 3, 'NV03', N'chieu123', N'Ca làm việc buổi chiều'),
	(4, 4, 'NV04', N'toi123', N'Ca làm việc buổi tối')
go
CREATE TABLE MonNuoc (
    MaMon nvarchar(10) NOT NULL PRIMARY KEY,
	TenMon nvarchar(50) NOT NULL,
	DonGia money NULL, 
    DonViTinh nvarchar(10) NULL, 
	DanhMuc nvarchar(50) NOT NULL
)
-- Thêm dữ liệu cho bảng MonNuoc
go
INSERT INTO MonNuoc (MaMon, TenMon, DonGia, DonViTinh, DanhMuc)
VALUES 
    ('CF01', N'Cà phê đen', 25000, N'Ly', N'Cà phê'),
    ('CF02', N'Cà phê sữa đá', 30000, N'Ly', N'Cà phê'),
    ('CF03', N'Cappuccino', 35000, N'Ly', N'Cà phê'),
	('CF04', N'Bạc xỉu', 30000, N'Ly', N'Cà phê'),

    ('TR01', N'Trà chanh', 20000, N'Ly', N'Trà'),
    ('TR02', N'Trà sữa', 25000, N'Ly', N'Trà'),
	('TR03', N'Trà đào', 30000, N'Ly', N'Trà'),
	('TR04', N'Trà sữa kem trứng', 35000, N'Ly', N'Trà'),

    ('ST01', N'Sinh tố dâu', 35000, N'Ly', N'Sinh tố'),
    ('ST02', N'Sinh tố bơ', 40000, N'Ly', N'Sinh tố'),
	('ST03', N'Sinh tố xoài', 35000, N'Ly', N'Sinh tố'),

    ('TP01', N'Chocolate chip', 5000, N'Phần', N'Topping'),
    ('TP02', N'Trân châu trắng', 7000, N'Phần', N'Topping'),
	('TP03', N'Thạch phô mai', 7000, N'Phần', N'Topping')
	go
CREATE TABLE HoaDon (
    MaHD int IDENTITY(1,1) NOT NULL PRIMARY KEY,
    MaNV nvarchar(10) NOT NULL,
    MaKH int NOT NULL,
    MaBan int NULL,
    NgayLapHoaDon datetime DEFAULT GETDATE(),
    TrangThai nvarchar(10),
    TongTien money,
    VAT float,
    ChiPhiKhac money,
    TongThu money,
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
)

go
CREATE TABLE ChiTietHoaDon (
    MaCTHD int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	MaHD int NOT NULL,
	MaMon nvarchar(10) NOT NULL,
	DonGia money,
    SoLuong int, 
	ThanhTien money, -- tổng tiền của món đó tính bằng đơn giá * số lượng
	--liên kết khóa
	FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
	FOREIGN KEY (MaMon) REFERENCES MonNuoc(MaMon)
)
go
-- Chèn hóa đơn thứ nhất

INSERT INTO NhanVien (MaNV, Ten, TrangThaiLamViec, NgayVaoLam, SoDienThoai, Email, MaChucVu)
VALUES 
    ('NV01', N'Phạm Văn Đức', N'Đang làm việc', '2023-07-20', '0123456789', 'phamvanduc@example.com', 3),
    ('NV02', N'Lê Thị Lan', N'Đang làm việc', '2023-08-10', '0123456789', 'lelan@example.com', 1),
	('NV03', N'Phạm Văn Hải', N'Đang làm việc', '2023-07-20', '0123456789', 'phamc@example.com', 3),
    ('NV04', N'Hu hu hu', N'Đang làm việc', '2023-08-10', '0123456789', 'hun@example.com', 1)
	go
INSERT INTO HoaDon (MaNV, MaKH, MaBan, TrangThai, TongTien, VAT, ChiPhiKhac, TongThu)
VALUES ('NV01', 1, null, N'đang xử lý', 100000, 0.1, 0, 90000),
		 ('NV02', 2, null, N'hoàn tất', 150000, 0.15, 0, 127500),
	('NV03', 3, NULL, N'đang xử lý', 80000, 0.08, 0, 73600)
--select * from HoaDon
--select * from ChiTietHoaDon
--select * from KhachHang
--select * from TaiKhoan
--insert into KhachHang (TenKH)
--values (null)
--SELECT * FROM NhanVien WHERE MaChucVu = 1
--SELECT DAY(NgayLapHoaDon) AS Ngay, SUM(TongThu) AS tongDoanhThu
--FROM HoaDon
--WHERE CAST(NgayLapHoaDon AS DATE) = CAST(GETDATE() AS DATE)
--GROUP BY DAY(NgayLapHoaDon)
