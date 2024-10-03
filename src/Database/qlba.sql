-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: qlba
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethoadon` (
  `SOHD` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MASP` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MASIZE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SOLUONG` int NOT NULL,
  `DONGIA` double NOT NULL,
  PRIMARY KEY (`SOHD`,`MASP`,`MASIZE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
INSERT INTO `chitiethoadon` VALUES ('HD0','SP1','SIZE0',1,270000),('HD0','SP5','SIZE1',1,281000),('HD1','SP4','SIZE4',1,427000),('HD1','SP7','SIZE3',1,360000),('HD1','SP7','SIZE4',1,360000),('HD2','SP6','SIZE4',1,193000),('HD2','SP7','SIZE4',1,360000);
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietphieunhap`
--

DROP TABLE IF EXISTS `chitietphieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietphieunhap` (
  `MAPN` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MASP` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SOLUONG` int NOT NULL,
  `GIANHAP` double NOT NULL,
  `THANHTIEN` double NOT NULL,
  `MASIZE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`MAPN`,`MASP`,`MASIZE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietphieunhap`
--

LOCK TABLES `chitietphieunhap` WRITE;
/*!40000 ALTER TABLE `chitietphieunhap` DISABLE KEYS */;
INSERT INTO `chitietphieunhap` VALUES ('PN001','SP1',10,264000,2640000,'SIZE0'),('PN001','SP1',10,264000,2640000,'SIZE1'),('PN001','SP1',10,264000,2640000,'SIZE2'),('PN022','SP2',20,129900,2598000,'SIZE1'),('PN022','SP2',10,129900,1299000,'SIZE2'),('PN023','SP3',10,275000,2750000,'SIZE3'),('PN023','SP4',15,418000,6270000,'SIZE4'),('PN023','SP5',10,275000,2750000,'SIZE1'),('PN023','SP5',10,275000,2750000,'SIZE2'),('PN024','SP6',20,189000,3780000,'SIZE4'),('PN025','SP7',10,352000,3520000,'SIZE3'),('PN025','SP7',5,352000,1760000,'SIZE4');
/*!40000 ALTER TABLE `chitietphieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietquyen`
--

DROP TABLE IF EXISTS `chitietquyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietquyen` (
  `MAQUYEN` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MACHUCNANG` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `HANHDONG` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`MAQUYEN`,`MACHUCNANG`,`HANHDONG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietquyen`
--

LOCK TABLES `chitietquyen` WRITE;
/*!40000 ALTER TABLE `chitietquyen` DISABLE KEYS */;
INSERT INTO `chitietquyen` VALUES ('QNV','HD','Thêm'),('QNV','HD','Xem'),('QNV','KH','Thêm'),('QNV','KH','Xem'),('QNV','LOAI','Xem'),('QNV','NCC','Xem'),('QNV','SIZE','Xem'),('QNV','SP','Xem'),('QNV','TK','Xem'),('QQLBH','HD','Sửa'),('QQLBH','HD','Thêm'),('QQLBH','HD','Xem'),('QQLBH','HD','Xóa'),('QQLBH','KH','Thêm'),('QQLBH','KH','Xem'),('QQLBH','LOAI','Thêm'),('QQLBH','LOAI','Xem'),('QQLBH','NCC','Xem'),('QQLBH','NULLThK','Xem'),('QQLBH','NV','Sửa'),('QQLBH','NV','Thêm'),('QQLBH','NV','Xem'),('QQLBH','NV','Xóa'),('QQLBH','PN','Xem'),('QQLBH','QLK','Xem'),('QQLBH','SIZE','Sửa'),('QQLBH','SIZE','Thêm'),('QQLBH','SIZE','Xem'),('QQLBH','SIZE','Xóa'),('QQLBH','SP','Sửa'),('QQLBH','SP','Thêm'),('QQLBH','SP','Xem'),('QQLBH','SP','Xóa'),('QQLBH','TK','Sửa'),('QQLBH','TK','Thêm'),('QQLBH','TK','Xem'),('QQLBH','TK','Xóa'),('QQLHT','HD','Sửa'),('QQLHT','HD','Thêm'),('QQLHT','HD','Xem'),('QQLHT','HD','Xóa'),('QQLHT','KH','Sửa'),('QQLHT','KH','Thêm'),('QQLHT','KH','Xem'),('QQLHT','KH','Xóa'),('QQLHT','LOAI','Sửa'),('QQLHT','LOAI','Thêm'),('QQLHT','LOAI','Xem'),('QQLHT','LOAI','Xóa'),('QQLHT','NCC','Sửa'),('QQLHT','NCC','Thêm'),('QQLHT','NCC','Xem'),('QQLHT','NCC','Xóa'),('QQLHT','NULLThK','Xem'),('QQLHT','NV','Sửa'),('QQLHT','NV','Thêm'),('QQLHT','NV','Xem'),('QQLHT','NV','Xóa'),('QQLHT','PN','Sửa'),('QQLHT','PN','Thêm'),('QQLHT','PN','Xem'),('QQLHT','PN','Xóa'),('QQLHT','PQ','Sửa'),('QQLHT','PQ','Thêm'),('QQLHT','PQ','Xem'),('QQLHT','PQ','Xóa'),('QQLHT','QLK','Xem'),('QQLHT','SIZE','Sửa'),('QQLHT','SIZE','Thêm'),('QQLHT','SIZE','Xem'),('QQLHT','SIZE','Xóa'),('QQLHT','SP','Sửa'),('QQLHT','SP','Thêm'),('QQLHT','SP','Xem'),('QQLHT','SP','Xóa'),('QQLHT','TK','Sửa'),('QQLHT','TK','Thêm'),('QQLHT','TK','Xem'),('QQLHT','TK','Xóa'),('QQLK','HD','Sửa'),('QQLK','HD','Thêm'),('QQLK','HD','Xem'),('QQLK','HD','Xóa'),('QQLK','KH','Xem'),('QQLK','LOAI','Sửa'),('QQLK','LOAI','Thêm'),('QQLK','LOAI','Xem'),('QQLK','LOAI','Xóa'),('QQLK','NCC','Sửa'),('QQLK','NCC','Thêm'),('QQLK','NCC','Xem'),('QQLK','NCC','Xóa'),('QQLK','NULLThK','Xem'),('QQLK','NV','Xem'),('QQLK','PN','Sửa'),('QQLK','PN','Thêm'),('QQLK','PN','Xem'),('QQLK','PN','Xóa'),('QQLK','QLK','Xem'),('QQLK','SIZE','Sửa'),('QQLK','SIZE','Thêm'),('QQLK','SIZE','Xem'),('QQLK','SIZE','Xóa'),('QQLK','SP','Sửa'),('QQLK','SP','Thêm'),('QQLK','SP','Xem'),('QQLK','SP','Xóa'),('QQLK','TK','Xem');
/*!40000 ALTER TABLE `chitietquyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietsanpham`
--

DROP TABLE IF EXISTS `chitietsanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietsanpham` (
  `MASP` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MASIZE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SOLUONG` int NOT NULL,
  PRIMARY KEY (`MASP`,`MASIZE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietsanpham`
--

LOCK TABLES `chitietsanpham` WRITE;
/*!40000 ALTER TABLE `chitietsanpham` DISABLE KEYS */;
INSERT INTO `chitietsanpham` VALUES ('SP1','SIZE0',9),('SP1','SIZE1',10),('SP1','SIZE2',10),('SP2','SIZE1',20),('SP2','SIZE2',10),('SP3','SIZE3',10),('SP4','SIZE4',14),('SP5','SIZE1',9),('SP5','SIZE2',10),('SP6','SIZE4',19),('SP7','SIZE3',9),('SP7','SIZE4',3);
/*!40000 ALTER TABLE `chitietsanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chucnang`
--

DROP TABLE IF EXISTS `chucnang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chucnang` (
  `MACHUCNANG` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TENCHUCNANG` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`MACHUCNANG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chucnang`
--

LOCK TABLES `chucnang` WRITE;
/*!40000 ALTER TABLE `chucnang` DISABLE KEYS */;
INSERT INTO `chucnang` VALUES ('HD','Hoá đơn'),('KH','Khách hàng'),('LOAI','Loại'),('NCC','Nhà cung cấp'),('NULLThK','Thống kê'),('NV','Nhân viên'),('PN','Phiếu nhập'),('PQ','Phân quyền'),('QLK','Kho'),('SIZE','Size'),('SP','Sản phẩm'),('TK','Tài khoản');
/*!40000 ALTER TABLE `chucnang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hinh`
--

DROP TABLE IF EXISTS `hinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hinh` (
  `TENHINH` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MASP` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`TENHINH`,`MASP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hinh`
--

LOCK TABLES `hinh` WRITE;
/*!40000 ALTER TABLE `hinh` DISABLE KEYS */;
INSERT INTO `hinh` VALUES ('ao_hoodie_mau_hot.jpg','SP6'),('ao_hoodie_phoi.jpg','SP5'),('ao_hoodie_zip.jpg','SP7'),('asm_oxford_ke_soc_1.jpg','SP4'),('asm_oxford_ke_soc_2.jpg','SP4'),('asm_soc_1.jpg','SP3'),('asm_soc_2.jpg','SP3'),('asm_tay_dai.jpg','SP1'),('didika_ao_len_1.jpg','SP2'),('didita_ao_len_2.jpg','SP2');
/*!40000 ALTER TABLE `hinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `SOHD` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NGAYHD` date NOT NULL,
  `MAKH` int NOT NULL,
  `MANV` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TONGTIEN` double NOT NULL,
  `TIENGIAMGIA` double DEFAULT NULL,
  `THOIGIAN` time NOT NULL,
  PRIMARY KEY (`SOHD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES ('HD0','2024-10-03',10,'NV1',551000,0,'01:43:26'),('HD1','2024-10-03',13,'NV1',1147000,0,'01:45:56'),('HD2','2024-10-03',11,'NV1',553000,0,'01:47:03');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MAKH` int NOT NULL AUTO_INCREMENT,
  `TENKH` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SDT` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DIEMTICHLUY` int NOT NULL,
  `TRANGTHAI` int NOT NULL,
  PRIMARY KEY (`MAKH`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (10,'Thanh Phương','0376784589',55,1),(11,'Đinh Quỳnh','0984536786',55,1),(13,'Hoàng Oanh','0985735985',114,1);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai`
--

DROP TABLE IF EXISTS `loai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loai` (
  `MALOAI` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TENLOAI` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TINHTRANG` int NOT NULL,
  PRIMARY KEY (`MALOAI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai`
--

LOCK TABLES `loai` WRITE;
/*!40000 ALTER TABLE `loai` DISABLE KEYS */;
INSERT INTO `loai` VALUES ('LOAI0','Áo sơ mi',1),('LOAI1','Áo len',1),('LOAI2','Áo khoác',1),('LOAI3','Áo hoodie',1);
/*!40000 ALTER TABLE `loai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `MANCC` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TENNCC` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SDT` int NOT NULL,
  `TRANGTHAI` tinyint DEFAULT '1',
  PRIMARY KEY (`MANCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
INSERT INTO `nhacungcap` VALUES ('NCC0','Zara',984567812,1),('NCC1','Uniqlo',984567813,1),('NCC2','GAP',984567814,1),('NCC3','ASOS',984567815,1);
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MANV` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TENNV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CHUCVU` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SDT` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DIACHI` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `EMAIL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRANGTHAI` int NOT NULL,
  PRIMARY KEY (`MANV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('NV1','Oanh','Quản lí hệ thống','0982317898','TP HCM','oanh123@gmail.com',1),('NV2','Thanh Phương','Nhân viên bán hàng','0425781236','Quận 7','phuong@gmail.com',1),('NV3','Tiệp','Quản lý kho','0567891234','Quận 5','tiep@gmail.com',1);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieunhap` (
  `MAPN` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MANV` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NGAYNHAP` date NOT NULL,
  `TONGTIEN` double NOT NULL,
  `MANCC` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DEM` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`MAPN`),
  UNIQUE KEY `DEM` (`DEM`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieunhap`
--

LOCK TABLES `phieunhap` WRITE;
/*!40000 ALTER TABLE `phieunhap` DISABLE KEYS */;
INSERT INTO `phieunhap` VALUES ('PN001','NV1','2024-10-03',7920000,'NCC0',21),('PN022','NV1','2024-10-03',3897000,'NCC1',22),('PN023','NV1','2024-10-03',17270100,'NCC0',23),('PN024','NV1','2024-10-03',5670000,'NCC2',24),('PN025','NV1','2024-10-03',12320000,'NCC3',25);
/*!40000 ALTER TABLE `phieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen`
--

DROP TABLE IF EXISTS `quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quyen` (
  `MAQUYEN` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TENQUYEN` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`MAQUYEN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen`
--

LOCK TABLES `quyen` WRITE;
/*!40000 ALTER TABLE `quyen` DISABLE KEYS */;
INSERT INTO `quyen` VALUES ('QNV','Quyền nhân viên'),('QQLBH','Quyền quản lí bán hàng'),('QQLHT','Quyền quản lí hệ thống'),('QQLK','Quyền quản lí kho');
/*!40000 ALTER TABLE `quyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `MASP` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MALOAI` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `PRICE` double NOT NULL,
  `TENSP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRANGTHAI` tinyint NOT NULL,
  PRIMARY KEY (`MASP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES ('SP1','LOAI0',270000,'Áo sơ mi dài tay Odin Club',1),('SP2','LOAI1',133000,'DIDIKA Áo Len Tay Dài Cổ Tròn Dáng Rộng',1),('SP3','LOAI0',281000,'Áo sơ mi sọc Boizclub Premium',1),('SP4','LOAI0',427000,'Áo Sơ Mi Oxford Kẻ Sọc',1),('SP5','LOAI2',281000,'Áo Khoác Hoodie Phối Zip INTERBREAK',1),('SP6','LOAI2',193000,'ÁO KHOÁC HOODIE MẪU HOT TREND ESTIAL',1),('SP7','LOAI3',360000,'Áo Hoodie Zip',1);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size` (
  `MASIZE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TENSIZE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRANGTHAI` tinyint DEFAULT '1',
  PRIMARY KEY (`MASIZE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES ('SIZE0','S',1),('SIZE1','M',1),('SIZE2','L',1),('SIZE3','XL',1),('SIZE4','XXL',1);
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `MANV` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `USERNAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `PASSWORD` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NGAYTAOTK` date NOT NULL,
  `TINHTRANG` int NOT NULL,
  `MAQUYEN` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('NV1','NV1','SangHard!','2023-02-13',1,'QQLHT'),('NV2','NV2','phuong123','2024-09-18',1,'QNV'),('NV3','NV3','tiep123','2024-09-18',1,'QQLK');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-03  1:49:38
