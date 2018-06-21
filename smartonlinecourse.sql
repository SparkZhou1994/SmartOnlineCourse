/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : smartonlinecourse

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-06-19 12:18:56
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of choose_course
-- ----------------------------
INSERT INTO `choose_course` VALUES ('1', '1', '1');
INSERT INTO `choose_course` VALUES ('2', '2', '1');
INSERT INTO `choose_course` VALUES ('3', '3', '1');
INSERT INTO `choose_course` VALUES ('4', '4', '1');
INSERT INTO `choose_course` VALUES ('5', '5', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '高数', '2', null, '/image/course/default.png');
INSERT INTO `course` VALUES ('2', '大物', '3', null, '/image/course/default.png');
INSERT INTO `course` VALUES ('3', 'Java', '4', null, '/image/course/default.png');
INSERT INTO `course` VALUES ('4', 'HTML', '5', null, '/image/course/default.png');
INSERT INTO `course` VALUES ('5', 'Python', '6', null, '/image/course/default.png');

-- ----------------------------
-- Table structure for `homework`
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `homework_id` int(11) NOT NULL AUTO_INCREMENT,
  `choose_course_id` int(11) DEFAULT NULL,
  `describe` varchar(50) DEFAULT NULL,
  `attachment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`homework_id`),
  KEY `choose_course_id` (`choose_course_id`),
  CONSTRAINT `homework_ibfk_1` FOREIGN KEY (`choose_course_id`) REFERENCES `choose_course` (`choose_course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('1', '1', null, null);
INSERT INTO `homework` VALUES ('2', '2', null, null);
INSERT INTO `homework` VALUES ('3', '3', null, null);
INSERT INTO `homework` VALUES ('4', '4', null, null);
INSERT INTO `homework` VALUES ('5', '5', null, null);

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
  `avatar` varchar(50) DEFAULT '/image/user/default.png',
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
