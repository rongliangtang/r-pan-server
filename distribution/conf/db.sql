SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for r_pan_file
-- ----------------------------
DROP TABLE IF EXISTS `r_pan_file`;

CREATE TABLE `r_pan_file`
(
    `file_id`                   bigint                                                 NOT NULL COMMENT '文件id',
    `filename`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件名称',
    `real_path`                 varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件物理路径',
    `file_size`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件实际大小',
    `file_size_desc`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件大小展示字符',
    `file_suffix`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件后缀',
    `file_preview_content_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件预览的响应头Content-Type的值',
    `identifier`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件唯一标识',
    `create_user`               bigint                                                 NOT NULL COMMENT '创建人',
    `create_time`               datetime                                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC COMMENT ='物理文件信息表';

-- ----------------------------
-- Table structure for r_pan_share
-- ----------------------------
DROP TABLE IF EXISTS `r_pan_share`;
CREATE TABLE `r_pan_share`
(
    `share_id`       bigint(0) NOT NULL COMMENT '分享id',
    `share_name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '分享名称',
    `share_type`     tinyint(1) NOT NULL DEFAULT 0 COMMENT '分享类型（0 有提取码）',
    `share_day_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '分享类型（0 永久有效；1 7天有效；2 30天有效）',
    `share_day`      tinyint(1) NOT NULL DEFAULT 0 COMMENT '分享有效天数（永久有效为0）',
    `share_end_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '分享结束时间',
    `share_url`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '分享链接地址',
    `share_code`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '分享提取码',
    `share_status`   tinyint(1) NOT NULL DEFAULT 0 COMMENT '分享状态（0 正常；1 有文件被删除）',
    `create_user`    bigint(0) NOT NULL COMMENT '分享创建人',
    `create_time`    datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    PRIMARY KEY (`share_id`) USING BTREE,
    UNIQUE INDEX `uk_create_user_time` (`create_user`, `create_time`) USING BTREE COMMENT '创建人、创建时间唯一索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '用户分享表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for r_pan_share_file
-- ----------------------------
DROP TABLE IF EXISTS `r_pan_share_file`;
CREATE TABLE `r_pan_share_file`
(
    `id`          bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `share_id`    bigint(0) NOT NULL COMMENT '分享id',
    `file_id`     bigint(0) NOT NULL COMMENT '文件记录ID',
    `create_user` bigint(0) NOT NULL COMMENT '分享创建人',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_share_id_file_id` (`share_id`, `file_id`) USING BTREE COMMENT '分享ID、文件ID联合唯一索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '用户分享文件表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for r_pan_user
-- ----------------------------
DROP TABLE IF EXISTS `r_pan_user`;
CREATE TABLE `r_pan_user`
(
    `user_id`     bigint(0) NOT NULL COMMENT '用户id',
    `username`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '用户名',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '密码',
    `salt`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '随机盐值',
    `question`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '密保问题',
    `answer`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '密保答案',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE INDEX `uk_username` (`username`) USING BTREE COMMENT '用户名唯一索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '用户信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for r_pan_user_file
-- ----------------------------
DROP TABLE IF EXISTS `r_pan_user_file`;
CREATE TABLE `r_pan_user_file`
(
    `file_id`        bigint(20) NOT NULL COMMENT '文件记录ID',
    `user_id`        bigint(20) NOT NULL COMMENT '用户ID',
    `parent_id`      bigint(20) NOT NULL COMMENT '上级文件夹ID,顶级文件夹为0',
    `real_file_id`   bigint(20) NOT NULL DEFAULT '0' COMMENT '真实文件id',
    `filename`       varchar(255) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件名',
    `folder_flag`    tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是文件夹 （0 否 1 是）',
    `file_size_desc` varchar(255) COLLATE utf8mb4_bin NOT NULL DEFAULT '--' COMMENT '文件大小展示字符',
    `file_type`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '文件类型（1 普通文件 2 压缩文件 3 excel 4 word 5 pdf 6 txt 7 图片 8 音频 9 视频 10 ppt 11 源码文件 12 csv）',
    `del_flag`       tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识（0 否 1 是）',
    `create_user`    bigint(20) NOT NULL COMMENT '创建人',
    `create_time`    datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user`    bigint(20) NOT NULL COMMENT '更新人',
    `update_time`    datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`file_id`) USING BTREE,
    KEY              `index_file_list` (`user_id`, `del_flag`, `parent_id`, `file_type`, `file_id`, `filename`, `folder_flag`,
        `file_size_desc`, `create_time`, `update_time`) USING BTREE COMMENT '查询文件列表索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC COMMENT ='用户文件信息表';

-- ----------------------------
-- Table structure for r_pan_user_search_history
-- ----------------------------
DROP TABLE IF EXISTS `r_pan_user_search_history`;
CREATE TABLE `r_pan_user_search_history`
(
    `id`             bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`        bigint(0) NOT NULL COMMENT '用户id',
    `search_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '搜索文案',
    `create_time`    datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `update_time`    datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_user_id_search_content_update_time` (`user_id`, `search_content`, `update_time`) USING BTREE COMMENT '用户id、搜索内容和更新时间唯一索引',
    UNIQUE INDEX `uk_user_id_search_content` (`user_id`, `search_content`) USING BTREE COMMENT '用户id和搜索内容唯一索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '用户搜索历史表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for r_pan_file_chunk
-- ----------------------------

DROP TABLE IF EXISTS `r_pan_file_chunk`;
CREATE TABLE `r_pan_file_chunk`
(
    `id`              bigint                           NOT NULL AUTO_INCREMENT COMMENT '主键',
    `identifier`      varchar(255) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '文件唯一标识',
    `real_path`       varchar(700) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '分片真实的存储路径',
    `chunk_number`    int                              NOT NULL DEFAULT '0' COMMENT '分片编号',
    `expiration_time` datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
    `create_user`     bigint                           NOT NULL COMMENT '创建人',
    `create_time`     datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_identifier_chunk_number_create_user` (`identifier`, `chunk_number`, `create_user`) USING BTREE COMMENT '文件唯一标识、分片编号和用户ID的唯一索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 101
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='文件分片信息表';

-- ----------------------------
-- Table structure for r_pan_error_log
-- ----------------------------

DROP TABLE IF EXISTS `r_pan_error_log`;
CREATE TABLE `r_pan_error_log`
(
    `id`          bigint                           NOT NULL AUTO_INCREMENT COMMENT '主键',
    `log_content` varchar(900) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '日志内容',
    `log_status`  tinyint                                   DEFAULT '0' COMMENT '日志状态：0 未处理 1 已处理',
    `create_user` bigint                           NOT NULL COMMENT '创建人',
    `create_time` datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user` bigint                           NOT NULL COMMENT '更新人',
    `update_time` datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='错误日志表';

SET
FOREIGN_KEY_CHECKS = 1;