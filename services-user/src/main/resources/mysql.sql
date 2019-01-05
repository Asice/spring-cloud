CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `tid` bigint(64) DEFAULT NULL COMMENT '操作表id',
  `tname` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作表名',
  `content` text COMMENT '操作内容',
  `ip` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作者IP',
  `location` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作地点',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '更新时间',
  `cname` varchar(16) NOT NULL COMMENT '创建人',
  `uname` varchar(16) DEFAULT NULL COMMENT '更新人',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码md5',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态 0有效 1锁定',
  `last_login_time` datetime DEFAULT NULL COMMENT '更新时间',
  `register_ip` varchar(64) DEFAULT NULL COMMENT '注册ip',
  `last_login_ip` varchar(64) DEFAULT NULL COMMENT '最后登陆ip',
  `last_login_city` varchar(32) DEFAULT NULL COMMENT '登陆地址',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '更新时间',
  `wname` varchar(16) DEFAULT NULL COMMENT '操作人',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态：0:激活，1:禁止',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '更新时间',
  `cname` varchar(16) NOT NULL COMMENT '创建人',
  `uname` varchar(16) DEFAULT NULL COMMENT '更新人',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `rid` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `parent_id` int(20) NOT NULL COMMENT '上级菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单/按钮名称',
  `url` varchar(100) DEFAULT NULL COMMENT '菜单URL',
  `perms` text COMMENT '权限标识',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `type` char(2) NOT NULL COMMENT '类型 0菜单 1按钮',
  `order_num` int(20) DEFAULT NULL COMMENT '排序',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '更新时间',
  `cname` varchar(16) NOT NULL COMMENT '创建人',
  `uname` varchar(16) DEFAULT NULL COMMENT '更新人',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8 COMMENT='菜单表';

CREATE TABLE `t_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `rid` int(11) NOT NULL COMMENT '角色ID',
  `mid` int(11) NOT NULL COMMENT '菜单/按钮ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3519 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';







