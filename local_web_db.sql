/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80100
 Source Host           : localhost:3306
 Source Schema         : local_web_db

 Target Server Type    : MySQL
 Target Server Version : 80100
 File Encoding         : 65001

 Date: 11/09/2023 16:42:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `GUID` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '唯一标识',
  `file_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_size` double NULL DEFAULT NULL COMMENT '文件大小',
  `owner` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '所属人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', '1', '厚大法考', 'word', 1000, '4');
INSERT INTO `file` VALUES ('2', '2', '鸡你太美', 'word', 1000, '3');
INSERT INTO `file` VALUES ('3', '3', '法外狂徒', 'word', 1024, '5');
INSERT INTO `file` VALUES ('4', '4', '李四有罪', 'word', 2048, '6');
INSERT INTO `file` VALUES ('5', '5', '水浒传', 'word', 512, '2');

-- ----------------------------
-- Table structure for file_user_role
-- ----------------------------
DROP TABLE IF EXISTS `file_user_role`;
CREATE TABLE `file_user_role`  (
  `file_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '文件',
  `user_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '用户',
  `role_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '权限',
  INDEX `1`(`file_id`) USING BTREE,
  INDEX `2`(`user_id`) USING BTREE,
  INDEX `3`(`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_user_role
-- ----------------------------
INSERT INTO `file_user_role` VALUES ('1', '1', '1');
INSERT INTO `file_user_role` VALUES ('5', '1', '3');
INSERT INTO `file_user_role` VALUES ('2', '1', '2');

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `gid` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '上级分组id',
  `group_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '分组名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('0', NULL, '根目录');
INSERT INTO `group` VALUES ('1', '0', '杭州电子科技大学');
INSERT INTO `group` VALUES ('2', '0', '中国政法大学');
INSERT INTO `group` VALUES ('11', '1', '网络空间安全学院');
INSERT INTO `group` VALUES ('12', '1', '计算机学院');
INSERT INTO `group` VALUES ('21', '2', '刑事司法学院');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '只读');
INSERT INTO `role` VALUES ('2', '只写');
INSERT INTO `role` VALUES ('3', '读写');
INSERT INTO `role` VALUES ('0', '无任何权限');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '所属组织id',
  `login_name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '登录名称',
  `user_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `user_pass` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户密码',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `del_flag` int(0) NOT NULL COMMENT '是否删除，0否1是',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '创建人',
  `create_time` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `if_manage` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '是否为管理员',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `1`(`group_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '11', 'hhf', '何海峰', '123456', '18668085289', '2186263258@qq.com', 0, '1', '2023-02-01 13:24:17', '1', '2023-02-01 13:24:17', '1');
INSERT INTO `user` VALUES ('2', '11', 'lxy', '李欣芸', '123456', NULL, NULL, 0, '1', '2023-02-01 13:24:17', '1', '2023-02-01 13:24:17', '1');
INSERT INTO `user` VALUES ('3', '12', 'xhz', '小黑子', '123456', '110', NULL, 0, '1', '2023-02-01 13:24:17', '1', '2023-02-01 13:24:17', '1');
INSERT INTO `user` VALUES ('4', '21', 'lx', '罗翔', '123456', '110', NULL, 0, '1', '2023-02-01 13:24:17', '1', '2023-02-01 13:24:17', '1');
INSERT INTO `user` VALUES ('5', '2', 'zs', '张三', '123456', '110', NULL, 0, '1', '2023-02-01 13:24:17', '1', '2023-02-01 13:24:17', '1');
INSERT INTO `user` VALUES ('6', '2', 'ls', '李四', '123456', '120', NULL, 0, '1', '2023-02-01 13:24:17', '1', '2023-02-01 13:24:17', '1');

SET FOREIGN_KEY_CHECKS = 1;
