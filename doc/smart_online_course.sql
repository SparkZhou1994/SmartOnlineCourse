/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : 127.0.0.1:3306
 Source Schema         : smart_online_course

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 28/01/2019 22:02:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_choose_course
-- ----------------------------
DROP TABLE IF EXISTS `t_choose_course`;
CREATE TABLE `t_choose_course`  (
  `version` bigint(255) NOT NULL,
  `choose_course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `score` tinyint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`choose_course_id`) USING BTREE,
  INDEX `fk_user_id_table_choose_course`(`user_id`) USING BTREE,
  INDEX `fk_course_id_table_choose_course`(`course_id`) USING BTREE,
  CONSTRAINT `fk_user_id_table_choose_course` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_course_id_table_choose_course` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `version` bigint(255) NOT NULL,
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL,
  `catalog` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '/course/default.png',
  `avg_score` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `fk_user_id_table_course`(`user_id`) USING BTREE,
  CONSTRAINT `fk_user_id_table_course` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_course_ware
-- ----------------------------
DROP TABLE IF EXISTS `t_course_ware`;
CREATE TABLE `t_course_ware`  (
  `version` bigint(255) NOT NULL,
  `course_ware_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `attachment` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `batch` tinyint(4) NOT NULL,
  PRIMARY KEY (`course_ware_id`) USING BTREE,
  INDEX `fk_course_id_table_course_ware`(`course_id`) USING BTREE,
  CONSTRAINT `fk_course_id_table_course_ware` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_discuss
-- ----------------------------
DROP TABLE IF EXISTS `t_discuss`;
CREATE TABLE `t_discuss`  (
  `version` bigint(255) NOT NULL,
  `discuss_id` int(11) NOT NULL AUTO_INCREMENT,
  `choose_course_id` int(11) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `last_publish_time` datetime NULL,
  `vote` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `choose_1` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `choose_2` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `choose_3` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `choose_4` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`discuss_id`) USING BTREE,
  INDEX `fk_choose_course_id_table_discuss`(`choose_course_id`) USING BTREE,
  CONSTRAINT `fk_choose_course_id_table_discuss` FOREIGN KEY (`choose_course_id`) REFERENCES `t_choose_course` (`choose_course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_discuss_content
-- ----------------------------
DROP TABLE IF EXISTS `t_discuss_content`;
CREATE TABLE `t_discuss_content`  (
  `version` bigint(20) NOT NULL,
  `discuss_content_id` int(11) NOT NULL AUTO_INCREMENT,
  `discuss_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` tinytext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `publish_time` datetime NULL,
  `choose` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`discuss_content_id`) USING BTREE,
  INDEX `fk_discuss_id_table_discuss_content`(`discuss_id`) USING BTREE,
  INDEX `fk_user_id_table_discuss_content`(`user_id`) USING BTREE,
  CONSTRAINT `fk_discuss_id_table_discuss_content` FOREIGN KEY (`discuss_id`) REFERENCES `t_discuss` (`discuss_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_id_table_discuss_content` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_homework
-- ----------------------------
DROP TABLE IF EXISTS `t_homework`;
CREATE TABLE `t_homework`  (
  `version` bigint(255) NOT NULL,
  `homework_id` int(11) NOT NULL AUTO_INCREMENT,
  `choose_course_id` int(11) NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `attachment` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `batch` tinyint(255) NOT NULL,
  `end_time` datetime NULL,
  `submit_time` datetime NULL DEFAULT NULL,
  `range` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `score` tinyint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`homework_id`) USING BTREE,
  INDEX `fk_choose_course_id_table_homework`(`choose_course_id`) USING BTREE,
  CONSTRAINT `fk_choose_course_id_table_homework` FOREIGN KEY (`choose_course_id`) REFERENCES `t_choose_course` (`choose_course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `version` bigint(255) NOT NULL,
  `message_id` int(11) NOT NULL,
  `choose_course_id` int(11) NOT NULL,
  `content` tinytext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `publish_data` date NOT NULL,
  PRIMARY KEY (`message_id`) USING BTREE,
  INDEX `fk_choose_course_id_table_message`(`choose_course_id`) USING BTREE,
  CONSTRAINT `fk_choose_course_id_table_message` FOREIGN KEY (`choose_course_id`) REFERENCES `t_choose_course` (`choose_course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_sign
-- ----------------------------
DROP TABLE IF EXISTS `t_sign`;
CREATE TABLE `t_sign`  (
  `version` bigint(255) NOT NULL,
  `sign_id` int(11) NOT NULL,
  `choose_course_id` int(11) NOT NULL,
  `batch` tinyint(255) NOT NULL,
  `code` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `end_time` datetime NULL,
  `sign_time` datetime NULL DEFAULT NULL,
  `range` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sign_id`) USING BTREE,
  INDEX `fk_choose_course_id`(`choose_course_id`) USING BTREE,
  CONSTRAINT `fk_choose_course_id` FOREIGN KEY (`choose_course_id`) REFERENCES `t_choose_course` (`choose_course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `version` bigint(255) NOT NULL DEFAULT 0,
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `age` tinyint(4) NOT NULL DEFAULT 0,
  `telphone` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '/user/default.png',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uni_username`(`username`) USING BTREE,
  UNIQUE INDEX `uni_telphone`(`telphone`) USING BTREE,
  UNIQUE INDEX `uni_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_user_password
-- ----------------------------
DROP TABLE IF EXISTS `t_user_password`;
CREATE TABLE `t_user_password`  (
  `version` bigint(255) NOT NULL DEFAULT 0,
  `user_id` int(11) NOT NULL,
  `encrypt_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `fk_user_id_table_password`(`user_id`) USING BTREE,
  CONSTRAINT `fk_user_id_table_password` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
