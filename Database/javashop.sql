-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 27, 2021 at 05:09 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javashop`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_account`
--

CREATE TABLE `tbl_account` (
  `id_account` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_account`
--

INSERT INTO `tbl_account` (`id_account`, `username`, `password`, `status`) VALUES
('EM01', 'luan', 'dd960d42bb47da21af3b3b0c31684540', 1),
('EM1640534693695', 'kiet', 'e6bbeabfe26b581fcd1f5592c927f704', 0),
('EM1640621088774', 'manh', '75f0817da202dfff6c1e8bf6ec5fad8f', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_brand`
--

CREATE TABLE `tbl_brand` (
  `id_brand` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_brand`
--

INSERT INTO `tbl_brand` (`id_brand`, `name`) VALUES
('BR1640534951452', 'CoolMate'),
('BR1640534998209', 'BJ'),
('BR1640621183186', 'BK');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_category`
--

CREATE TABLE `tbl_category` (
  `id_category` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_category`
--

INSERT INTO `tbl_category` (`id_category`, `name`) VALUES
('CA01', 'Áo'),
('CA02', 'Quần');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_categorychild`
--

CREATE TABLE `tbl_categorychild` (
  `id_categorychild` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_category` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_categorychild`
--

INSERT INTO `tbl_categorychild` (`id_categorychild`, `id_category`, `name`) VALUES
('CC001', 'CA01', 'Áo thun'),
('CC02', 'CA01', 'Áo sơ mi'),
('CC1640534755730', 'CA02', 'Quần dài'),
('CC1640534793284', 'CA02', 'Quần lửng');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_customer`
--

CREATE TABLE `tbl_customer` (
  `id_customer` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `createdate` date NOT NULL,
  `point` int(11) NOT NULL DEFAULT 0,
  `email` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_customer`
--

INSERT INTO `tbl_customer` (`id_customer`, `fullname`, `createdate`, `point`, `email`, `address`, `phone`) VALUES
('CU1640535729418', 'Luân', '2021-12-26', 335, 'tranvuluan@gmail.com', 'HCM', '08583474'),
('CU1640621278509', 'Kiệt', '2021-12-27', 340, 'kiet@gmail.com', 'HCM', '12312313');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_employee`
--

CREATE TABLE `tbl_employee` (
  `id_employee` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_position` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `cmnd` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_employee`
--

INSERT INTO `tbl_employee` (`id_employee`, `id_position`, `fullname`, `birthday`, `address`, `phone`, `email`, `image`, `cmnd`, `gender`) VALUES
('EM01', 'p1', 'Luân', '2001-12-27', 'Ninh Thuận', '0868486575', 'tranvuluan069@gmail.com', '123', '12312312313123123', 'Nam'),
('EM1640534693695', 'p1', 'Kiet', '2001-01-01', 'Ho Chi Minh', '0123456788', 'kiet@gmail.com', 'abc', '12312321312', 'Nam'),
('EM1640621088774', 'p1', 'Manh', '2001-01-01', 'HCM', '0912312', 'manh@gmail.com', 'abc', '123123123', 'Nam');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_order`
--

CREATE TABLE `tbl_order` (
  `id_order` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_customer` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_voucher` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_employee` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `totalprice` float NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_order`
--

INSERT INTO `tbl_order` (`id_order`, `id_customer`, `id_voucher`, `id_employee`, `totalprice`, `date`) VALUES
('OR1640619547251', 'CU1640535729418', 'VC1640534625092', 'EM01', 268000, '2021-12-27'),
('OR1640621277398', 'CU1640621278509', 'VC1640534625092', 'EM01', 272000, '2021-12-27');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_order_item`
--

CREATE TABLE `tbl_order_item` (
  `id_order` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_product` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_order_item`
--

INSERT INTO `tbl_order_item` (`id_order`, `id_product`, `quantity`, `price`) VALUES
('OR1640619547251', 'PR1640619150209', 1, 135000),
('OR1640619547251', 'PR1640619188733', 1, 200000),
('OR1640621277398', 'PR1640621209542', 2, 170000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_permission`
--

CREATE TABLE `tbl_permission` (
  `id_permission` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_permission`
--

INSERT INTO `tbl_permission` (`id_permission`, `name`) VALUES
('PE01', 'Quản lý bán hàng'),
('PE02', 'Quản lý sản phẩm'),
('PE03', 'Quản lý danh mục'),
('PE04', 'Quản lý nhà cung cấp'),
('PE05', 'Quản lý nhập hàng'),
('PE06', 'Quản lý khuyến mãi'),
('PE07', 'Quản lý hóa đơn'),
('PE08', 'Quản lý nhân viên'),
('PE09', 'Quản lý khách hàng'),
('PE10', 'Quản lý quyền'),
('PE11', 'Quản lý thống kê');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_position`
--

CREATE TABLE `tbl_position` (
  `id_position` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_position`
--

INSERT INTO `tbl_position` (`id_position`, `name`) VALUES
('p1', 'Quản lý'),
('p2', 'Nhân viên kho'),
('p3', 'Nhân viên kinh doanh');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pos_permission`
--

CREATE TABLE `tbl_pos_permission` (
  `id_permission` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `id_position` varchar(50) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_pos_permission`
--

INSERT INTO `tbl_pos_permission` (`id_permission`, `id_position`) VALUES
('PE01', 'p1'),
('PE01', 'p3'),
('PE02', 'p1'),
('PE02', 'p3'),
('PE03', 'p1'),
('PE03', 'p3'),
('PE04', 'p1'),
('PE05', 'p1'),
('PE05', 'p2'),
('PE06', 'p1'),
('PE06', 'p3'),
('PE07', 'p1'),
('PE07', 'p3'),
('PE08', 'p1'),
('PE09', 'p1'),
('PE09', 'p3'),
('PE10', 'p1'),
('PE11', 'p1');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_product`
--

CREATE TABLE `tbl_product` (
  `id_product` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_brand` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_categorychild` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` float DEFAULT NULL,
  `image` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_product`
--

INSERT INTO `tbl_product` (`id_product`, `id_brand`, `id_categorychild`, `name`, `quantity`, `price`, `image`, `status`) VALUES
('PR1640619150209', 'BR1640534951452', 'CC001', 'Áo thun Cool', 59, 150000, '10.jpg', 1),
('PR1640619188733', 'BR1640534998209', 'CC02', 'Áo sơ mi BJ', 49, 200000, '25.jpg', 1),
('PR1640621183186', 'BR1640621183186', 'CC1640534755730', 'Quần dài BK', 100, 300000, '35.jpg', 1),
('PR1640621209542', 'BR1640534951452', 'CC02', 'Áo sơ mi hoa hòe', 28, 170000, '38.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_product_sale`
--

CREATE TABLE `tbl_product_sale` (
  `id_product` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `salepercent` float NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_product_sale`
--

INSERT INTO `tbl_product_sale` (`id_product`, `salepercent`, `startdate`, `enddate`) VALUES
('PR1640619150209', 0.1, '2021-12-26', '2022-01-08');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_supplier`
--

CREATE TABLE `tbl_supplier` (
  `id_supplier` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_supplier`
--

INSERT INTO `tbl_supplier` (`id_supplier`, `name`, `address`) VALUES
('SU01', 'Nhà máy Á Châu', 'Trung Quốc'),
('SU02', 'Nhà máy Thượng Hải', 'Trung Quốc');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_voucher`
--

CREATE TABLE `tbl_voucher` (
  `id_voucher` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `discountpercent` float NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_voucher`
--

INSERT INTO `tbl_voucher` (`id_voucher`, `code`, `discountpercent`, `startdate`, `enddate`) VALUES
('VC1640534596143', 'Null', 0, '2020-01-01', '2099-01-01'),
('VC1640534625092', 'UUDAI', 0.2, '2021-01-01', '2022-12-12');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_warehousereceipt`
--

CREATE TABLE `tbl_warehousereceipt` (
  `id_warehousereceipt` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_supplier` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_employee` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp(),
  `totalprice` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_warehousereceipt`
--

INSERT INTO `tbl_warehousereceipt` (`id_warehousereceipt`, `id_supplier`, `id_employee`, `date`, `totalprice`) VALUES
('WA1640619120579', 'SU01', 'EM01', '2021-12-26 17:00:00', 12500000),
('WA1640619837606', 'SU01', 'EM01', '2021-12-26 17:00:00', 1100000),
('WA1640621144981', 'SU01', 'EM01', '2021-12-26 17:00:00', 19500000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_warehousereceipt_detail`
--

CREATE TABLE `tbl_warehousereceipt_detail` (
  `id_warehousereceipt` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_product` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `amount` int(11) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_warehousereceipt_detail`
--

INSERT INTO `tbl_warehousereceipt_detail` (`id_warehousereceipt`, `id_product`, `amount`, `price`) VALUES
('WA1640619120579', 'PR1640619150209', 50, 100000),
('WA1640619120579', 'PR1640619188733', 50, 150000),
('WA1640619837606', 'PR1640619150209', 10, 110000),
('WA1640621144981', 'PR1640621183186', 100, 150000),
('WA1640621144981', 'PR1640621209542', 30, 150000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_account`
--
ALTER TABLE `tbl_account`
  ADD PRIMARY KEY (`id_account`);

--
-- Indexes for table `tbl_brand`
--
ALTER TABLE `tbl_brand`
  ADD PRIMARY KEY (`id_brand`);

--
-- Indexes for table `tbl_category`
--
ALTER TABLE `tbl_category`
  ADD PRIMARY KEY (`id_category`);

--
-- Indexes for table `tbl_categorychild`
--
ALTER TABLE `tbl_categorychild`
  ADD PRIMARY KEY (`id_categorychild`),
  ADD KEY `id_category` (`id_category`);

--
-- Indexes for table `tbl_customer`
--
ALTER TABLE `tbl_customer`
  ADD PRIMARY KEY (`id_customer`);

--
-- Indexes for table `tbl_employee`
--
ALTER TABLE `tbl_employee`
  ADD PRIMARY KEY (`id_employee`);

--
-- Indexes for table `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD PRIMARY KEY (`id_order`),
  ADD KEY `id_voucher` (`id_voucher`),
  ADD KEY `id_customer` (`id_customer`),
  ADD KEY `id_employee` (`id_employee`);

--
-- Indexes for table `tbl_order_item`
--
ALTER TABLE `tbl_order_item`
  ADD PRIMARY KEY (`id_order`,`id_product`),
  ADD KEY `id_product` (`id_product`);

--
-- Indexes for table `tbl_permission`
--
ALTER TABLE `tbl_permission`
  ADD PRIMARY KEY (`id_permission`);

--
-- Indexes for table `tbl_position`
--
ALTER TABLE `tbl_position`
  ADD PRIMARY KEY (`id_position`);

--
-- Indexes for table `tbl_pos_permission`
--
ALTER TABLE `tbl_pos_permission`
  ADD PRIMARY KEY (`id_permission`,`id_position`),
  ADD KEY `id_position` (`id_position`);

--
-- Indexes for table `tbl_product`
--
ALTER TABLE `tbl_product`
  ADD PRIMARY KEY (`id_product`),
  ADD KEY `id_brand` (`id_brand`),
  ADD KEY `id_categorychild` (`id_categorychild`);

--
-- Indexes for table `tbl_product_sale`
--
ALTER TABLE `tbl_product_sale`
  ADD PRIMARY KEY (`id_product`);

--
-- Indexes for table `tbl_supplier`
--
ALTER TABLE `tbl_supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indexes for table `tbl_voucher`
--
ALTER TABLE `tbl_voucher`
  ADD PRIMARY KEY (`id_voucher`);

--
-- Indexes for table `tbl_warehousereceipt`
--
ALTER TABLE `tbl_warehousereceipt`
  ADD PRIMARY KEY (`id_warehousereceipt`),
  ADD KEY `id_employee` (`id_employee`),
  ADD KEY `id_supplier` (`id_supplier`);

--
-- Indexes for table `tbl_warehousereceipt_detail`
--
ALTER TABLE `tbl_warehousereceipt_detail`
  ADD PRIMARY KEY (`id_warehousereceipt`,`id_product`),
  ADD KEY `id_product` (`id_product`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_categorychild`
--
ALTER TABLE `tbl_categorychild`
  ADD CONSTRAINT `tbl_categorychild_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `tbl_category` (`id_category`);

--
-- Constraints for table `tbl_employee`
--
ALTER TABLE `tbl_employee`
  ADD CONSTRAINT `tbl_employee_ibfk_1` FOREIGN KEY (`id_employee`) REFERENCES `tbl_account` (`id_account`);

--
-- Constraints for table `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD CONSTRAINT `tbl_order_ibfk_1` FOREIGN KEY (`id_voucher`) REFERENCES `tbl_voucher` (`id_voucher`),
  ADD CONSTRAINT `tbl_order_ibfk_2` FOREIGN KEY (`id_customer`) REFERENCES `tbl_customer` (`id_customer`),
  ADD CONSTRAINT `tbl_order_ibfk_3` FOREIGN KEY (`id_employee`) REFERENCES `tbl_employee` (`id_employee`);

--
-- Constraints for table `tbl_order_item`
--
ALTER TABLE `tbl_order_item`
  ADD CONSTRAINT `tbl_order_item_ibfk_1` FOREIGN KEY (`id_order`) REFERENCES `tbl_order` (`id_order`),
  ADD CONSTRAINT `tbl_order_item_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `tbl_product` (`id_product`);

--
-- Constraints for table `tbl_pos_permission`
--
ALTER TABLE `tbl_pos_permission`
  ADD CONSTRAINT `tbl_pos_permission_ibfk_1` FOREIGN KEY (`id_permission`) REFERENCES `tbl_permission` (`id_permission`),
  ADD CONSTRAINT `tbl_pos_permission_ibfk_2` FOREIGN KEY (`id_position`) REFERENCES `tbl_position` (`id_position`);

--
-- Constraints for table `tbl_product`
--
ALTER TABLE `tbl_product`
  ADD CONSTRAINT `tbl_product_ibfk_1` FOREIGN KEY (`id_brand`) REFERENCES `tbl_brand` (`id_brand`),
  ADD CONSTRAINT `tbl_product_ibfk_2` FOREIGN KEY (`id_categorychild`) REFERENCES `tbl_categorychild` (`id_categorychild`);

--
-- Constraints for table `tbl_product_sale`
--
ALTER TABLE `tbl_product_sale`
  ADD CONSTRAINT `tbl_product_sale_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `tbl_product` (`id_product`);

--
-- Constraints for table `tbl_warehousereceipt`
--
ALTER TABLE `tbl_warehousereceipt`
  ADD CONSTRAINT `tbl_warehousereceipt_ibfk_1` FOREIGN KEY (`id_employee`) REFERENCES `tbl_employee` (`id_employee`),
  ADD CONSTRAINT `tbl_warehousereceipt_ibfk_2` FOREIGN KEY (`id_supplier`) REFERENCES `tbl_supplier` (`id_supplier`);

--
-- Constraints for table `tbl_warehousereceipt_detail`
--
ALTER TABLE `tbl_warehousereceipt_detail`
  ADD CONSTRAINT `tbl_warehousereceipt_detail_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `tbl_product` (`id_product`),
  ADD CONSTRAINT `tbl_warehousereceipt_detail_ibfk_2` FOREIGN KEY (`id_warehousereceipt`) REFERENCES `tbl_warehousereceipt` (`id_warehousereceipt`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
