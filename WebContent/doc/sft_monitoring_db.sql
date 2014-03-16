create database sft_monitoring_db;
use sft_monitoring_db;

/*
MySQL Data Transfer
Source Host: localhost
Source Database: sft_monitoring_db
Target Host: localhost
Target Database: sft_monitoring_db
Date: 2013/3/27 18:56:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for contacter
-- ----------------------------
DROP TABLE IF EXISTS `contacter`;
CREATE TABLE `contacter` (
  `id` int(11) NOT NULL auto_increment,
  `createDate` datetime default NULL,
  `email` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contactgroup
-- ----------------------------
DROP TABLE IF EXISTS `contactgroup`;
CREATE TABLE `contactgroup` (
  `id` int(11) NOT NULL auto_increment,
  `contacterIds` varchar(255) default NULL,
  `groupName` varchar(255) default NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for emailconfig
-- ----------------------------
DROP TABLE IF EXISTS `emailconfig`;
CREATE TABLE `emailconfig` (
  `id` int(11) NOT NULL auto_increment,
  `emailPassWord` varchar(255) default NULL,
  `mailServerHost` varchar(255) default NULL,
  `mailServerPort` varchar(255) default NULL,
  `remark` varchar(255) default NULL,
  `sysEmailAddress` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for source
-- ----------------------------
DROP TABLE IF EXISTS `source`;
CREATE TABLE `source` (
  `id` int(11) NOT NULL auto_increment,
  `emailInterval` float NOT NULL,
  `lastSendEmailTime` datetime default NULL,
  `monitorLevel` int(11) NOT NULL,
  `param` varchar(1000) default NULL,
  `recentlyResponseTime` float NOT NULL,
  `remark` varchar(500) default NULL,
  `responseTime` float NOT NULL,
  `sourceName` varchar(255) default NULL,
  `status` int(11) NOT NULL,
  `sysCode` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `urlAddress` varchar(500) default NULL,
  `fk_conteactGroupId` int(11) default NULL,
  `requestType` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCA90681BEA1FCFFC` (`fk_conteactGroupId`),
  CONSTRAINT `FKCA90681BEA1FCFFC` FOREIGN KEY (`fk_conteactGroupId`) REFERENCES `contactgroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for timer
-- ----------------------------
DROP TABLE IF EXISTS `timer`;
CREATE TABLE `timer` (
  `id` int(11) NOT NULL auto_increment,
  `monitoringLevel` int(11) default NULL,
  `remark` varchar(255) default NULL,
  `time` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `passWord` varchar(255) default NULL,
  `status` bit(1) default NULL,
  `userName` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `contacter` VALUES ('2', '2013-03-14 15:39:28', '214223894@qq.com', '小叶', '18318370303');
INSERT INTO `contactgroup` VALUES ('1', '1,2,', '通用组', '邮件联系人');
INSERT INTO `contactgroup` VALUES ('3', '', '特殊联系人组', '特殊联系人组');
INSERT INTO `emailconfig` VALUES ('1', '8862125', 'smtp.163.com', '25', null, 'siseideal@163.com');
INSERT INTO `emailconfig` VALUES ('2', null, null, null, null, null);
INSERT INTO `emailconfig` VALUES ('3', null, null, null, null, null);
INSERT INTO `emailconfig` VALUES ('4', null, null, null, null, null);
INSERT INTO `emailconfig` VALUES ('5', null, null, null, null, null);
INSERT INTO `emailconfig` VALUES ('6', null, null, null, null, null);
INSERT INTO `emailconfig` VALUES ('7', null, null, null, null, null);
INSERT INTO `source` VALUES ('1', '0.5', '2013-03-20 17:20:00', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\",\"type\":\"currentWeather\"}', '0.029', '', '1', '电信当天天气接口', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_message.do', '1', 'post');
INSERT INTO `source` VALUES ('2', '0.5', '2013-03-20 17:20:14', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\",\"type\":\"fiveDayWeather\"}', '0.028', '', '1', '电信五天天气接口', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_message.do', '1', 'post');
INSERT INTO `source` VALUES ('3', '0.5', '2013-03-20 17:20:13', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\",\"type\":\"farmMessage\"}', '0.066', '', '1', '电信农产品信息接口', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_message.do', '1', 'post');
INSERT INTO `source` VALUES ('4', '0.5', '2013-03-20 17:20:12', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\",\"type\":\"recruitment\"}', '0.026', '', '1', '电信招聘信息接口', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_message.do', '1', 'post');
INSERT INTO `source` VALUES ('5', '0.5', '2013-03-20 17:20:12', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\",\"type\":\"rentalHouse\"}', '0.026', '', '1', '电信租房信息接口', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_message.do', '1', 'post');
INSERT INTO `source` VALUES ('6', '0.5', '2013-03-20 17:20:11', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\",\"type\":\"blackListHouse\"}', '0.023', '', '1', '电信出租屋黑名单接口', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_message.do', '1', 'post');
INSERT INTO `source` VALUES ('7', '0.5', '2013-03-20 17:20:10', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\",\"type\":\"doctorDesc\"}', '0.026', '', '1', '电信医疗专家接口', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_message.do', '1', 'post');
INSERT INTO `source` VALUES ('8', '0.5', '2013-03-20 17:20:09', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\",\"type\":\"commonNews\"}', '0.146', '', '1', '电信通用新闻接口', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_message.do', '1', 'post');
INSERT INTO `source` VALUES ('9', '0.5', '2013-03-20 17:20:08', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\",\"type\":\"planning\"}', '0.023', '', '1', '电信政务公开规划计划接口', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_message.do', '1', 'post');
INSERT INTO `source` VALUES ('10', '0.5', '2013-03-20 17:20:07', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\",\"type\":\"rentalLow\"}', '0.023', '', '1', '电信出租屋法规接口', '1', '1', '1', ' http://183.60.181.73:8080/ServerPlatform/sft_message.do', '1', 'post');
INSERT INTO `source` VALUES ('11', '0.5', '2013-03-20 17:20:06', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\",\"type\":\"govReport\"}', '0.025', '', '1', '电信政务公开政府报告', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_message.do', '1', 'post');
INSERT INTO `source` VALUES ('12', '0.5', '2013-03-20 17:20:05', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\"}', '0.023', '', '1', '电信基础数据接口', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_basedata.do', '1', 'post');
INSERT INTO `source` VALUES ('13', '0.5', '2013-03-20 17:30:00', '1', 'param={\"key\":\"bd48b12565de2b4935a4a7c26948f574\",\"timestamp\":\"20120314141623\"}', '0.12', '', '1', '电信实时数据接口', '1', '1', '1', 'http://183.60.181.73:8080/ServerPlatform/sft_quick_news.do', '1', 'post');
INSERT INTO `source` VALUES ('14', '0.5', null, '2', '', '0.086', '', '3', '天气预报接口', '1', '1', '2', 'http://121.8.216.253/WebService/Service.asmx/sft_getWeatherDisc', '1', 'post');
INSERT INTO `source` VALUES ('15', '0.5', null, '2', '', '0.026', '', '3', '天气新闻接口', '1', '1', '2', 'http://121.8.216.253/WebService/Service.asmx/sft_getLatestNews', '1', 'post');
INSERT INTO `source` VALUES ('16', '0.5', '2013-03-22 15:28:35', '2', '', '0.042', '', '1', '南沙新闻接口', '1', '1', '2', 'http://www.gzns.gov.cn/nsxx/nsxw/rss_1893.xml', '1', 'post');
INSERT INTO `source` VALUES ('17', '0.5', '2013-03-22 15:27:10', '2', '', '0.024', '', '1', '基层动态接口', '1', '1', '2', 'http://www.gzns.gov.cn/nsxx/jcdt/rss_1893.xml', '1', 'post');
INSERT INTO `source` VALUES ('18', '0.5', '2013-03-22 15:25:45', '2', '', '0.033', '', '1', '媒体看南沙接口', '1', '1', '2', 'http://www.gzns.gov.cn/nsxx/mtkns/rss_1893.xml', '1', 'post');
INSERT INTO `source` VALUES ('19', '0.5', '2013-03-22 15:24:20', '2', '', '0.031', '', '1', '经贸信息接口', '1', '1', '2', 'http://www.gzns.gov.cn/nsxx/jmxx/rss_1893.xml', '1', 'post');
INSERT INTO `source` VALUES ('20', '0.5', '2013-03-22 15:22:55', '2', '', '0.026', '', '1', '机构设置接口', '1', '1', '2', 'http://www.gzns.gov.cn/zwgk/jgsz/index_1895.xml', '1', 'post');
INSERT INTO `source` VALUES ('21', '0.5', '2013-03-22 15:21:30', '2', '', '0.041', '', '1', '领导之窗', '1', '1', '2', 'http://www.gzns.gov.cn/zwgk/ldzc/index_1894.xml', '1', 'post');
INSERT INTO `source` VALUES ('22', '0.5', null, '2', '', '0.085', '', '1', '办事指南', '1', '1', '2', 'http://183.60.181.80/cas_home/index.php/Api-guide', '1', 'post');
INSERT INTO `source` VALUES ('23', '0.5', null, '2', '', '0.088', '', '1', '公安信息', '1', '1', '2', 'http://183.60.181.80/cas_home/index.php/Api-policeInfo', '1', 'post');
INSERT INTO `source` VALUES ('24', '0.5', null, '2', '', '0.091', '', '1', '租房信息黑名单', '1', '1', '2', 'http://183.60.181.80/cas_home/index.php/Api-blacklist', '1', 'post');
INSERT INTO `source` VALUES ('25', '0.5', null, '2', '', '0.091', '', '1', '租房信息', '1', '1', '2', 'http://183.60.181.80/cas_home/index.php/Api-renthouse', '1', 'post');
INSERT INTO `source` VALUES ('26', '0.5', null, '2', '', '0.091', '', '1', '出租屋宣传栏', '1', '1', '2', 'http://183.60.181.80/cas_home/index.php/Api-noticeinfo', '1', 'post');
INSERT INTO `source` VALUES ('27', '0.5', null, '2', '', '0.077', '', '1', '治病防治', '1', '1', '2', 'http://183.60.181.80/cas_home/index.php/Api-illness', '1', 'post');
INSERT INTO `source` VALUES ('28', '0.5', null, '2', '', '0.179', '', '1', '优秀医疗专家', '1', '1', '2', 'http://183.60.181.80/cas_home/index.php/Api-technologist', '1', 'post');
INSERT INTO `source` VALUES ('29', '0.5', null, '2', '', '0.089', '', '1', '食品安全', '1', '1', '2', 'http://183.60.181.80/cas_home/index.php/Api-foodsafety', '1', 'post');
INSERT INTO `source` VALUES ('30', '0.5', null, '2', '', '0.136', '', '3', '卫生政策', '1', '1', '2', 'http://183.60.181.80/cas_home/index.php/Api-hnotice', '1', 'post');
INSERT INTO `timer` VALUES ('1', '1', '高级', '0 0/10 * * * ?');
INSERT INTO `timer` VALUES ('2', '2', '中级', '0 0/20 * * * ?');
INSERT INTO `timer` VALUES ('3', '3', '低频', '0 0 * * * ?');
INSERT INTO `user_info` VALUES ('1', '盖茨', '3da092d257308681b47c674fd31351db', '', 'admin');
INSERT INTO `user_info` VALUES ('2', '马云', '3da092d257308681b47c674fd31351db', '', 'root');
INSERT INTO `user_info` VALUES ('3', '李彦宏', '3da092d257308681b47c674fd31351db', '', 'user');
