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

-- 导出  表 ehuizhen.DoctorAudit 结构
CREATE TABLE IF NOT EXISTS `DoctorAudit` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DoctorName` varchar(50) NOT NULL COMMENT '医生姓名',
  `PhoneNum` varchar(30) NOT NULL COMMENT '手机号',
  `Sex` int(11) NOT NULL DEFAULT '0' COMMENT '性别1:男 2:女',
  `Email` varchar(50) DEFAULT NULL COMMENT '邮箱，海外用户校验，国内用户可选',
  `IdCard` varchar(50) NOT NULL COMMENT '身份证',
  `DoctorCertNo` varchar(100) DEFAULT NULL COMMENT '医生职业证书编号',
  `Hospital` varchar(100) DEFAULT NULL COMMENT '所在医院',
  `Department` varchar(50) DEFAULT NULL COMMENT '所在科室',
  `Position` varchar(50) DEFAULT NULL COMMENT '职称',
  `SkilledField` varchar(200) DEFAULT NULL COMMENT '擅长领域',
  `SelfIntro` varchar(200) DEFAULT NULL COMMENT '个人介绍',
  `SelfPhotoUrl` varchar(200) DEFAULT NULL COMMENT '个人照片',
  `ChargingStandard` varchar(50) DEFAULT NULL COMMENT '收费标准，海外用户有自己的收费标准，国内用户按统一标准',
  `Status` int(11) NOT NULL DEFAULT '0' COMMENT '1:第1次提交信息;2:第2次信息;3:待审核;4:审核未通过；5;审核通过',
  `Oversea` int(11) NOT NULL DEFAULT '0' COMMENT '0:国内 1:在海外 ',
  `WxOpenId` varchar(30) DEFAULT NULL COMMENT '微信OpenId',
  `InDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '录入时间',
  `AuditDate` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '审核时间',
  `AuditUserId` int(11) DEFAULT '0' COMMENT '审核人Id',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  ehuizhen.DoctorAudit 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `DoctorAudit` DISABLE KEYS */;
/*!40000 ALTER TABLE `DoctorAudit` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
