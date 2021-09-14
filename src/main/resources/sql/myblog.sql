-- 数据库初始化脚本

-- 创建数据库v1.0
/*CREATE
DATABASE myblog;*/

-- 使用数据库
USE myblog;

-- 创建t_blog表
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`
(
    `id`              bigint NOT NULL AUTO_INCREMENT COMMENT '博客id',
    `title`           varchar(255) DEFAULT NULL COMMENT '博客标题',
    `content`         longtext COMMENT '博客内容',
    `first_picture`   varchar(255) DEFAULT NULL COMMENT '首图',
    `flag`            varchar(255) DEFAULT NULL COMMENT '标记',
    `views`           int          DEFAULT NULL COMMENT '浏览次数',
    `appreciation`    bit(1) NOT NULL COMMENT '赞赏开启',
    `share_statement` bit(1) NOT NULL COMMENT '版权开启',
    `commentable`     bit(1) NOT NULL COMMENT '评论开启',
    `published`       bit(1) NOT NULL COMMENT '是否发布',
    `recommend`       bit(1) NOT NULL COMMENT '是否推荐',
    `create_time`     datetime(6) DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime(6) DEFAULT NULL COMMENT '更新时间',
    `type_id`         bigint       DEFAULT NULL COMMENT '分类id',
    `user_id`         bigint       DEFAULT NULL COMMENT '用户id',
    `description`     longtext COMMENT '描述',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1060 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='t_blog表';

-- 创建t_user表
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`    varchar(255) DEFAULT NULL COMMENT '用户姓名',
    `password`    varchar(255) DEFAULT NULL COMMENT '用户密码',
    `type`        varchar(255) DEFAULT NULL COMMENT '用户类型',
    `nickname`    varchar(255) DEFAULT NULL COMMENT '用户昵称',
    `avatar`      varchar(255) DEFAULT NULL COMMENT '用户头像',
    `email`       varchar(255) DEFAULT NULL COMMENT '用户邮箱',
    `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT 't_user表';

-- 创建t_type表
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`
(
    `id`   bigint NOT NULL AUTO_INCREMENT COMMENT '分类id',
    `name` varchar(255) DEFAULT NULL COMMENT '分类名称',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT 't_type表';

-- 创建t_comment表
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`
(
    `id`                int    NOT NULL AUTO_INCREMENT COMMENT '评论id',
    `nickname`          varchar(255) DEFAULT NULL COMMENT '评论者昵称',
    `email`             varchar(255) DEFAULT NULL COMMENT '评论者邮箱',
    `avatar`            varchar(255) DEFAULT NULL COMMENT '评论者头像',
    `content`           varchar(255) DEFAULT NULL COMMENT '评论内容',
    `create_time`       datetime(6) DEFAULT NULL COMMENT '评论创建时间',
    `blog_id`           bigint       DEFAULT NULL COMMENT '博客id',
    `parent_comment_id` bigint       DEFAULT NULL COMMENT '父评论id',
    `admin_comment`     bit(1) NOT NULL COMMENT '管理评论',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT 't_comment表';