-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2025 at 10:16 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `supportsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `douutien`
--

CREATE TABLE `douutien` (
  `MaDoUuTien` int(11) NOT NULL,
  `TenDoUuTien` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `douutien`
--

INSERT INTO `douutien` (`MaDoUuTien`, `TenDoUuTien`) VALUES
(1, 'High'),
(2, 'Medium'),
(3, 'Low');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `HoTen` varchar(50) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `KichHoat` bit(1) NOT NULL DEFAULT b'1',
  `HinhAnh` varchar(255) DEFAULT NULL,
  `Quyen` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`username`, `password`, `HoTen`, `NgaySinh`, `KichHoat`, `HinhAnh`, `Quyen`) VALUES
('admin', '$2a$10$kYtoZzpiGNgbbVT9pRN0quLJhiudWUzktsd7QasF0bd.q6AsX6tnS', 'Admin', '2003-09-10', b'1', 'admin.jpg\r\n', 3),
('bao', '$2a$10$GaiCp6c0rlAR/8ftMrJqeeexeRn4RlG7Qxrm1XmGCkLbOEePNna8K', 'Truong Bảo', '2024-12-11', b'0', 'aa84d5e596cf4422b66685740b2b6839.jpg', 1),
('baomap', '$2a$10$Ui8fYjuZmm1Yj2/Zz9iZS.JoKsmdLuoLzHrIHWia4b/zBOzRg9dHe', 'Trần Văn Bảo', '2024-12-12', b'0', 'ce5d35b3c62a4ba19dfa9df8b8222f70.jpg', 1),
('baomap2', '$2a$10$ro.mBiqbw5DwnSAkK4QB3.lanHPd.bLG8X7jPTVe0jzjCAoA379Mq', 'Trương Văn Bảo', '2024-12-18', b'0', '9c3a7901a6a64329bda52a9c2a853966.jpg', 2),
('binh', '$2a$10$kYbk4u/S0.M/yznm9lrE6.P119L788HiiClVO0opw2arSTBbCFXPm', 'Thanh Binh', '2024-12-18', b'0', 'eaaf0f5783cb4689948dee1effb8ec73.jpg', 1),
('hung', '$2a$10$QekIL8M/RGwpcl42cK3UvOVNgFQrvTSTsl3aZmFo9RWitDPrvGUye', 'Quang Hung', '2024-12-10', b'0', 'f66d12a46a314342a034f8e4968d3c89.jpg', 2),
('khai', '$2a$10$rWhNSeRTGSVfb.Lk7/7qg.9uXfg4UGGz10MBYoKuqTOYG6wmwyA1S', 'Anh Khải', '2024-12-26', b'0', '0eeec373b5e0448c810ee960e0e11545.jpg', 1),
('nhat', '$2a$10$5Mch0neozmkoCmwg98WnK.EwlWw3mBqemYtpnPQV12FkyyB9TYT0y', 'Lâm Xuan Minh Nhat', '2024-12-26', b'0', '1b823a829f994536b3b09f2d9fdf5164.jpg', 2),
('phuong', '$2a$10$VUgCpm51631rxwFC9ym3gOsDo4WBqwLUxrrywvKCysY4QcGR4e3Hy', NULL, NULL, b'0', NULL, 1),
('thao', '$2a$10$t/5b9GoPMm1/tOd7wwzIMOcjpWxidgDfURQx94XkjON55nRaXHPLq', 'Minh Thảo', '2024-12-10', b'0', 'a3632e616a684c6093da09d5a72bce6b.jpg', 2),
('van', '$2a$10$5ESd4EEd2iBra03pMXns8uRiiOZHtazlkYrdVe7OwtT6ibpm2zWze', NULL, NULL, b'0', NULL, 2),
('vinh', '$2a$10$I1UYB5Us8HRzPKqNLOLdZ.gS9coT8ARUffW7RtSGVCDxfZ.SHVu7y', NULL, NULL, b'0', NULL, 1),
('Zeros', '$2a$10$HCBN3dRc7xxrJN45C5Cs6.ye9zxiGgosUe3o0tQtEp0DL0MWbgLVu', NULL, NULL, b'0', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `yeucau`
--

CREATE TABLE `yeucau` (
  `MaYeuCau` int(11) NOT NULL,
  `TieuDe` varchar(255) NOT NULL,
  `NoiDung` varchar(255) NOT NULL,
  `NgayGui` date NOT NULL,
  `MaDoUuTien` int(11) NOT NULL,
  `MaNvGui` varchar(50) NOT NULL,
  `MaNvXuLy` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `yeucau`
--

INSERT INTO `yeucau` (`MaYeuCau`, `TieuDe`, `NoiDung`, `NgayGui`, `MaDoUuTien`, `MaNvGui`, `MaNvXuLy`) VALUES
(1, 'Tester phần mềm diệt virus', '                          				sdasdasd', '2024-12-29', 1, 'binh', 'thao'),
(2, 'Dự án Biệt Thự', 'Xây biệt thự bằng phần mềm java                          				', '2024-12-29', 2, 'bao', 'thao'),
(3, 'Dự án .NET', 'Xây phần mềm bán hàng bằng ASP.NET                          				', '2024-12-29', 3, 'bao', 'van'),
(4, 'Dự án PHP', 'Xây dựng web đấu giá bằng PHP                          				', '2024-12-29', 2, 'binh', 'van'),
(5, 'Phần mềm quản lý', 'Phần mềm quản lý Sinh Viên', '2024-12-20', 3, 'binh', 'van'),
(6, 'Phần mềm quản lý', 'Phần mềm quản lý Sinh Viên', '2024-12-25', 3, 'bao', 'nhat'),
(7, 'Dự án xây dụng công ty', 'Dự án xây dụng công ty TNHH Aptech       				', '2024-12-30', 1, 'binh', 'van'),
(8, 'Dự án bất động sản', 'Lập 1 công ty về bất động sản                          				', '2024-12-30', 2, 'bao', 'nhat'),
(9, 'Phòng chống hỏa hoạn', 'Phòng chống hỏa hoạn content', '2024-12-30', 2, 'bao', 'thao'),
(10, 'Dự án chuột', 'Dự án chuột 123            				', '2024-12-30', 2, 'bao', 'thao');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `douutien`
--
ALTER TABLE `douutien`
  ADD PRIMARY KEY (`MaDoUuTien`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `yeucau`
--
ALTER TABLE `yeucau`
  ADD PRIMARY KEY (`MaYeuCau`),
  ADD KEY `MaNvGui` (`MaNvGui`),
  ADD KEY `MaNvXuLy` (`MaNvXuLy`),
  ADD KEY `MaDoUuTien` (`MaDoUuTien`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `yeucau`
--
ALTER TABLE `yeucau`
  MODIFY `MaYeuCau` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `yeucau`
--
ALTER TABLE `yeucau`
  ADD CONSTRAINT `yeucau_ibfk_2` FOREIGN KEY (`MaNvGui`) REFERENCES `nhanvien` (`username`),
  ADD CONSTRAINT `yeucau_ibfk_3` FOREIGN KEY (`MaNvXuLy`) REFERENCES `nhanvien` (`username`),
  ADD CONSTRAINT `yeucau_ibfk_4` FOREIGN KEY (`MaDoUuTien`) REFERENCES `douutien` (`MaDoUuTien`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
