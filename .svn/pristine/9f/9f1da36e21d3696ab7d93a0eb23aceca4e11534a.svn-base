-- --------------------------------------------------------
-- 主机:                           192.168.5.102
-- 服务器版本:                        5.5.49-0ubuntu0.14.04.1 - (Ubuntu)
-- 服务器操作系统:                      debian-linux-gnu
-- HeidiSQL 版本:                  8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 ehuizhen_test.Patient 结构
CREATE TABLE IF NOT EXISTS `Patient` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `PatientName` varchar(20) NOT NULL,
  `PhoneNum` char(11) NOT NULL,
  `MobileIMEI` varchar(100) DEFAULT NULL,
  `PassWord` varchar(100) NOT NULL,
  `Sex` int(11) DEFAULT NULL,
  `Photo` varchar(50) DEFAULT NULL,
  `BirthDay` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `WeixinAppId` varchar(100) DEFAULT NULL COMMENT '微信openId',
  `Address` varchar(50) DEFAULT NULL,
  `Status` int(11) DEFAULT '1' COMMENT '0 无效 1 有效',
  `InUser` int(11) DEFAULT NULL,
  `InDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `EditUser` int(11) DEFAULT NULL,
  `EditDate` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `OpenId` varchar(30) DEFAULT NULL COMMENT '微信openId',
  `BindStatus` int(11) DEFAULT '0' COMMENT '1:openId已经和账号绑定',
  `BirthYear` int(11) DEFAULT '0' COMMENT '出生年',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
