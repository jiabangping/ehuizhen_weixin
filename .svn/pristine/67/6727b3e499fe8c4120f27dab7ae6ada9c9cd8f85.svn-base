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

-- 导出  表 ehuizhen_test.WeiXinAccount 结构
CREATE TABLE IF NOT EXISTS `WeiXinAccount` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DoctorAccountBindDate` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `PatientAccountBindDate` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `Status` int(11) NOT NULL DEFAULT '1' COMMENT '0:未关注，1:关注,',
  `SubscribeDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UnSubscribeDate` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `WxOpenId` varchar(100) NOT NULL unique DEFAULT '',
  `PatientId` int(20) DEFAULT NULL,
  `DoctorId` int(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK4qe842shmd67dlrxlhdbfceoy` (`DoctorId`),
  KEY `FKof04jnmbf4p81dtjljqxg3aaj` (`PatientId`),
  CONSTRAINT `FKof04jnmbf4p81dtjljqxg3aaj` FOREIGN KEY (`PatientId`) REFERENCES `Patient` (`Id`),
  CONSTRAINT `FK4qe842shmd67dlrxlhdbfceoy` FOREIGN KEY (`DoctorId`) REFERENCES `Doctor` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
