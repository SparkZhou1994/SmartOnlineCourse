/*
Navicat MySQL Data Transfer

Source Server         : mysql-localhost
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : smartonlinecourse

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-10-08 15:30:16
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of choose_course
-- ----------------------------
INSERT INTO `choose_course` VALUES ('1', '1', '1', '12');
INSERT INTO `choose_course` VALUES ('2', '1', '2', null);
INSERT INTO `choose_course` VALUES ('3', '3', '1', null);
INSERT INTO `choose_course` VALUES ('4', '4', '1', null);
INSERT INTO `choose_course` VALUES ('5', '5', '1', null);
INSERT INTO `choose_course` VALUES ('6', '1', '3', null);

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
  `avg_score` tinyint(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '高数', '2', '基础', '/image/course/default.png', '12');
INSERT INTO `course` VALUES ('2', '大物', '3', '基础', '/image/course/default.png', null);
INSERT INTO `course` VALUES ('3', 'Java', '4', '信息', '/image/course/default.png', null);
INSERT INTO `course` VALUES ('4', 'HTML', '5', '信息', '/image/course/default.png', null);
INSERT INTO `course` VALUES ('5', 'Python', '6', '信息', '/image/course/default.png', null);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss
-- ----------------------------
INSERT INTO `discuss` VALUES ('25', '2', '关于三重积分的讨论', '课程第8章', '2018-09-13 16:50:42', '0', null, null, null, null);
INSERT INTO `discuss` VALUES ('26', '2', '关于购买辅导教材的投票', '《高数解题集》', '2018-09-13 16:51:12', '1', '购买', '不购买', null, null);
INSERT INTO `discuss` VALUES ('27', '1', '关于高数考试范围', '范围在哪里', '2018-09-13 16:51:50', '0', null, null, null, null);
INSERT INTO `discuss` VALUES ('28', '1', '关于考试范围增加的投票', '考试范围增加，难度减小', '2018-09-13 16:52:48', '1', '增加', '保持', '减小', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss_content
-- ----------------------------
INSERT INTO `discuss_content` VALUES ('2', '25', '1', '有空间感', '2018-09-13 15:53:42', null);
INSERT INTO `discuss_content` VALUES ('3', '25', '1', '对空间感要求较高', '2018-09-13 16:50:42', null);
INSERT INTO `discuss_content` VALUES ('4', '26', '1', null, '2018-09-13 16:51:12', '1');
INSERT INTO `discuss_content` VALUES ('5', '28', '1', null, '2018-09-13 16:52:48', '3');

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
  `content` tinytext,
  `publish_date` date DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `choose_course_id` (`choose_course_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`choose_course_id`) REFERENCES `choose_course` (`choose_course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1', '您有1个作业未提交', '2018-09-24');
INSERT INTO `message` VALUES ('2', '6', '您有1个作业未提交', '2018-09-24');

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
INSERT INTO `sign` VALUES ('1', '1', '1', '1234', '2018-08-23 21:27:13', '2018-08-03 17:30:05', '0');
INSERT INTO `sign` VALUES ('2', '6', '1', '1234', '2018-08-03 17:35:58', '2018-08-03 17:30:05', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Spark', '$2a$10$xrbcEYBy/j3jGXtTrRMn3esX.2iWjR0Mqu50dqo5Vvamcah80NjFi', '13127945312', '2637666515@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('2', 'Echo', '$2a$10$xrbcEYBy/j3jGXtTrRMn3esX.2iWjR0Mqu50dqo5Vvamcah80NjFi', '13127945311', '2637666511@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('3', 'Grace', '$2a$10$xrbcEYBy/j3jGXtTrRMn3esX.2iWjR0Mqu50dqo5Vvamcah80NjFi', '13127945313', '2637666512@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('4', 'Jack', '$2a$10$xrbcEYBy/j3jGXtTrRMn3esX.2iWjR0Mqu50dqo5Vvamcah80NjFi', '13127945314', '2637666513@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('5', 'Andy', '$2a$10$xrbcEYBy/j3jGXtTrRMn3esX.2iWjR0Mqu50dqo5Vvamcah80NjFi', '13127945315', '2637666514@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('6', '周炬辉', '$2a$10$xrbcEYBy/j3jGXtTrRMn3esX.2iWjR0Mqu50dqo5Vvamcah80NjFi', '13127945316', '2637666516@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('13', 'SparkZhou', '$2a$10$G2a1jiMbs3eNz8b6Y.Tx1e/fuJjI4S6GDWTEbbPAi8ThGck5DdFiW', null, '2637666519@qq.com', null, '/image/user/default.png');
INSERT INTO `user` VALUES ('14', 'ABV', '$2a$10$/4yzr9oqamz1m7Ng.GjBReFLtYNUe7NJeD5LbzNJHuUUtWmKjUVXi', null, '2637666520@qq.com', null, '/image/user/default.png');
