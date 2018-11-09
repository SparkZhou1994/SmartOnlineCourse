/*
Navicat MySQL Data Transfer

Source Server         : mysql-localhost
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : smartonlinecourse

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-10-17 09:18:57
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of choose_course
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_ware
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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
  `attachment` varchar(500) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `submit_time` datetime DEFAULT NULL,
  `range` char(1) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`homework_id`),
  KEY `choose_course_id` (`choose_course_id`),
  CONSTRAINT `homework_ibfk_1` FOREIGN KEY (`choose_course_id`) REFERENCES `choose_course` (`choose_course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
