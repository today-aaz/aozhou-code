/*
 Navicat Premium Data Transfer

 Source Server         : azhou
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : aozhou_code

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 25/12/2024 16:41:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `permission` varchar(255) NOT NULL COMMENT '权限标识，如 user:create, admin:delete',
  `permission_desc` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of sys_permissions
-- ----------------------------
BEGIN;
INSERT INTO `sys_permissions` (`id`, `permission`, `permission_desc`, `created_at`, `updated_at`) VALUES (1, 'user:create', '用户创建权限', '2024-12-24 14:22:45', '2024-12-24 14:22:45');
INSERT INTO `sys_permissions` (`id`, `permission`, `permission_desc`, `created_at`, `updated_at`) VALUES (2, 'user:update', '用户更新权限', '2024-12-24 14:22:45', '2024-12-24 14:22:45');
INSERT INTO `sys_permissions` (`id`, `permission`, `permission_desc`, `created_at`, `updated_at`) VALUES (3, 'user:delete', '用户删除权限', '2024-12-24 14:22:45', '2024-12-24 14:22:45');
INSERT INTO `sys_permissions` (`id`, `permission`, `permission_desc`, `created_at`, `updated_at`) VALUES (4, 'admin:delete', '管理员删除权限', '2024-12-24 14:22:45', '2024-12-24 14:22:45');
INSERT INTO `sys_permissions` (`id`, `permission`, `permission_desc`, `created_at`, `updated_at`) VALUES (5, 'admin:view', '管理员查看权限', '2024-12-24 14:22:45', '2024-12-24 14:22:45');
INSERT INTO `sys_permissions` (`id`, `permission`, `permission_desc`, `created_at`, `updated_at`) VALUES (6, 'admin:update', '管理员更新权限', '2024-12-24 14:22:45', '2024-12-24 14:22:45');
INSERT INTO `sys_permissions` (`id`, `permission`, `permission_desc`, `created_at`, `updated_at`) VALUES (7, 'role:assign', '角色分配权限', '2024-12-24 14:22:45', '2024-12-24 14:22:45');
INSERT INTO `sys_permissions` (`id`, `permission`, `permission_desc`, `created_at`, `updated_at`) VALUES (8, 'role:delete', '角色删除权限', '2024-12-24 14:22:45', '2024-12-24 14:22:45');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permissions`;
CREATE TABLE `sys_role_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色权限关系ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `permission_id` bigint NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色&权限关联表';

-- ----------------------------
-- Records of sys_role_permissions
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (1, 1, 1);
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (2, 1, 2);
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (3, 1, 3);
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (4, 1, 4);
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (5, 1, 5);
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (6, 1, 6);
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (7, 1, 7);
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (8, 2, 1);
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (9, 2, 2);
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (10, 3, 2);
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (11, 3, 4);
INSERT INTO `sys_role_permissions` (`id`, `role_id`, `permission_id`) VALUES (12, 4, 5);
COMMIT;

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(255) NOT NULL COMMENT '角色名',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
BEGIN;
INSERT INTO `sys_roles` (`id`, `role_name`, `role_desc`, `created_at`, `updated_at`) VALUES (1, 'admin', '管理员角色，拥有所有权限', '2024-12-24 14:22:53', '2024-12-24 14:22:53');
INSERT INTO `sys_roles` (`id`, `role_name`, `role_desc`, `created_at`, `updated_at`) VALUES (2, 'user', '普通用户角色，拥有基本的操作权限', '2024-12-24 14:22:53', '2024-12-24 14:22:53');
INSERT INTO `sys_roles` (`id`, `role_name`, `role_desc`, `created_at`, `updated_at`) VALUES (3, 'moderator', '版主角色，具有管理用户的部分权限', '2024-12-24 14:22:53', '2024-12-24 14:22:53');
INSERT INTO `sys_roles` (`id`, `role_name`, `role_desc`, `created_at`, `updated_at`) VALUES (4, 'guest', '访客角色，只有查看权限', '2024-12-24 14:22:53', '2024-12-24 14:22:53');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户角色关系ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户&角色关联表';

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_roles` (`id`, `user_id`, `role_id`) VALUES (1, 1, 1);
INSERT INTO `sys_user_roles` (`id`, `user_id`, `role_id`) VALUES (2, 2, 2);
INSERT INTO `sys_user_roles` (`id`, `user_id`, `role_id`) VALUES (3, 3, 3);
INSERT INTO `sys_user_roles` (`id`, `user_id`, `role_id`) VALUES (4, 4, 4);
COMMIT;

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '加密后的密码',
  `salt` varchar(255) NOT NULL COMMENT '密码盐',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `status` int DEFAULT '1' COMMENT '用户状态：1启用，0禁用',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_users
-- ----------------------------
BEGIN;
INSERT INTO `sys_users` (`id`, `username`, `password`, `salt`, `email`, `phone`, `status`, `created_at`, `updated_at`) VALUES (1, 'admin', '123456', 'aozhou', NULL, NULL, 1, '2024-12-23 10:14:01', '2024-12-23 10:14:01');
INSERT INTO `sys_users` (`id`, `username`, `password`, `salt`, `email`, `phone`, `status`, `created_at`, `updated_at`) VALUES (2, 'admin_user', '123456', 'salt_1', 'admin@example.com', '12345678901', 1, '2024-12-24 14:23:24', '2024-12-24 14:26:54');
INSERT INTO `sys_users` (`id`, `username`, `password`, `salt`, `email`, `phone`, `status`, `created_at`, `updated_at`) VALUES (3, 'normal_user', '123456', 'salt_2', 'user@example.com', '12345678902', 1, '2024-12-24 14:23:24', '2024-12-24 14:26:54');
INSERT INTO `sys_users` (`id`, `username`, `password`, `salt`, `email`, `phone`, `status`, `created_at`, `updated_at`) VALUES (4, 'mod_user', '123456', 'salt_3', 'mod@example.com', '12345678903', 1, '2024-12-24 14:23:24', '2024-12-24 14:26:54');
INSERT INTO `sys_users` (`id`, `username`, `password`, `salt`, `email`, `phone`, `status`, `created_at`, `updated_at`) VALUES (5, 'guest_user', '123456', 'salt_4', 'guest@example.com', '12345678904', 1, '2024-12-24 14:23:24', '2024-12-24 14:26:54');
INSERT INTO `sys_users` (`id`, `username`, `password`, `salt`, `email`, `phone`, `status`, `created_at`, `updated_at`) VALUES (7, 'aozhou', '93583f712f4c714406fd592b4eccf554', 'aozhou', '', NULL, 1, '2024-12-25 11:14:30', '2024-12-25 11:14:30');
COMMIT;

-- ----------------------------
-- Table structure for uid_baidu_worker
-- ----------------------------
DROP TABLE IF EXISTS `uid_baidu_worker`;
CREATE TABLE `uid_baidu_worker` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增 ID',
  `host_name` varchar(64) NOT NULL COMMENT '主机名',
  `port` varchar(64) NOT NULL COMMENT '端口',
  `type` int NOT NULL COMMENT '节点类型：实际节点或容器节点',
  `launch_date` date NOT NULL COMMENT '启动日期',
  `modified` timestamp NOT NULL COMMENT '修改时间',
  `created` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用于 UID 生成器的工作节点分配器';

-- ----------------------------
-- Records of uid_baidu_worker
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
