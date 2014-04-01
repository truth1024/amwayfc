DROP DATABASE IF EXISTS `amwayfc`;
create database amwayfc default charset utf8;
grant all privileges on amwayfc.* to amwayfc@'%' identified by 'amwayfc';
grant all privileges on amwayfc.* to amwayfc@'localhost' identified by 'amwayfc';
flush privileges;
use amwayfc;

set names utf8;

-- ----------------------------
-- Table structure for `t_journey`
-- ----------------------------
DROP TABLE IF EXISTS `t_journey`;
CREATE TABLE `t_journey` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '行程ID',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `briefinfo` varchar(1000) DEFAULT NULL COMMENT '简介',
  `start` datetime NOT NULL COMMENT '出发时间',
  `end` datetime DEFAULT NULL COMMENT '结束时间',
  `infopath` varchar(50) DEFAULT NULL,
  `num` int(11) DEFAULT NULL COMMENT '人数上限',
  `istake` tinyint(4) DEFAULT NULL COMMENT '是否可以带孩子 1：是；2：否；',
  `remainnum` varchar(10) DEFAULT NULL COMMENT '剩余人数',
  `duration` varchar(10) DEFAULT NULL COMMENT '时长',
  `suit` varchar(20) DEFAULT NULL COMMENT '适合人群',
  `power` varchar(20) DEFAULT NULL COMMENT '体力指数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_journey
-- ----------------------------
INSERT INTO `t_journey` VALUES ('1', '尼斯', '法国', '2014-07-18 00:00:00', '2014-07-18 23:00:00', null, null, '1', null, null, null, null);
INSERT INTO `t_journey` VALUES ('2', '戛纳', '法国', '2014-07-19 08:00:00', '2014-07-19 19:00:00', null, null, '1', null, null, null, null);
INSERT INTO `t_journey` VALUES ('3', '蒙地卡罗', '摩纳哥公园', '2014-07-19 21:00:00', '2014-07-19 22:30:00', null, null, '1', null, null, null, null);
INSERT INTO `t_journey` VALUES ('4', '马赛', '法国', '2014-07-20 15:52:18', null, null, null, '1', null, null, null, null);
INSERT INTO `t_journey` VALUES ('5', '巴塞隆拿返回各地', '【蒙追山】--【城堡公园】--【LA ROCA Village Outlets】', '2014-07-23 15:52:52', null, null, null, '1', null, null, null, null);
INSERT INTO `t_journey` VALUES ('6', '经典·马赛——探寻圣地', '【马赛旧港】--【圣母加德大教堂】--【隆尚宫】--【欧洲和地中海文明博物馆】', '2014-07-21 15:53:21', null, '/web/journeyIntroduce.html#3', '120', '1', '117', '7.5', '酷爱历史', '★★★');
INSERT INTO `t_journey` VALUES ('7', '恬静·马赛——峡湾悠荡', '【卡西斯】--【卡朗格峡湾】', '2014-07-21 15:53:58', null, '/web/journeyIntroduce.html#4', '120', '1', '117', '8', '探索求新', '★★★★★');
INSERT INTO `t_journey` VALUES ('8', '激情·马赛——航海魅力', '【帆船出海】--【伊夫岛】--【伊夫堡】', '2014-07-21 15:54:52', null, '/web/journeyIntroduce.html#5', '54', '2', '54', '7.75', '热爱自然', '★★★');
INSERT INTO `t_journey` VALUES ('9', '意境·巴塞罗那', '【奎尔公园】--【圣家族大教堂】--【感恩大道】', '2014-07-22 15:55:31', null, '/web/journeyIntroduce.html#6', null, '1', null, '7.5', '家庭同乐', '★★★');
INSERT INTO `t_journey` VALUES ('10', '华彩·巴塞罗那', '【蒙追山】--【城堡公园】--【LA ROCA Village Outlets】', '2014-07-22 15:55:52', null, '/web/journeyIntroduce.html#7', null, '1', null, '6.5', '老少皆宜', '★★★');
INSERT INTO `t_journey` VALUES ('11', '自由.马赛 —— 畅享邮轮', '', '2014-07-21 00:00:00', null, null, null, '1', null, null, null, null);
INSERT INTO `t_journey` VALUES ('12', '自行安排', null, '2014-07-22 00:00:00', null, null, null, '1', null, null, null, null);

-- ----------------------------
-- Table structure for `t_login`
-- ----------------------------
DROP TABLE IF EXISTS `t_login`;
CREATE TABLE `t_login` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '登录ID',
  `logincode` varchar(50) NOT NULL COMMENT '登录码',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `md5password` varchar(50) NOT NULL COMMENT '加密密码',
  `isfirst` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否为首次登录 1：是；2：否；',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_logincode` (`logincode`),
  KEY `index_password` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_login
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `logincode` varchar(50) NOT NULL COMMENT '登录码',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `isadult` tinyint(2) NOT NULL COMMENT '是否成年 1：已成年：2：未成年；',
  `isjoin` tinyint(2) DEFAULT NULL COMMENT '是否出席 1：是；2：否；',
  `areacode` varchar(20) DEFAULT NULL COMMENT '所属区域',
  `areaname` varchar(20) DEFAULT NULL COMMENT '区域名称',
  `province` varchar(20) DEFAULT NULL COMMENT '省份',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `store` varchar(20) DEFAULT NULL COMMENT '店铺',
  `nature` varchar(20) DEFAULT NULL COMMENT '性质',
  `relation` varchar(10) DEFAULT NULL,
  `birthdate` date DEFAULT NULL COMMENT '出生日期',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `visa` varchar(10) DEFAULT NULL COMMENT '签证',
  `airticket` varchar(10) DEFAULT NULL COMMENT '机票',
  `diet` varchar(10) DEFAULT NULL COMMENT '饮食禁忌',
  `istake` tinyint(4) DEFAULT NULL COMMENT '是否可以带孩子 1：是；2：否；',
  PRIMARY KEY (`id`),
  KEY `index_logincode` (`logincode`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_journey`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_journey`;
CREATE TABLE `t_user_journey` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户行程关联ID',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `jid` bigint(20) DEFAULT NULL COMMENT '行程ID',
  `auid` bigint(20) DEFAULT NULL COMMENT '父母ID',
  PRIMARY KEY (`id`),
  KEY `index_uid` (`uid`),
  KEY `index_auid` (`auid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;