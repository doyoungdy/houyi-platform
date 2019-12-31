
DROP TABLE IF EXISTS `client_paas`;
CREATE TABLE `client_paas`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(5) NOT NULL DEFAULT 1 COMMENT '状态(0:不可用,1:可用,-1:删除)',
  `service_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务标识',
  `service_name` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务名称',
  `service_desc` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务详细描述',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '显示排序',
  `create_time` datetime(0) NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE (`service_code`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `client_open_api`;
CREATE TABLE `client_open_api`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(5) NOT NULL DEFAULT 1 COMMENT '状态(0:不可用,1:可用,-1:删除)',
  `service_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所在服务',
  `api_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口名称',
  `api_path` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口path',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '显示排序',
  `create_time` datetime(0) NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `client_open_template`;
CREATE TABLE `client_open_template`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(5) NOT NULL DEFAULT 1 COMMENT '状态(0:不可用,1:可用,-1:删除)',
  `code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板code',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '开发模板名称',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '显示排序',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `code`(`code`) USING BTREE,
  UNIQUE (`code`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;



DROP TABLE IF EXISTS `client_r_template_api`;
CREATE TABLE `client_r_template_api`  (
  `template_id` int(11) NOT NULL COMMENT '模板id',
  `api_id` int(11) NOT NULL COMMENT '接口id',
  PRIMARY KEY (`template_id`, `api_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;



DROP TABLE IF EXISTS `client_r_template_client`;
CREATE TABLE `client_r_template_client`  (
  `client_id` int(11) NOT NULL COMMENT '客户端id',
  `template_id` int(11) NOT NULL COMMENT '模板id',
  PRIMARY KEY (`client_id`, `template_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

