/*
Navicat MySQL Data Transfer

Source Server         : mysql-localhost
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : smartonlinecourse

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-10-17 12:16:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `choose_course`
-- ----------------------------
DROP TABLE IF EXISTS `choose_course`;
CREATE TABLE `choose_course` (
  `choose_course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `score` tinyint(255) DEFAULT NULL,
  PRIMARY KEY (`choose_course_id`),
  KEY `user_id` (`user_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `choose_course_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `choose_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of choose_course
-- ----------------------------
INSERT INTO `choose_course` VALUES ('1', '1', '3', null);
INSERT INTO `choose_course` VALUES ('2', '2', '3', null);
INSERT INTO `choose_course` VALUES ('3', '3', '3', null);
INSERT INTO `choose_course` VALUES ('4', '4', '4', null);
INSERT INTO `choose_course` VALUES ('5', '5', '4', null);
INSERT INTO `choose_course` VALUES ('6', '1', '1', '90');
INSERT INTO `choose_course` VALUES ('7', '2', '1', null);
INSERT INTO `choose_course` VALUES ('8', '3', '1', null);
INSERT INTO `choose_course` VALUES ('9', '4', '1', null);
INSERT INTO `choose_course` VALUES ('10', '5', '1', null);
INSERT INTO `choose_course` VALUES ('11', '4', '2', null);
INSERT INTO `choose_course` VALUES ('12', '5', '2', null);
INSERT INTO `choose_course` VALUES ('13', '1', '2', '80');
INSERT INTO `choose_course` VALUES ('14', '6', '4', null);
INSERT INTO `choose_course` VALUES ('15', '7', '4', null);
INSERT INTO `choose_course` VALUES ('16', '6', '1', null);
INSERT INTO `choose_course` VALUES ('17', '7', '1', null);
INSERT INTO `choose_course` VALUES ('18', '6', '2', null);
INSERT INTO `choose_course` VALUES ('19', '4', '5', null);
INSERT INTO `choose_course` VALUES ('20', '5', '5', null);

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(15) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `catalog` varchar(10) DEFAULT NULL,
  `avatar` varchar(500) DEFAULT '/image/course/default.png',
  `avg_score` tinyint(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'Java', '3', '信息', '/Java_920011f2-3354-4591-b974-719124a52888_prog_java_128px_1097203_easyicon.net.png', '85');
INSERT INTO `course` VALUES ('2', 'Python', '3', '信息', '/Python_b674149e-5dfa-48d3-9be3-4e1ef65c1625_development_python_128px_540419_easyicon.net.png', null);
INSERT INTO `course` VALUES ('3', 'Html5', '3', '信息', '/Html5_b4f5835b-fbe3-4ce7-98ed-f0c40145562e_html5_html_5_htm_html5-old-logo_128px_522185_easyicon.net.png', null);
INSERT INTO `course` VALUES ('4', '日语', '4', '外语', '/日语_11704dbd-0fcc-4d1f-8856-8783455e6a19_Japan_72px_585328_easyicon.net.png', null);
INSERT INTO `course` VALUES ('5', '英语', '4', '外语', '/英语_ea6b51a3-d252-42c2-b576-42cc147427a6_english_flag_kingdom_united_72px_1405_easyicon.net.png', null);
INSERT INTO `course` VALUES ('6', '德语', '4', '外语', '/德语_195d3c78-a4ec-4709-b9f8-ab0bfc01607a_Germany_72px_585301_easyicon.net.png', null);
INSERT INTO `course` VALUES ('7', '法语', '4', '外语', '/法语_e50f5426-8b6c-441c-b524-494eb9a85098_flag_france_french_72px_4445_easyicon.net.png', null);

-- ----------------------------
-- Table structure for `course_ware`
-- ----------------------------
DROP TABLE IF EXISTS `course_ware`;
CREATE TABLE `course_ware` (
  `course_ware_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `attachment` varchar(500) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_ware_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_ware_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_ware
-- ----------------------------
INSERT INTO `course_ware` VALUES ('1', '1', 'Maven Setting', 'Java_fd78efc7-c461-47d2-afda-b9d484fdb512_settings.xml', '1');

-- ----------------------------
-- Table structure for `discuss`
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss` (
  `discuss_id` int(11) NOT NULL AUTO_INCREMENT,
  `choose_course_id` int(11) DEFAULT NULL,
  `title` varchar(60) DEFAULT NULL,
  `describe` varchar(50) DEFAULT NULL,
  `last_publish_time` datetime DEFAULT NULL,
  `vote` char(1) DEFAULT NULL,
  `choose_1` varchar(60) DEFAULT NULL,
  `choose_2` varchar(60) DEFAULT NULL,
  `choose_3` varchar(60) DEFAULT NULL,
  `choose_4` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`discuss_id`),
  KEY `choose_course_id` (`choose_course_id`),
  CONSTRAINT `discuss_ibfk_1` FOREIGN KEY (`choose_course_id`) REFERENCES `choose_course` (`choose_course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss
-- ----------------------------
INSERT INTO `discuss` VALUES ('1', '1', '关于类型转换问题', '如何将String转为int', '2018-10-17 10:50:01', '0', null, null, null, null);
INSERT INTO `discuss` VALUES ('2', '1', '类型转换安全吗', '投票选择类型转换是否安全 ', '2018-10-17 10:49:18', '1', '安全', '不安全', '看情况', null);

-- ----------------------------
-- Table structure for `discuss_content`
-- ----------------------------
DROP TABLE IF EXISTS `discuss_content`;
CREATE TABLE `discuss_content` (
  `discuss_content_id` int(11) NOT NULL AUTO_INCREMENT,
  `discuss_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` tinytext,
  `publish_time` datetime DEFAULT NULL,
  `choose` char(1) DEFAULT NULL,
  PRIMARY KEY (`discuss_content_id`),
  KEY `discuss_id` (`discuss_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `discuss_content_ibfk_1` FOREIGN KEY (`discuss_id`) REFERENCES `discuss` (`discuss_id`),
  CONSTRAINT `discuss_content_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss_content
-- ----------------------------
INSERT INTO `discuss_content` VALUES ('1', '2', '2', null, '2018-10-17 10:46:42', '3');
INSERT INTO `discuss_content` VALUES ('2', '1', '2', '使用Integer包装类的.parseInt()方法', '2018-10-17 10:48:07', null);
INSERT INTO `discuss_content` VALUES ('3', '2', '1', null, '2018-10-17 10:49:18', '3');
INSERT INTO `discuss_content` VALUES ('4', '1', '1', '使用Integer包装类的.parseInt()方法，使用try catch捕获异常', '2018-10-17 10:50:00', null);

-- ----------------------------
-- Table structure for `homework`
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `homework_id` int(11) NOT NULL AUTO_INCREMENT,
  `choose_course_id` int(11) DEFAULT NULL,
  `describe` varchar(50) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `attachment` varchar(500) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `submit_time` datetime DEFAULT NULL,
  `range` char(1) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`homework_id`),
  KEY `choose_course_id` (`choose_course_id`),
  CONSTRAINT `homework_ibfk_1` FOREIGN KEY (`choose_course_id`) REFERENCES `choose_course` (`choose_course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('1', '6', '简单配置JDBC Mysql依赖', 'POM配置', 'Java_8a5a2f92-4390-4142-9f45-571bb79e0af9_pom.xml', '1', '2018-10-17 22:00:00', '2018-10-17 11:02:39', '1', '100');
INSERT INTO `homework` VALUES ('2', '13', '简单配置JDBC Mysql依赖', 'POM配置', 'Java_2470d6ef-5694-4d95-a052-4e8e6a1aa1ca_pom.xml', '1', '2018-10-17 22:00:00', '2018-10-17 11:05:10', '1', '80');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `choose_course_id` int(11) DEFAULT NULL,
  `content` tinytext,
  `publish_date` date DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `choose_course_id` (`choose_course_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`choose_course_id`) REFERENCES `choose_course` (`choose_course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `sign`
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `sign_id` int(11) NOT NULL AUTO_INCREMENT,
  `choose_course_id` int(11) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL,
  `code` char(6) DEFAULT NULL,
  `sign_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `range` char(1) DEFAULT NULL,
  PRIMARY KEY (`sign_id`),
  KEY `choose_course_id` (`choose_course_id`),
  CONSTRAINT `sign_ibfk_1` FOREIGN KEY (`choose_course_id`) REFERENCES `choose_course` (`choose_course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES ('1', '6', '1', '123456', '2018-10-17 10:30:09', '2018-10-17 10:31:47', '1');
INSERT INTO `sign` VALUES ('2', '13', '1', '123456', '2018-10-17 10:32:57', '2018-10-17 10:31:47', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `tel` char(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `credit` tinyint(4) DEFAULT NULL,
  `avatar` varchar(500) DEFAULT '/image/user/default.png',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idx_tel` (`tel`) USING BTREE,
  UNIQUE KEY `idx_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Spark', '$2a$10$TLvsadEJMFRQF/Qh9IL8DexO0vrRWGEMunPMunredIqQAOHfT4tEe', '13127945312', 'Spark@qq.com', null, '/Spark_6c1ab673-c4cc-4a12-9fd4-6e1038fe55d0_avatar_128px_1202943_easyicon.net.png');
INSERT INTO `user` VALUES ('2', 'Echo', '$2a$10$8.WDtVC.5K8gzozWjcaTNegqmIFEaZF2MqT5IVHqzt/ZAAh7zAqFa', null, 'Echo@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('3', 'Andy', '$2a$10$xnuG2zqgkjGnKerAl0stsuupvLDMYD0QofZNTzH/xvXU/o3xQ1MEG', null, 'Andy@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('4', 'Nacy', '$2a$10$E0umu5dFXGuISetDOSBLv.RacuS8OWyLwZ/1Okcgw25tWFtYEmcT.', '13585726717', 'Nacy@qq.com', null, '/Nacy_c1041515-99e8-4c17-8e39-152865831431_avatar_128px_1202944_easyicon.net.png');
INSERT INTO `user` VALUES ('5', 'Lily', '$2a$10$8iFZd6bSDU0f41Fxf9yfn.qqtSEFN9aBYknkafVvFgPWYrb1J2ija', null, 'Lily@qq.com', null, '/image/user/default.png');
