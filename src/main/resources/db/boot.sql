/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : boot

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 18/07/2019 14:49:50
*/


CREATE DATABASE boot;

USE boot;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for bt_users
-- ----------------------------
DROP TABLE IF EXISTS `bt_users`;
CREATE TABLE `bt_users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '0',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bt_users
-- ----------------------------
INSERT INTO `bt_users` VALUES (1, '张三', 20, '1', '2019-07-17 19:37:31');
INSERT INTO `bt_users` VALUES (2, '李四', 22, '0', '2019-07-17 19:37:40');

SET FOREIGN_KEY_CHECKS = 1;
