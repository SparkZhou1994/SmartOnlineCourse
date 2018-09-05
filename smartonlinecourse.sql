/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : smartonlinecourse

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-09-05 21:44:13
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
  PRIMARY KEY (`choose_course_id`),
  KEY `user_id` (`user_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `choose_course_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `choose_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of choose_course
-- ----------------------------
INSERT INTO `choose_course` VALUES ('1', '1', '1');
INSERT INTO `choose_course` VALUES ('2', '1', '2');
INSERT INTO `choose_course` VALUES ('3', '3', '1');
INSERT INTO `choose_course` VALUES ('4', '4', '1');
INSERT INTO `choose_course` VALUES ('5', '5', '1');
INSERT INTO `choose_course` VALUES ('6', '1', '3');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(15) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `catalog` varchar(10) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT '/image/course/default.png',
  PRIMARY KEY (`course_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '高数', '2', '基础', '/image/course/default.png');
INSERT INTO `course` VALUES ('2', '大物', '3', '基础', '/image/course/default.png');
INSERT INTO `course` VALUES ('3', 'Java', '4', '信息', '/image/course/default.png');
INSERT INTO `course` VALUES ('4', 'HTML', '5', '信息', '/image/course/default.png');
INSERT INTO `course` VALUES ('5', 'Python', '6', '信息', '/image/course/default.png');

-- ----------------------------
-- Table structure for `course_ware`
-- ----------------------------
DROP TABLE IF EXISTS `course_ware`;
CREATE TABLE `course_ware` (
  `course_ware_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `attachment` varchar(100) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_ware_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_ware_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_ware
-- ----------------------------

-- ----------------------------
-- Table structure for `discuss`
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss` (
  `discuss_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `title` varchar(60) DEFAULT NULL,
  `last_publish_time` datetime DEFAULT NULL,
  `vote` char(1) DEFAULT NULL,
  `choose_1` varchar(60) DEFAULT NULL,
  `choose_2` varchar(60) DEFAULT NULL,
  `choose_3` varchar(60) DEFAULT NULL,
  `choose_4` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`discuss_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `discuss_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss
-- ----------------------------
INSERT INTO `discuss` VALUES ('1', '1', 'Test', '2018-08-03 17:28:14', null, null, null, null, null);

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
  `choose` char(2) DEFAULT NULL,
  PRIMARY KEY (`discuss_content_id`),
  KEY `discuss_id` (`discuss_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `discuss_content_ibfk_1` FOREIGN KEY (`discuss_id`) REFERENCES `discuss` (`discuss_id`),
  CONSTRAINT `discuss_content_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss_content
-- ----------------------------

-- ----------------------------
-- Table structure for `homework`
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `homework_id` int(11) NOT NULL AUTO_INCREMENT,
  `choose_course_id` int(11) DEFAULT NULL,
  `describe` varchar(50) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `attachment` varchar(100) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `submit_time` datetime DEFAULT NULL,
  `range` char(1) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`homework_id`),
  KEY `choose_course_id` (`choose_course_id`),
  CONSTRAINT `homework_ibfk_1` FOREIGN KEY (`choose_course_id`) REFERENCES `choose_course` (`choose_course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('42', '1', 'Test File', 'Test', '高数_fe2617a1-e7cc-42c6-b174-c8d13f52170b_开题报告.txt', '1', '2018-09-30 17:05:00', null, null, null);
INSERT INTO `homework` VALUES ('43', '2', 'Test File', 'Test', '高数_2919becc-27f2-48a9-a154-b178db8148a1_周炬辉-1321386-开题报告.doc', '1', '2018-09-30 17:05:00', '2018-09-04 17:10:20', null, null);
INSERT INTO `homework` VALUES ('44', '6', 'Test File', 'Test', '高数_fe2617a1-e7cc-42c6-b174-c8d13f52170b_开题报告.txt', '1', '2018-09-30 17:05:00', null, null, null);

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `choose_course_id` int(11) DEFAULT NULL,
  `context` tinytext,
  `publish_date` date DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `choose_course_id` (`choose_course_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`choose_course_id`) REFERENCES `choose_course` (`choose_course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1', 'Test', '2018-08-03');

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `score_id` int(11) NOT NULL AUTO_INCREMENT,
  `choose_course_id` int(11) DEFAULT NULL,
  `score` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`score_id`),
  KEY `choose_course_id` (`choose_course_id`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`choose_course_id`) REFERENCES `choose_course` (`choose_course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('1', '1', '100');
INSERT INTO `score` VALUES ('2', '2', '90');
INSERT INTO `score` VALUES ('3', '3', '80');
INSERT INTO `score` VALUES ('4', '4', '70');
INSERT INTO `score` VALUES ('5', '5', '60');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES ('1', '1', '1', '1234', '2018-08-23 21:27:13', '2018-08-03 17:30:05', '0');
INSERT INTO `sign` VALUES ('2', '6', '1', '1234', '2018-08-03 17:35:58', '2018-08-03 17:30:05', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `tel` char(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `credit` tinyint(4) DEFAULT NULL,
  `avatar` varchar(500) DEFAULT '/image/user/default.png',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idx_tel` (`tel`) USING BTREE,
  UNIQUE KEY `idx_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Spark', '123', '13127945312', '2637666515@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('2', 'Echo', '123', '13127945311', '2637666511@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('3', 'Grace', '123', '13127945313', '2637666512@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('4', 'Jack', '123', '13127945314', '2637666513@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('5', 'Andy', '123', '13127945315', '2637666514@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('6', '周炬辉', '123', '13127945316', '2637666516@qq.com', null, '/image/user/default.png');
