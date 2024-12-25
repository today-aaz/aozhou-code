

-- 创建库
create database if not exists aozhou_code;

-- 切换库
use aozhou_code;

-- 用户表
create table sys_users
(
    id         bigint auto_increment comment '用户ID'
        primary key,
    username   varchar(255)                        not null comment '用户名',
    password   varchar(255)                        not null comment '加密后的密码',
    salt       varchar(255)                        not null comment '密码盐',
    email      varchar(255)                        null comment '邮箱',
    phone      varchar(15)                         null comment '手机号',
    status     int       default 1                 null comment '用户状态：1启用，0禁用',
    created_at timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint username
        unique (username)
)
    comment '用户信息表' charset = utf8mb4;


-- 角色表
create table sys_roles
(
    id         bigint auto_increment comment '角色ID'
        primary key,
    role_name  varchar(255)                        not null comment '角色名',
    role_desc  varchar(255)                        null comment '角色描述',
    created_at timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint role_name
        unique (role_name)
)
    comment '角色表' charset = utf8mb4;



-- 用户&角色关联表
create table sys_user_roles
(
    id      bigint auto_increment comment '用户角色关系ID'
        primary key,
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID'
)
    comment '用户&角色关联表' charset = utf8mb4;

-- 权限表
create table sys_permissions
(
    id              bigint auto_increment comment '权限ID'
        primary key,
    permission      varchar(255)                        not null comment '权限标识，如 user:create, admin:delete',
    permission_desc varchar(255)                        null comment '权限描述',
    created_at      timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at      timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint permission
        unique (permission)
)
    comment '权限表' charset = utf8mb4;

-- 角色&权限关联表
create table sys_role_permissions
(
    id            bigint auto_increment comment '角色权限关系ID'
        primary key,
    role_id       bigint not null comment '角色ID',
    permission_id bigint not null comment '权限ID'
)
    comment '角色&权限关联表' charset = utf8mb4;

