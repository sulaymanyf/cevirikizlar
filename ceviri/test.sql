/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.16 : Database - cevirikizlar
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cevirikizlar` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `cevirikizlar`;

/*Table structure for table `tb_ceviri` */

DROP TABLE IF EXISTS `tb_ceviri`;

CREATE TABLE `tb_ceviri` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `metin_id` varchar(20) DEFAULT NULL COMMENT '原文id',
  `title` varchar(100) DEFAULT NULL COMMENT '译文标题',
  `content` text COMMENT '译文正文',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期',
  `status` tinyint(2) DEFAULT NULL COMMENT '审核状态',
  `url` varchar(100) DEFAULT NULL COMMENT 'URL',
  `delete_flag` tinyint(1) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL COMMENT '语言',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='原文';

/*Table structure for table `tb_file` */

DROP TABLE IF EXISTS `tb_file`;

CREATE TABLE `tb_file` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `file_name` varchar(120) DEFAULT NULL COMMENT 'tag名称',
  `state` tinyint(2) DEFAULT NULL COMMENT 'tag颜色',
  `url` varchar(200) DEFAULT NULL COMMENT 'tag颜色',
  `path` varchar(200) DEFAULT NULL COMMENT 'tag颜色',
  `suffix` varchar(20) DEFAULT NULL COMMENT 'tag颜色',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期',
  `delete_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文件';

/*Table structure for table `tb_metin` */

DROP TABLE IF EXISTS `tb_metin`;

CREATE TABLE `tb_metin` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '文章正文',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期',
  `status` tinyint(2) DEFAULT NULL COMMENT '审核状态',
  `url` varchar(100) DEFAULT NULL COMMENT 'URL',
  `metin_type` varchar(64) DEFAULT NULL COMMENT '文章类型',
  `delete_flag` tinyint(1) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL COMMENT '原文语言',
  `to_language` varchar(200) DEFAULT NULL COMMENT '要翻译的语言',
  `tag_id` varchar(20) DEFAULT NULL COMMENT '标签id',
  `mark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='原文';

/*Table structure for table `tb_metin_type` */

DROP TABLE IF EXISTS `tb_metin_type`;

CREATE TABLE `tb_metin_type` (
  `id` varchar(20) NOT NULL COMMENT '类型编号',
  `type_name` varchar(40) DEFAULT NULL COMMENT '问题ID',
  `type_info` varchar(120) DEFAULT NULL COMMENT '说明',
  `pid` varchar(20) NOT NULL COMMENT '父id',
  `create_time` datetime DEFAULT NULL COMMENT '修改日期',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期',
  `delete_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='回答';

/*Table structure for table `tb_permission` */

DROP TABLE IF EXISTS `tb_permission`;

CREATE TABLE `tb_permission` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `description` text COMMENT '权限描述',
  `per_name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '权限类型 1为菜单 2为功能 3为API',
  `pid` varchar(64) DEFAULT '0' COMMENT '主键',
  `level` varchar(2) DEFAULT NULL COMMENT '权限等级，1为通用接口权限，2为需校验接口权限',
  `method` varchar(255) DEFAULT NULL COMMENT '请求类型',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `icon` varchar(20) DEFAULT NULL COMMENT '权限代码',
  `sort` int(20) DEFAULT NULL COMMENT '排序',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `code` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` varchar(20) NOT NULL COMMENT '主键ID',
  `role_name` varchar(40) DEFAULT NULL COMMENT '权限名称',
  `description` varchar(255) DEFAULT NULL COMMENT '说明',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(2) DEFAULT NULL,
  `name_zh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `tb_role_permission` */

DROP TABLE IF EXISTS `tb_role_permission`;

CREATE TABLE `tb_role_permission` (
  `id` varchar(20) NOT NULL,
  `role_id` varchar(20) DEFAULT NULL,
  `permission_id` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_role_permission_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `tb_tag` */

DROP TABLE IF EXISTS `tb_tag`;

CREATE TABLE `tb_tag` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `tag_name` varchar(40) DEFAULT NULL COMMENT 'tag名称',
  `tag_color` varchar(20) DEFAULT NULL COMMENT 'tag颜色',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期',
  `delete_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签';

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `user_name` varchar(40) NOT NULL COMMENT '用户姓名',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `first_name` varchar(20) DEFAULT NULL COMMENT '姓',
  `last_name` varchar(40) DEFAULT NULL COMMENT '名',
  `birthday` datetime DEFAULT NULL COMMENT '出生年月日',
  `last_login_time` datetime DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT 'E-Mail',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期',
  `delete_flag` tinyint(2) NOT NULL DEFAULT '0',
  `user_status` tinyint(2) NOT NULL,
  `regdate` datetime DEFAULT NULL COMMENT '注册日期',
  `online` bigint(20) DEFAULT NULL COMMENT '在线时长（分钟）',
  `interest` varchar(100) DEFAULT NULL COMMENT '兴趣',
  `login_count` bigint(20) DEFAULT NULL COMMENT '登录次数',
  `personality` varchar(100) DEFAULT NULL COMMENT '个性',
  `fanscount` int(20) DEFAULT NULL COMMENT '粉丝数',
  `followcount` int(20) DEFAULT NULL COMMENT '关注数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';

/*Table structure for table `tb_user_role` */

DROP TABLE IF EXISTS `tb_user_role`;

CREATE TABLE `tb_user_role` (
  `role_id` varchar(20) NOT NULL COMMENT '角色ID',
  `user_id` varchar(20) NOT NULL COMMENT '权限ID',
  `create_time` datetime DEFAULT NULL COMMENT '修改日期',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期',
  `delete_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
