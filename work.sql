/*
 Navicat Premium Data Transfer

 Source Server         : mysql80
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : work

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 07/06/2022 14:40:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` int NOT NULL COMMENT '管理员id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员姓名',
  `gender` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `aid_fix` FOREIGN KEY (`id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES (1001, '雨落22', '男', 222);
INSERT INTO `tb_admin` VALUES (1002, '张佳', '男', 20);
INSERT INTO `tb_admin` VALUES (1003, '周孜', '女', 20);

-- ----------------------------
-- Table structure for tb_cate
-- ----------------------------
DROP TABLE IF EXISTS `tb_cate`;
CREATE TABLE `tb_cate`  (
  `cate_id` int NOT NULL,
  `cate_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cate_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_cate
-- ----------------------------
INSERT INTO `tb_cate` VALUES (0, '用户');
INSERT INTO `tb_cate` VALUES (1, '管理员');
INSERT INTO `tb_cate` VALUES (2, '教师');
INSERT INTO `tb_cate` VALUES (3, '学生');
INSERT INTO `tb_cate` VALUES (4, '课程');
INSERT INTO `tb_cate` VALUES (5, '班级');
INSERT INTO `tb_cate` VALUES (6, '学院');

-- ----------------------------
-- Table structure for tb_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_class`;
CREATE TABLE `tb_class`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `grade` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name_cite`(`grade`) USING BTREE,
  CONSTRAINT `name_cite` FOREIGN KEY (`grade`) REFERENCES `tb_student` (`grade`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_class
-- ----------------------------
INSERT INTO `tb_class` VALUES (5003, '2011');

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名称',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程类型（必修或选修）',
  `teacher_id` int NOT NULL COMMENT '教师编号',
  `start_time` date NOT NULL COMMENT '开课时间',
  `end_time` date NOT NULL COMMENT '结课时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `teacher_id_1`(`teacher_id`) USING BTREE,
  CONSTRAINT `teacher_id_1` FOREIGN KEY (`teacher_id`) REFERENCES `tb_teacher` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_course
-- ----------------------------
INSERT INTO `tb_course` VALUES (4002, '教育技术学', '选修', 2002, '2022-05-10', '2022-05-28');
INSERT INTO `tb_course` VALUES (4005, '教育心理学', '选修', 2005, '2022-05-19', '2022-05-26');
INSERT INTO `tb_course` VALUES (4006, '中国教育史', '选修', 2002, '2022-05-11', '2022-05-31');
INSERT INTO `tb_course` VALUES (4007, '教育研究方法', '选修', 2005, '2022-05-10', '2022-05-31');
INSERT INTO `tb_course` VALUES (4009, '美国教育史', '选修', 2002, '2022-06-02', '2022-06-30');
INSERT INTO `tb_course` VALUES (4555, '人工智能技术', '必修', 2001, '2022-06-07', '2022-06-18');
INSERT INTO `tb_course` VALUES (4777, '四大文明古国', '必修', 2002, '2022-06-07', '2022-06-08');
INSERT INTO `tb_course` VALUES (4899, '计算机科学技术', '必修', 2002, '2022-06-07', '2022-06-29');
INSERT INTO `tb_course` VALUES (4999, '世界文明史', '必修', 2002, '2022-06-07', '2025-07-24');

-- ----------------------------
-- Table structure for tb_school
-- ----------------------------
DROP TABLE IF EXISTS `tb_school`;
CREATE TABLE `tb_school`  (
  `id` int NOT NULL,
  `school_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_school
-- ----------------------------
INSERT INTO `tb_school` VALUES (6001, '计算机学院');
INSERT INTO `tb_school` VALUES (6002, '教育学院');
INSERT INTO `tb_school` VALUES (6003, '马克思学院');
INSERT INTO `tb_school` VALUES (6004, '人工智能教学部');
INSERT INTO `tb_school` VALUES (6005, '经管学院');
INSERT INTO `tb_school` VALUES (6006, '体育学院');
INSERT INTO `tb_school` VALUES (6007, '美术学院');

-- ----------------------------
-- Table structure for tb_sele_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_sele_course`;
CREATE TABLE `tb_sele_course`  (
  `course_id` int NOT NULL,
  `student_id` int NOT NULL,
  `score` int NULL DEFAULT NULL,
  INDEX `course_id_1`(`course_id`) USING BTREE,
  INDEX `student_id_1`(`student_id`) USING BTREE,
  CONSTRAINT `course_id_1` FOREIGN KEY (`course_id`) REFERENCES `tb_course` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `student_id_1` FOREIGN KEY (`student_id`) REFERENCES `tb_student` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sele_course
-- ----------------------------
INSERT INTO `tb_sele_course` VALUES (4002, 3002, 88);
INSERT INTO `tb_sele_course` VALUES (4005, 3001, NULL);
INSERT INTO `tb_sele_course` VALUES (4007, 3001, 99);
INSERT INTO `tb_sele_course` VALUES (4007, 3002, 87);
INSERT INTO `tb_sele_course` VALUES (4009, 3001, NULL);
INSERT INTO `tb_sele_course` VALUES (4002, 3003, 99);
INSERT INTO `tb_sele_course` VALUES (4005, 3003, NULL);
INSERT INTO `tb_sele_course` VALUES (4006, 3003, NULL);
INSERT INTO `tb_sele_course` VALUES (4007, 3003, NULL);
INSERT INTO `tb_sele_course` VALUES (4009, 3003, NULL);
INSERT INTO `tb_sele_course` VALUES (4002, 3006, 99);
INSERT INTO `tb_sele_course` VALUES (4006, 3006, NULL);
INSERT INTO `tb_sele_course` VALUES (4009, 3006, NULL);
INSERT INTO `tb_sele_course` VALUES (4005, 3088, NULL);
INSERT INTO `tb_sele_course` VALUES (4006, 3088, NULL);
INSERT INTO `tb_sele_course` VALUES (4007, 3088, NULL);
INSERT INTO `tb_sele_course` VALUES (4005, 3023, NULL);
INSERT INTO `tb_sele_course` VALUES (4007, 3023, NULL);
INSERT INTO `tb_sele_course` VALUES (4009, 3023, NULL);
INSERT INTO `tb_sele_course` VALUES (4009, 3004, NULL);
INSERT INTO `tb_sele_course` VALUES (4999, 3001, NULL);
INSERT INTO `tb_sele_course` VALUES (4999, 3002, NULL);
INSERT INTO `tb_sele_course` VALUES (4999, 3003, NULL);
INSERT INTO `tb_sele_course` VALUES (4999, 3004, NULL);
INSERT INTO `tb_sele_course` VALUES (4999, 3005, NULL);
INSERT INTO `tb_sele_course` VALUES (4999, 3006, NULL);
INSERT INTO `tb_sele_course` VALUES (4999, 3023, NULL);
INSERT INTO `tb_sele_course` VALUES (4999, 3088, NULL);
INSERT INTO `tb_sele_course` VALUES (4899, 3001, 55);
INSERT INTO `tb_sele_course` VALUES (4899, 3002, 88);
INSERT INTO `tb_sele_course` VALUES (4899, 3003, NULL);
INSERT INTO `tb_sele_course` VALUES (4899, 3004, NULL);
INSERT INTO `tb_sele_course` VALUES (4899, 3005, NULL);
INSERT INTO `tb_sele_course` VALUES (4899, 3006, NULL);
INSERT INTO `tb_sele_course` VALUES (4899, 3023, NULL);
INSERT INTO `tb_sele_course` VALUES (4899, 3088, NULL);
INSERT INTO `tb_sele_course` VALUES (4777, 3001, NULL);
INSERT INTO `tb_sele_course` VALUES (4777, 3002, NULL);
INSERT INTO `tb_sele_course` VALUES (4777, 3003, NULL);
INSERT INTO `tb_sele_course` VALUES (4777, 3004, NULL);
INSERT INTO `tb_sele_course` VALUES (4777, 3005, NULL);
INSERT INTO `tb_sele_course` VALUES (4777, 3006, NULL);
INSERT INTO `tb_sele_course` VALUES (4777, 3023, NULL);
INSERT INTO `tb_sele_course` VALUES (4777, 3088, NULL);
INSERT INTO `tb_sele_course` VALUES (4555, 3001, NULL);
INSERT INTO `tb_sele_course` VALUES (4555, 3002, 66);
INSERT INTO `tb_sele_course` VALUES (4555, 3003, NULL);
INSERT INTO `tb_sele_course` VALUES (4555, 3004, NULL);
INSERT INTO `tb_sele_course` VALUES (4555, 3005, NULL);
INSERT INTO `tb_sele_course` VALUES (4555, 3006, NULL);
INSERT INTO `tb_sele_course` VALUES (4555, 3023, NULL);
INSERT INTO `tb_sele_course` VALUES (4555, 3088, NULL);
INSERT INTO `tb_sele_course` VALUES (4002, 3001, NULL);
INSERT INTO `tb_sele_course` VALUES (4006, 3001, NULL);

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `id` int NOT NULL COMMENT '学号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专业',
  `grade` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '年级',
  `gender` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `grade`(`grade`) USING BTREE,
  CONSTRAINT `sid_fix` FOREIGN KEY (`id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (3001, '张三', 20, '教育技术学', '1911', '男', '湖北省襄阳市襄城区');
INSERT INTO `tb_student` VALUES (3002, '李四', 20, '物联网', '2011', '男', '湖北省襄阳市樊城区');
INSERT INTO `tb_student` VALUES (3003, '测试1', 22, '教育技术学', '1912', '男', '地球');
INSERT INTO `tb_student` VALUES (3004, '测试2', 20, '计算机科学与技术', '1922', '男', '地球');
INSERT INTO `tb_student` VALUES (3005, '测试44', 20, '物流管理', '1944', '男', '地球');
INSERT INTO `tb_student` VALUES (3006, '测试443', 16, '汉语言文学', '1923', '女', '地球');
INSERT INTO `tb_student` VALUES (3023, '测试222', 20, '可持续能源', '1822', '男', '地球');
INSERT INTO `tb_student` VALUES (3088, '测试', 20, '科学史', '1722', '男', '地球');

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher`  (
  `id` int NOT NULL COMMENT '教师编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师姓名',
  `age` int NOT NULL COMMENT '教师年龄',
  `gender` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师性别',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师职称',
  `school_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tb_school_fix`(`school_id`) USING BTREE,
  CONSTRAINT `tb_school_fix` FOREIGN KEY (`school_id`) REFERENCES `tb_school` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `tid_fix` FOREIGN KEY (`id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES (2001, '李华', 43, '男', '教授', 6001);
INSERT INTO `tb_teacher` VALUES (2002, '小强', 30, '男', '副教授', 6002);
INSERT INTO `tb_teacher` VALUES (2003, '小田', 28, '男', '讲师', 6004);
INSERT INTO `tb_teacher` VALUES (2004, '小昭', 25, '女', '副教授', 6005);
INSERT INTO `tb_teacher` VALUES (2005, '小迪', 22, '男', '教授', 6002);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `identity` int NOT NULL,
  `token` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1001, '123123', 1, 'f9ea21123b4347c999a6cdd39bc1443b');
INSERT INTO `tb_user` VALUES (1002, '1234567', 1, NULL);
INSERT INTO `tb_user` VALUES (1003, '1234', 1, '7bf1a34fc8d14c2fa472cc7ec7c07579');
INSERT INTO `tb_user` VALUES (2001, '1234', 2, '14445fe60ee84da9ba2327d3ac27cb74');
INSERT INTO `tb_user` VALUES (2002, '1234', 2, 'f528620c5f34496cb313f01e2dbf6483');
INSERT INTO `tb_user` VALUES (2003, '123123', 2, NULL);
INSERT INTO `tb_user` VALUES (2004, '123111', 2, NULL);
INSERT INTO `tb_user` VALUES (2005, '123123', 2, NULL);
INSERT INTO `tb_user` VALUES (3001, '111111', 3, 'b51ae5d52edb40efb8407710ea2f8448');
INSERT INTO `tb_user` VALUES (3002, '1234', 3, NULL);
INSERT INTO `tb_user` VALUES (3003, '123123', 3, '2ce951fc71eb4bb6af06ea00a30f2902');
INSERT INTO `tb_user` VALUES (3004, '1234', 3, NULL);
INSERT INTO `tb_user` VALUES (3005, '1234', 3, NULL);
INSERT INTO `tb_user` VALUES (3006, '123123', 3, '8651b9d14e81498d93888cd455e1d3d5');
INSERT INTO `tb_user` VALUES (3023, '123123', 3, 'bfaa1590ee0d432cb9b7001fd17df2fa');
INSERT INTO `tb_user` VALUES (3088, '123123', 3, '2127db56a2ed4168a912cd73b9bbcefc');

SET FOREIGN_KEY_CHECKS = 1;
