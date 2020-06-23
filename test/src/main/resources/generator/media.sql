-- --------------------------------------------------------
-- 主机:                           192.168.215.70
-- 服务器版本:                        5.5.18 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 newaudimedia.media 结构
CREATE TABLE IF NOT EXISTS `media` (
  
  `media_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '媒体名称',
  `media_category` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '媒体大类',
  `media_type` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '媒体类别',
  `region` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '区域',
  `city` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '城市',
  `media_level` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '媒体级别',
  `media_impact_index` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '媒体影响力指数',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='媒体';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
