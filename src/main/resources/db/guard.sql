/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : cmbguard

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-08-28 08:52:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_center
-- ----------------------------
DROP TABLE IF EXISTS `d_center`;
CREATE TABLE `d_center` (
  `id` int(11) NOT NULL,
  `center` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of d_center
-- ----------------------------
INSERT INTO `d_center` VALUES ('1', '总行');
INSERT INTO `d_center` VALUES ('2', '研发');
INSERT INTO `d_center` VALUES ('3', '科兴');
INSERT INTO `d_center` VALUES ('4', '1234');

-- ----------------------------
-- Table structure for d_city
-- ----------------------------
DROP TABLE IF EXISTS `d_city`;
CREATE TABLE `d_city` (
  `id` int(11) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of d_city
-- ----------------------------
INSERT INTO `d_city` VALUES ('1', '深圳');
INSERT INTO `d_city` VALUES ('2', '上海');
INSERT INTO `d_city` VALUES ('3', '杭州');
INSERT INTO `d_city` VALUES ('4', '1234');
INSERT INTO `d_city` VALUES ('5', '12345');

-- ----------------------------
-- Table structure for d_config
-- ----------------------------
DROP TABLE IF EXISTS `d_config`;
CREATE TABLE `d_config` (
  `userid` varchar(100) NOT NULL COMMENT '用户编号，0为默认配置',
  `key` varchar(100) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`,`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of d_config
-- ----------------------------
INSERT INTO `d_config` VALUES ('0', 'IGetITILWorkOrderAddressPostfix', '%22&view=expand', '获取ITIL工单地址后缀');
INSERT INTO `d_config` VALUES ('0', 'IGetITILWorkOrderAddressPrefix', '1http://99.1.76.103:13080/SM/9/rest/cmbguards?query=RequestorID=%22', '获取ITIL工单地址前缀');
INSERT INTO `d_config` VALUES ('0', 'LoginITILPassWord', 'liye', '登录ITIL密码');
INSERT INTO `d_config` VALUES ('0', 'LoginITILUserName', 'liye', '登录ITIL用户名');
INSERT INTO `d_config` VALUES ('1', 'DBName', '12341', '数据库名称');
INSERT INTO `d_config` VALUES ('1', 'DBPassword', '123', '数据库登录密码');
INSERT INTO `d_config` VALUES ('1', 'DBServer', '0.0.0.0', '数据库服务器地址');
INSERT INTO `d_config` VALUES ('1', 'DBType', '1', '数据库类型：SQLServer=1，Oracle=2');
INSERT INTO `d_config` VALUES ('1', 'DBUserName', '1234', '数据库登录用户名');
INSERT INTO `d_config` VALUES ('1', 'IGetConfigAddress', 'http://localhost:8080/#/sysSetting', '获取系统配置参数Rest接口地址');
INSERT INTO `d_config` VALUES ('1', 'InReaderAction', '1', '进入读卡器动作：1，进入；0，离开');
INSERT INTO `d_config` VALUES ('1', 'InReaderID', '333', '进入读卡器编号');
INSERT INTO `d_config` VALUES ('1', 'InReaderName', 'XXXXXX机房入口', '进入读卡器名称');
INSERT INTO `d_config` VALUES ('1', 'ISwipingCardAddress', 'http://localhost:8080/#/sysSetting', '发送工单刷卡记录Rest接口地址');
INSERT INTO `d_config` VALUES ('1', 'IValidateUserAddress', 'http://localhost:8080/#/sysSetting', '验证用户Rest接口地址');
INSERT INTO `d_config` VALUES ('1', 'IVendorAddress', 'http://localhost:8080/#/sysSetting', '发送工单供应商记录Rest接口地址');
INSERT INTO `d_config` VALUES ('1', 'IWorkOrderAddress', 'http://localhost:8080/#/sysSetting', '发送工单Rest接口地址');
INSERT INTO `d_config` VALUES ('1', 'MachineRoomID', '44', '机房编号');
INSERT INTO `d_config` VALUES ('1', 'MachineRoomName', 'i2/ 数据楼一楼机房', '机房名称');
INSERT INTO `d_config` VALUES ('1', 'OutReaderAction', '0', '离开读卡器动作：1，进入；0，离开');
INSERT INTO `d_config` VALUES ('1', 'OutReaderID', '444', '离开读卡器编号');
INSERT INTO `d_config` VALUES ('1', 'OutReaderName', 'XXXXXX机房出口', '离开读卡器名称');
INSERT INTO `d_config` VALUES ('1', 'RefreshDBInterval', '1234', '刷新数据间隔，单位：毫秒');
INSERT INTO `d_config` VALUES ('2', 'DBName', '12341', '数据库名称');
INSERT INTO `d_config` VALUES ('2', 'DBPassword', '123', '数据库登录密码');
INSERT INTO `d_config` VALUES ('2', 'DBServer', '0.0.0.0', '数据库服务器地址');
INSERT INTO `d_config` VALUES ('2', 'DBType', '1', '数据库类型：SQLServer=1，Oracle=2');
INSERT INTO `d_config` VALUES ('2', 'DBUserName', '1234', '数据库登录用户名');
INSERT INTO `d_config` VALUES ('2', 'IGetConfigAddress', 'http://localhost:8080/#/sysSetting', '获取系统配置参数Rest接口地址');
INSERT INTO `d_config` VALUES ('2', 'InReaderAction', '1', '进入读卡器动作：1，进入；0，离开');
INSERT INTO `d_config` VALUES ('2', 'InReaderID', '333', '进入读卡器编号');
INSERT INTO `d_config` VALUES ('2', 'InReaderName', 'XXXXXX机房入口', '进入读卡器名称');
INSERT INTO `d_config` VALUES ('2', 'ISwipingCardAddress', 'http://localhost:8080/#/sysSetting', '发送工单刷卡记录Rest接口地址');
INSERT INTO `d_config` VALUES ('2', 'IValidateUserAddress', 'http://localhost:8080/#/sysSetting', '验证用户Rest接口地址');
INSERT INTO `d_config` VALUES ('2', 'IVendorAddress', 'http://localhost:8080/#/sysSetting', '发送工单供应商记录Rest接口地址');
INSERT INTO `d_config` VALUES ('2', 'IWorkOrderAddress', 'http://localhost:8080/#/sysSetting', '发送工单Rest接口地址');
INSERT INTO `d_config` VALUES ('2', 'MachineRoomID', '44', '机房编号');
INSERT INTO `d_config` VALUES ('2', 'MachineRoomName', 'i2/ 数据楼一楼机房', '机房名称');
INSERT INTO `d_config` VALUES ('2', 'OutReaderAction', '0', '离开读卡器动作：1，进入；0，离开');
INSERT INTO `d_config` VALUES ('2', 'OutReaderID', '444', '离开读卡器编号');
INSERT INTO `d_config` VALUES ('2', 'OutReaderName', 'XXXXXX机房出口', '离开读卡器名称');
INSERT INTO `d_config` VALUES ('2', 'RefreshDBInterval', '512341', '刷新数据间隔，单位：毫秒');
INSERT INTO `d_config` VALUES ('3', 'DBName', 'Amadeus5', '数据库名称');
INSERT INTO `d_config` VALUES ('3', 'DBPassword', 'Adminuser01', '数据库登录密码');
INSERT INTO `d_config` VALUES ('3', 'DBServer', '99.1.90.222', '数据库服务器地址');
INSERT INTO `d_config` VALUES ('3', 'DBType', '1', '数据库类型：SQLServer=1，Oracle=2');
INSERT INTO `d_config` VALUES ('3', 'DBUserName', 'user01', '数据库登录用户名');
INSERT INTO `d_config` VALUES ('3', 'IGetConfigAddress', 'http://localhost:8086/GetConfig', '获取系统配置参数Rest接口地址');
INSERT INTO `d_config` VALUES ('3', 'InReaderAction', '1', '进入读卡器动作：1，进入；0，离开');
INSERT INTO `d_config` VALUES ('3', 'InReaderID', '333', '进入读卡器编号');
INSERT INTO `d_config` VALUES ('3', 'InReaderName', 'XXXXXX机房入口', '进入读卡器名称');
INSERT INTO `d_config` VALUES ('3', 'ISwipingCardAddress', 'http://localhost:8086/SwipingCard', '发送工单刷卡记录Rest接口地址');
INSERT INTO `d_config` VALUES ('3', 'IValidateUserAddress', 'http://localhost:8086/ValidateUser', '验证用户Rest接口地址');
INSERT INTO `d_config` VALUES ('3', 'IVendorAddress', 'http://localhost:8086/Vendor', '发送工单供应商记录Rest接口地址');
INSERT INTO `d_config` VALUES ('3', 'IWorkOrderAddress', 'http://localhost:8086/WorkOrder', '发送工单Rest接口地址');
INSERT INTO `d_config` VALUES ('3', 'MachineRoomID', '1123', '机房编号');
INSERT INTO `d_config` VALUES ('3', 'MachineRoomName', '', '机房名称');
INSERT INTO `d_config` VALUES ('3', 'OutReaderAction', '0', '离开读卡器动作：1，进入；0，离开');
INSERT INTO `d_config` VALUES ('3', 'OutReaderID', '444', '离开读卡器编号');
INSERT INTO `d_config` VALUES ('3', 'OutReaderName', 'XXXXXX机房出口', '离开读卡器名称');
INSERT INTO `d_config` VALUES ('3', 'RefreshDBInterval', '100', '刷新数据间隔，单位：毫秒');

-- ----------------------------
-- Table structure for d_machineroom
-- ----------------------------
DROP TABLE IF EXISTS `d_machineroom`;
CREATE TABLE `d_machineroom` (
  `cityid` int(10) NOT NULL COMMENT '城市编号',
  `centerid` int(10) NOT NULL COMMENT '中心编号',
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '机房编号',
  `code` varchar(100) DEFAULT NULL COMMENT '机房在物业管理系统中的编号',
  `name` varchar(100) DEFAULT NULL COMMENT '机房在物业管理系统中的名称',
  `ip` varchar(100) DEFAULT NULL COMMENT '机房主控电脑IP',
  `inreaderid` varchar(10) DEFAULT NULL COMMENT '进入机房刷卡器编号',
  `outreaderid` varchar(10) DEFAULT NULL COMMENT '离开机房刷卡器编号',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_city` (`cityid`),
  CONSTRAINT `fk_city` FOREIGN KEY (`cityid`) REFERENCES `d_city` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of d_machineroom
-- ----------------------------
INSERT INTO `d_machineroom` VALUES ('1', '2', '1', '44', 'i2/ 数据楼一楼机房', null, '1', '2');
INSERT INTO `d_machineroom` VALUES ('1', '2', '2', '55', 'i2/ 数据楼一楼机房', '', '1', '2');
INSERT INTO `d_machineroom` VALUES ('1', '2', '3', '66', 'i2/ 数据楼一楼机房', '192.0.0.1', '1', '2');
INSERT INTO `d_machineroom` VALUES ('1', '1', '7', '1123', '7楼ECC', '99.1.90.72', '1', '2');
INSERT INTO `d_machineroom` VALUES ('1', '1', '20', '54423452345', 'asdfa', '192.1.1.255', '1', '2');
INSERT INTO `d_machineroom` VALUES ('1', '1', '21', '1234', '1234', '192.0.0.3', '1', '2');
INSERT INTO `d_machineroom` VALUES ('1', '2', '23', '1', '1', '192.0.0.1', '1', '2');
INSERT INTO `d_machineroom` VALUES ('1', '1', '24', '123412', '12341234', '192.0.0.1', '1', '3');
INSERT INTO `d_machineroom` VALUES ('2', '1', '26', '23453425', '23452345', '192.0.0.1', '1', '2');
INSERT INTO `d_machineroom` VALUES ('3', '1', '27', '44', 'asfasdf', '192.0.0.1', '1', '2');
INSERT INTO `d_machineroom` VALUES ('1', '1', '28', '66', '1231234', '192.0.0.1', '1', '2');
INSERT INTO `d_machineroom` VALUES ('1', '1', '29', '66', 'asdfasd', '192.0.0.1', '1', '2');
INSERT INTO `d_machineroom` VALUES ('1', '1', '30', '23543', '2345', '192.0.0.1', '1', '2');

-- ----------------------------
-- Table structure for d_reader
-- ----------------------------
DROP TABLE IF EXISTS `d_reader`;
CREATE TABLE `d_reader` (
  `id` int(11) NOT NULL COMMENT '读卡器在本系统的编号',
  `centerid` int(11) DEFAULT NULL COMMENT '中心编号',
  `number` int(11) DEFAULT NULL COMMENT '读卡器在物业管理系统里的编号',
  `name` varchar(100) DEFAULT NULL COMMENT '读卡器在物业管理系统里的编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of d_reader
-- ----------------------------
INSERT INTO `d_reader` VALUES ('1', '2', '333', 'XXXXXX机房入口');
INSERT INTO `d_reader` VALUES ('2', '2', '444', 'XXXXXX机房出口');
INSERT INTO `d_reader` VALUES ('3', '1', '1', 'k');
INSERT INTO `d_reader` VALUES ('4', '2', '555', 'XXXXXX机房入口');
INSERT INTO `d_reader` VALUES ('5', '4', '222', 'asdfsd');
INSERT INTO `d_reader` VALUES ('6', '1', '1234124', 'asdfasdf');
INSERT INTO `d_reader` VALUES ('7', '2', '12341234', '阿萨德发');

-- ----------------------------
-- Table structure for d_role
-- ----------------------------
DROP TABLE IF EXISTS `d_role`;
CREATE TABLE `d_role` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of d_role
-- ----------------------------
INSERT INTO `d_role` VALUES ('1', 'guard', 'qwert');
INSERT INTO `d_role` VALUES ('2', 'check ', 'asdf');

-- ----------------------------
-- Table structure for d_user
-- ----------------------------
DROP TABLE IF EXISTS `d_user`;
CREATE TABLE `d_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `roleid` int(10) DEFAULT NULL,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_user
-- ----------------------------
INSERT INTO `d_user` VALUES ('0', '1', '0', '0');
INSERT INTO `d_user` VALUES ('1', '1', 'test', 'test');
INSERT INTO `d_user` VALUES ('2', '1', '1', '1');
INSERT INTO `d_user` VALUES ('3', '1', '2', '2');
INSERT INTO `d_user` VALUES ('5', '1', '123', '123');
INSERT INTO `d_user` VALUES ('6', '1', '5', '5');
INSERT INTO `d_user` VALUES ('7', '2', '6', '6');
INSERT INTO `d_user` VALUES ('8', '1', '9', '6');
INSERT INTO `d_user` VALUES ('9', '2', 'asdf', '123');
INSERT INTO `d_user` VALUES ('10', '1', '123456', '1234');
INSERT INTO `d_user` VALUES ('11', '2', '去玩儿群', '123');

-- ----------------------------
-- Table structure for r_swipingcard
-- ----------------------------
DROP TABLE IF EXISTS `r_swipingcard`;
CREATE TABLE `r_swipingcard` (
  `pk` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mrid` int(11) DEFAULT NULL COMMENT '机房编号',
  `workorderid` varchar(100) DEFAULT NULL COMMENT '工单编号',
  `time` datetime DEFAULT NULL COMMENT '刷卡时间',
  `readerid` varchar(100) DEFAULT NULL COMMENT '读卡器编号',
  `readername` varchar(100) DEFAULT NULL COMMENT '读卡器名称',
  `username` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `usernumber` varchar(100) DEFAULT NULL COMMENT '用户在供应商或者外包系统的编号',
  `userid` varchar(100) DEFAULT NULL COMMENT '用户身份证号码',
  `usertype` varchar(100) DEFAULT NULL COMMENT '用户类型',
  `usernumbertype` varchar(100) DEFAULT NULL COMMENT '用户编号类型（供应商，外包，行员）',
  `company` varchar(100) DEFAULT NULL COMMENT '公司',
  `department` varchar(100) DEFAULT NULL COMMENT '部门',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限',
  `liftname` varchar(100) DEFAULT NULL COMMENT '电梯',
  `location` varchar(100) DEFAULT NULL COMMENT '位置',
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB AUTO_INCREMENT=65643 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of r_swipingcard
-- ----------------------------
INSERT INTO `r_swipingcard` VALUES ('65606', '1', 'ITIL548998', '2018-08-10 18:43:02', '1', 'XXXXXX机房入口', '王治文', null, '11', '行员', '', '数据楼1号闸机出', '数据楼1号闸机出', '数据楼1号闸机出', '数据楼1号闸机出', null);
INSERT INTO `r_swipingcard` VALUES ('65607', '1', 'ITIL548998', '2018-08-10 18:58:17', '2', 'XXXXXX机房出口', '刘超', null, '2', '供应商', '供应商', '数据楼2号闸机进', '数据楼2号闸机进', '数据楼2号闸机进', '数据楼2号闸机进', null);
INSERT INTO `r_swipingcard` VALUES ('65608', '1', 'ITIL735749', '2018-08-10 19:04:37', '1', '陈坦', '陈坦', null, '234816', '行员', null, '研发八楼电梯厅', '研发八楼电梯厅', '研发八楼电梯厅', '研发八楼电梯厅', null);
INSERT INTO `r_swipingcard` VALUES ('65609', '1', 'ITIL796285', '2018-08-10 19:03:50', '2', '王江平', '王江平', null, '198681', '行员', null, '数据楼2号闸机出', '数据楼2号闸机出', '数据楼2号闸机出', '数据楼2号闸机出', null);
INSERT INTO `r_swipingcard` VALUES ('65610', '1', 'ITIL219924', '2018-08-10 19:16:32', '1', '殷俊', '殷俊', null, '198347', '行员', null, '数据七楼机房门出口', '数据七楼机房门出口', '数据七楼机房门出口', '数据七楼机房门出口', null);
INSERT INTO `r_swipingcard` VALUES ('65611', '1', 'ITIL219924', '2018-08-10 19:21:51', '2', '林晓东1', '林晓东1', null, '', '供应商', null, '数据楼1号闸机进', '数据楼1号闸机进', '数据楼1号闸机进', '数据楼1号闸机进', null);
INSERT INTO `r_swipingcard` VALUES ('65612', '1', 'ITIL219924', '2018-08-10 19:21:57', '1', '4号巡逻岗', '4号巡逻岗', null, '', '供应商', null, '研发十一楼6#楼梯', '研发十一楼6#楼梯', '研发十一楼6#楼梯', '研发十一楼6#楼梯', null);
INSERT INTO `r_swipingcard` VALUES ('65613', '0', 'ITIL231897', '2018-08-13 17:01:46', '2', '汪敏', '汪敏', null, '374273', '行员', null, '研发九楼电梯厅', '研发九楼电梯厅', '研发九楼电梯厅', '研发九楼电梯厅', null);
INSERT INTO `r_swipingcard` VALUES ('65614', '0', 'ITIL669248', '2018-08-13 21:59:01', '1', '罗杰', '罗杰', null, '375231', '行员', null, '大门闸机出口2出', '大门闸机出口2出', '大门闸机出口2出', '大门闸机出口2出', null);
INSERT INTO `r_swipingcard` VALUES ('65615', '0', 'ITIL669248', '2018-08-13 22:05:14', '2', '黄奕豪', '黄奕豪', null, '', '供应商', null, '数据楼9#电梯读卡器', '数据楼9#电梯读卡器', '数据楼9#电梯读卡器', '数据楼9#电梯读卡器', null);
INSERT INTO `r_swipingcard` VALUES ('65616', '0', 'ITIL150605', '2018-08-13 22:03:38', '1', '刘增荣', '刘增荣', null, '', '供应商', null, '数据楼2号闸机出', '数据楼2号闸机出', '数据楼2号闸机出', '数据楼2号闸机出', null);
INSERT INTO `r_swipingcard` VALUES ('65617', '0', 'ITIL150605', '2018-08-13 22:13:49', '2', '数据五楼值班卡', '数据五楼值班卡', null, '', '供应商', null, '数据五楼机房走道门', '数据五楼机房走道门', '数据五楼机房走道门', '数据五楼机房走道门', null);
INSERT INTO `r_swipingcard` VALUES ('65618', '0', 'ITIL150605', '2018-08-13 22:14:28', '1', '边慧善', '边慧善', null, '375100', '行员', null, '大门闸机出口1出', '大门闸机出口1出', '大门闸机出口1出', '大门闸机出口1出', null);
INSERT INTO `r_swipingcard` VALUES ('65619', '0', 'ITIL918931', '2018-08-14 15:57:24', '1', '李永平', '李永平', null, '231106', '行员', null, 'ECC值班岗', 'ECC值班岗', 'ECC值班岗', 'ECC值班岗', null);
INSERT INTO `r_swipingcard` VALUES ('65620', '0', 'ITIL890569', '2018-08-14 17:15:01', '1', '曹玲林', '曹玲林', null, '174586', '行员', null, 'ECC值班岗', 'ECC值班岗', 'ECC值班岗', 'ECC值班岗', null);
INSERT INTO `r_swipingcard` VALUES ('65621', '0', 'ITIL890569', '2018-08-14 17:16:28', '2', '何铁军', '何铁军', null, '430321197107114551', '非行员', null, '数据楼2号闸机进', '数据楼2号闸机进', '数据楼2号闸机进', '数据楼2号闸机进', null);
INSERT INTO `r_swipingcard` VALUES ('65622', '0', 'ITIL890569', '2018-08-14 17:16:30', '2', '华山', '华山', null, '', '供应商', null, '研发1#电梯', '研发1#电梯', '研发1#电梯', '研发1#电梯', null);
INSERT INTO `r_swipingcard` VALUES ('65623', '0', 'ITIL345709', '2018-08-14 23:14:27', '1', '周伟鸿', '周伟鸿', null, '098566', '行员', null, '数据楼2号闸机出', '数据楼2号闸机出', '数据楼2号闸机出', '数据楼2号闸机出', null);
INSERT INTO `r_swipingcard` VALUES ('65624', '0', 'ITIL916264', '2018-08-15 15:42:09', '1', '应建军', '应建军', null, '098603', '行员', null, '数据六楼电梯厅玻璃门', '数据六楼电梯厅玻璃门', '数据六楼电梯厅玻璃门', '数据六楼电梯厅玻璃门', null);
INSERT INTO `r_swipingcard` VALUES ('65625', '0', 'ITIL283759', '2018-08-15 15:39:11', '2', '许志科', '许志科', null, '440301197808233832', '非行员', null, '数据四楼电梯厅玻璃门', '数据四楼电梯厅玻璃门', '数据四楼电梯厅玻璃门', '数据四楼电梯厅玻璃门', null);
INSERT INTO `r_swipingcard` VALUES ('65626', '0', 'ITIL283759', '2018-08-15 15:43:15', '1', '杨文忠', '杨文忠', null, '', '供应商', null, '数据四楼电梯厅玻璃门', '数据四楼电梯厅玻璃门', '数据四楼电梯厅玻璃门', '数据四楼电梯厅玻璃门', null);
INSERT INTO `r_swipingcard` VALUES ('65627', '0', 'ITIL283759', '2018-08-15 15:43:24', '2', '数据六楼值班卡', '数据六楼值班卡', null, '', '供应商', null, '数据六楼电梯厅玻璃门', '数据六楼电梯厅玻璃门', '数据六楼电梯厅玻璃门', '数据六楼电梯厅玻璃门', null);
INSERT INTO `r_swipingcard` VALUES ('65628', '0', 'ITIL283759', '2018-08-15 15:43:32', '1', '缪龙漂', '缪龙漂', null, '274307', '行员', null, '数据楼1号闸机进', '数据楼1号闸机进', '数据楼1号闸机进', '数据楼1号闸机进', null);
INSERT INTO `r_swipingcard` VALUES ('65629', '0', 'ITIL242210', '2018-08-15 15:59:36', '2', '谭红梅', '谭红梅', null, '198601', '行员', null, '数据楼2号闸机出', '数据楼2号闸机出', '数据楼2号闸机出', '数据楼2号闸机出', null);
INSERT INTO `r_swipingcard` VALUES ('65630', '0', 'ITIL686136', '2018-08-16 23:29:49', '1', '董毅博', '董毅博', null, '374339', '行员', null, '数据楼9#电梯读卡器', '数据楼9#电梯读卡器', '数据楼9#电梯读卡器', '数据楼9#电梯读卡器', null);
INSERT INTO `r_swipingcard` VALUES ('65631', '0', 'ITIL686136', '2018-08-16 23:30:20', '2', '张亮', '张亮', null, '', '供应商', null, '监控中心东门', '监控中心东门', '监控中心东门', '监控中心东门', null);
INSERT INTO `r_swipingcard` VALUES ('65632', '0', 'ITIL686136', '2018-08-16 23:30:34', '1', '董毅博', '董毅博', null, '374339', '行员', null, '数据七楼电梯厅玻璃门', '数据七楼电梯厅玻璃门', '数据七楼电梯厅玻璃门', '数据七楼电梯厅玻璃门', null);
INSERT INTO `r_swipingcard` VALUES ('65633', '0', 'ITIL686136', '2018-08-16 23:32:13', '2', '5号巡逻岗', '5号巡逻岗', null, '', '供应商', null, '研发01楼屏蔽间2', '研发01楼屏蔽间2', '研发01楼屏蔽间2', '研发01楼屏蔽间2', null);
INSERT INTO `r_swipingcard` VALUES ('65634', '0', 'ITIL686136', '2018-08-16 23:32:42', '1', '数据四楼值班卡', '数据四楼值班卡', null, '', '供应商', null, '数据四楼电梯厅玻璃门', '数据四楼电梯厅玻璃门', '数据四楼电梯厅玻璃门', '数据四楼电梯厅玻璃门', null);
INSERT INTO `r_swipingcard` VALUES ('65635', '0', 'ITIL686136', '2018-08-16 23:33:16', '2', '入口岗值班卡', '入口岗值班卡', null, '', '供应商', null, '大门闸机入口2出', '大门闸机入口2出', '大门闸机入口2出', '大门闸机入口2出', null);
INSERT INTO `r_swipingcard` VALUES ('65636', '0', 'ITIL246106', '2018-08-16 23:37:04', '1', '李政宇', '李政宇', null, '234407', '行员', null, '大门闸机入口2出', '大门闸机入口2出', '大门闸机入口2出', '大门闸机入口2出', null);
INSERT INTO `r_swipingcard` VALUES ('65637', '0', 'ITIL428873', '2018-08-16 23:37:04', '2', '李政宇', '李政宇', null, '234407', '行员', null, '大门闸机入口2出', '大门闸机入口2出', '大门闸机入口2出', '大门闸机入口2出', null);
INSERT INTO `r_swipingcard` VALUES ('65638', '0', 'ITIL835389', '2018-08-16 23:37:04', '1', '李政宇', '李政宇', null, '234407', '行员', null, '大门闸机入口2出', '大门闸机入口2出', '大门闸机入口2出', '大门闸机入口2出', null);
INSERT INTO `r_swipingcard` VALUES ('65639', '0', 'ITIL610426', '2018-08-16 23:30:34', '2', '董毅博', '董毅博', null, '374339', '行员', null, '数据七楼电梯厅玻璃门', '数据七楼电梯厅玻璃门', '数据七楼电梯厅玻璃门', '数据七楼电梯厅玻璃门', null);
INSERT INTO `r_swipingcard` VALUES ('65640', '0', 'ITIL414808', '2018-08-16 23:23:32', '1', '易凯', '易凯', null, '375189', '行员', null, '大门闸机出口2出', '大门闸机出口2出', '大门闸机出口2出', '大门闸机出口2出', null);
INSERT INTO `r_swipingcard` VALUES ('65641', '0', 'ITIL630686', '2018-08-16 23:22:10', '2', '王明俊', '王明俊', null, '01158080', '行员', null, '大门闸机入口2出', '大门闸机入口2出', '大门闸机入口2出', '大门闸机入口2出', null);
INSERT INTO `r_swipingcard` VALUES ('65642', '0', 'ITIL984929', '2018-08-16 23:27:40', '1', '黄勇', '黄勇', null, '666666', '行员', null, '大门闸机出口1出', '大门闸机出口1出', '大门闸机出口1出', '大门闸机出口1出', null);

-- ----------------------------
-- Table structure for r_vendor
-- ----------------------------
DROP TABLE IF EXISTS `r_vendor`;
CREATE TABLE `r_vendor` (
  `pk` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mrid` int(10) DEFAULT NULL COMMENT '机房编号',
  `workorderid` varchar(100) DEFAULT NULL COMMENT '工单编号',
  `name` varchar(100) DEFAULT NULL COMMENT '供应商姓名',
  `id` varchar(100) DEFAULT NULL COMMENT '供应商身份证号码',
  `number` varchar(100) DEFAULT NULL COMMENT '供应商编号（在供应商或者外包系统中的编号）',
  `company` varchar(100) DEFAULT NULL COMMENT '供应商所属公司',
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB AUTO_INCREMENT=1607 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of r_vendor
-- ----------------------------
INSERT INTO `r_vendor` VALUES ('1487', '1', 'ITIL548998', '彝', '925844667631212435', 'RP61378', '公司名占淬嘶勉');
INSERT INTO `r_vendor` VALUES ('1488', '1', 'ITIL548998', '咬', '979725246848849148', 'YR8849973', '公司名咬靠');
INSERT INTO `r_vendor` VALUES ('1489', '1', 'ITIL548998', '入挚范烤', '891869192546362154', 'NB21418', '公司名入挚');
INSERT INTO `r_vendor` VALUES ('1490', '1', 'ITIL548998', '智', '857711248117974189', 'EN481315', '公司名魄毋智');
INSERT INTO `r_vendor` VALUES ('1491', '1', 'ITIL548998', '智', '498648485747533426', 'BV762791', '公司名魄毋');
INSERT INTO `r_vendor` VALUES ('1492', '1', 'ITIL735749', '菠瞳', '355471984971364454', 'RL915171', '公司名谐在炒');
INSERT INTO `r_vendor` VALUES ('1493', '1', 'ITIL735749', '菠瞳炒碳', '384977257454992393', 'DA36854', '公司名谐在炒碳');
INSERT INTO `r_vendor` VALUES ('1494', '1', 'ITIL735749', '菠瞳', '597846426844114367', 'DH74868', '公司名模技');
INSERT INTO `r_vendor` VALUES ('1495', '1', 'ITIL735749', '汛咱储怔', '515835189482258771', 'PJ271687', '公司名汛咱');
INSERT INTO `r_vendor` VALUES ('1496', '1', 'ITIL735749', '趁技训冀', '391447566635267124', 'JU48721', '公司名模技训冀');
INSERT INTO `r_vendor` VALUES ('1497', '1', 'ITIL735749', '趁技', '934845618268197486', 'EG891637', '公司名汛咱储怔');
INSERT INTO `r_vendor` VALUES ('1498', '1', 'ITIL735749', '模技训冀', '684692625647412554', 'XB6646125', '公司名趁技训冀');
INSERT INTO `r_vendor` VALUES ('1499', '1', 'ITIL796285', '耸衣', '146481965864951339', 'ET14414', '公司名挚氛');
INSERT INTO `r_vendor` VALUES ('1500', '1', 'ITIL796285', '寇柳绅各', '964695184241221595', 'QQ859377', '公司名寇柳绅');
INSERT INTO `r_vendor` VALUES ('1501', '1', 'ITIL796285', '疏伊氨', '647374388759356251', 'VQ4425136', '公司名榨给');
INSERT INTO `r_vendor` VALUES ('1502', '1', 'ITIL219924', '探颜郊', '411164769874741478', 'RP1944639', '公司名网职阜挚');
INSERT INTO `r_vendor` VALUES ('1503', '1', 'ITIL219924', '葱', '891611283277673263', 'ZQ66686', '公司名宙捡');
INSERT INTO `r_vendor` VALUES ('1504', '1', 'ITIL219924', '扎贫', '899698657784238771', 'CV41725', '公司名翱贫烤延');
INSERT INTO `r_vendor` VALUES ('1505', '1', 'ITIL219924', '峡', '756281195435121834', 'YZ51486', '公司名扎贫烤延');
INSERT INTO `r_vendor` VALUES ('1506', '1', 'ITIL219924', '翱贫', '253262429686618212', 'OM6151344', '公司名翱贫');
INSERT INTO `r_vendor` VALUES ('1507', '1', 'ITIL219924', '峡贫烤延', '315123814158279378', 'EM3428456', '公司名扎贫');
INSERT INTO `r_vendor` VALUES ('1508', null, 'ITIL231897', '仕真荡', '433478592494968755', 'VJ9348697', '公司名拈掏俭次');
INSERT INTO `r_vendor` VALUES ('1509', null, 'ITIL231897', '映槛舷许', '346833462168261915', 'CZ182464', '公司名逮器舷');
INSERT INTO `r_vendor` VALUES ('1510', null, 'ITIL231897', '讶樟蝴留', '961868739334654148', 'IY5742948', '公司名热樟蝴留');
INSERT INTO `r_vendor` VALUES ('1511', null, 'ITIL231897', '讶', '892487227974396984', 'TE859569', '公司名讶樟');
INSERT INTO `r_vendor` VALUES ('1512', null, 'ITIL231897', '缓樟', '951585322162885771', 'OK64631', '公司名讶樟蝴留');
INSERT INTO `r_vendor` VALUES ('1513', null, 'ITIL231897', '讶樟蝴', '966393973218125926', 'AE8887832', '公司名讶樟');
INSERT INTO `r_vendor` VALUES ('1514', null, 'ITIL669248', '腹丸脏', '889424629285476868', 'GE662884', '公司名娘戌');
INSERT INTO `r_vendor` VALUES ('1515', null, 'ITIL669248', '辊搜', '769752584478732122', 'LF61947', '公司名燥戌没墟');
INSERT INTO `r_vendor` VALUES ('1516', null, 'ITIL669248', '娘戌', '612295417353441116', 'ZQ942439', '公司名娘戌');
INSERT INTO `r_vendor` VALUES ('1517', null, 'ITIL150605', '脖', '677283883264782164', 'QF265724', '公司名冶宙挝主');
INSERT INTO `r_vendor` VALUES ('1518', null, 'ITIL150605', '冶', '156441519454819734', 'LU82668', '公司名冶宙挝主');
INSERT INTO `r_vendor` VALUES ('1519', null, 'ITIL150605', '脖宙挝主', '397894443797986163', 'MT336836', '公司名冶宙挝主');
INSERT INTO `r_vendor` VALUES ('1520', null, 'ITIL918931', '艇', '649316636568147395', 'WT89254', '公司名圆呵鸳');
INSERT INTO `r_vendor` VALUES ('1521', null, 'ITIL918931', '圆呵鸳', '973933517168249794', 'AS75436', '公司名艺狸');
INSERT INTO `r_vendor` VALUES ('1522', null, 'ITIL918931', '喝', '694244993677476728', 'OY61774', '公司名艺狸');
INSERT INTO `r_vendor` VALUES ('1523', null, 'ITIL918931', '艺狸茹', '491755157615466579', 'SH46876', '公司名喝吵');
INSERT INTO `r_vendor` VALUES ('1524', null, 'ITIL918931', '喝吵', '733968489414473381', 'OC497264', '公司名钦狸茹');
INSERT INTO `r_vendor` VALUES ('1525', null, 'ITIL890569', '俱扼驭', '854855793258972467', 'IB89518', '公司名俱扼驭挽');
INSERT INTO `r_vendor` VALUES ('1526', null, 'ITIL890569', '俱扼', '924243152757782469', 'NQ317427', '公司名俱扼');
INSERT INTO `r_vendor` VALUES ('1527', null, 'ITIL890569', '婆碗披循', '751315929768154293', 'TI61485', '公司名驭扼');
INSERT INTO `r_vendor` VALUES ('1528', null, 'ITIL890569', '俱', '417315349867611215', 'WO1271629', '公司名俱扼驭挽');
INSERT INTO `r_vendor` VALUES ('1529', null, 'ITIL890569', '婆碗', '542218349159765572', 'RY83382', '公司名哲察蛰翼');
INSERT INTO `r_vendor` VALUES ('1530', null, 'ITIL890569', '哲', '864842436378312388', 'NZ5179788', '公司名哲察蛰');
INSERT INTO `r_vendor` VALUES ('1531', null, 'ITIL345709', '喘筋', '228732938415353346', 'QC66927', '公司名喘斤');
INSERT INTO `r_vendor` VALUES ('1532', null, 'ITIL345709', '堵菊臻梳', '639793842238815972', 'FD428442', '公司名堵菊臻');
INSERT INTO `r_vendor` VALUES ('1533', null, 'ITIL345709', '凛枢', '484715271722168838', 'KW7354463', '公司名凛枢犯梳');
INSERT INTO `r_vendor` VALUES ('1534', null, 'ITIL345709', '砧铱犯梳', '392749133157363142', 'QL24377', '公司名凛枢犯梳');
INSERT INTO `r_vendor` VALUES ('1535', null, 'ITIL345709', '凛枢', '622823683173393249', 'IJ77453', '公司名堵菊臻');
INSERT INTO `r_vendor` VALUES ('1536', null, 'ITIL345709', '砧铱犯梳', '394291456724257469', 'KZ29295', '公司名凛枢');
INSERT INTO `r_vendor` VALUES ('1537', null, 'ITIL345709', '凛枢犯', '396537619226615582', 'DT8232524', '公司名犯野诌幼');
INSERT INTO `r_vendor` VALUES ('1538', null, 'ITIL345709', '麻浚', '778742291322817727', 'UB86344', '公司名麻浚高');
INSERT INTO `r_vendor` VALUES ('1539', null, 'ITIL345709', '置酥锚', '549658259968615121', 'VN1617655', '公司名置酥');
INSERT INTO `r_vendor` VALUES ('1540', null, 'ITIL916264', '乔依', '181389183777798631', 'SD4834589', '公司名浙伊莎哩');
INSERT INTO `r_vendor` VALUES ('1541', null, 'ITIL916264', '执屿', '266218476141991424', 'CO483222', '公司名莎甲蔬禹');
INSERT INTO `r_vendor` VALUES ('1542', null, 'ITIL916264', '催甲蔬', '537994217528618188', 'LO43732', '公司名执屿蔬');
INSERT INTO `r_vendor` VALUES ('1543', null, 'ITIL283759', '羽', '929691381515695926', 'XM65568', '公司名羽壶');
INSERT INTO `r_vendor` VALUES ('1544', null, 'ITIL283759', '羽', '722485776361689945', 'MU136764', '公司名牌绣');
INSERT INTO `r_vendor` VALUES ('1545', null, 'ITIL283759', '牌绣期', '831887656439682245', 'PK8277718', '公司名岔壶期');
INSERT INTO `r_vendor` VALUES ('1546', null, 'ITIL283759', '岔', '715888697128133576', 'CF3817656', '公司名牌样');
INSERT INTO `r_vendor` VALUES ('1547', null, 'ITIL283759', '浴', '612682213515428352', 'NT8957858', '公司名期矽');
INSERT INTO `r_vendor` VALUES ('1548', null, 'ITIL283759', '浴矽', '372188634764579955', 'EJ99444', '公司名期矽瞅');
INSERT INTO `r_vendor` VALUES ('1549', null, 'ITIL242210', '冀批俞堕', '125412161867585665', 'AQ989352', '公司名逾珠叹');
INSERT INTO `r_vendor` VALUES ('1550', null, 'ITIL242210', '万批俞堕', '188346556125724118', 'OE211489', '公司名冀批俞');
INSERT INTO `r_vendor` VALUES ('1551', null, 'ITIL242210', '逾珠叹', '193627953629261724', 'FQ525251', '公司名万批');
INSERT INTO `r_vendor` VALUES ('1552', null, 'ITIL242210', '逾珠叹', '244479211721321872', 'HD897244', '公司名披巡磨');
INSERT INTO `r_vendor` VALUES ('1553', null, 'ITIL686136', '明恕蚂逛', '956541846953172156', 'HV298368', '公司名喻恕蚂逛');
INSERT INTO `r_vendor` VALUES ('1554', null, 'ITIL686136', '明恕蚂', '986517584667357364', 'LR41292', '公司名门握砧旺');
INSERT INTO `r_vendor` VALUES ('1555', null, 'ITIL686136', '砧淹杜', '753392427876311275', 'TD87528', '公司名佃淹杜铱');
INSERT INTO `r_vendor` VALUES ('1556', null, 'ITIL686136', '佃', '284876679763568184', 'IE297221', '公司名婆握砧旺');
INSERT INTO `r_vendor` VALUES ('1557', null, 'ITIL686136', '佃淹杜', '994949166575266251', 'UN5489815', '公司名婆握');
INSERT INTO `r_vendor` VALUES ('1558', null, 'ITIL246106', '酣卞折', '631664445968953854', 'FF292173', '公司名栓卞');
INSERT INTO `r_vendor` VALUES ('1559', null, 'ITIL246106', '酣卞折', '157663411139763885', 'CJ522878', '公司名酣卞折');
INSERT INTO `r_vendor` VALUES ('1560', null, 'ITIL246106', '酣卞折', '233776115333222538', 'VB2858256', '公司名酣卞折唁');
INSERT INTO `r_vendor` VALUES ('1561', null, 'ITIL246106', '栓卞折', '397634444338876585', 'KL856572', '公司名预残迂');
INSERT INTO `r_vendor` VALUES ('1562', null, 'ITIL246106', '预残迂', '693617769431146342', 'JO8784621', '公司名缓戌霖戌');
INSERT INTO `r_vendor` VALUES ('1563', null, 'ITIL246106', '缓戌', '417122245717692561', 'LS811282', '公司名陇残迂');
INSERT INTO `r_vendor` VALUES ('1564', null, 'ITIL246106', '缓戌', '677365993965148613', 'ES854388', '公司名陇残迂戌');
INSERT INTO `r_vendor` VALUES ('1565', null, 'ITIL428873', '痊恿夯', '991381561551677853', 'DQ23722', '公司名痊恿夯');
INSERT INTO `r_vendor` VALUES ('1566', null, 'ITIL428873', '泻沧', '298726473977179657', 'QX158948', '公司名汉沧墒凌');
INSERT INTO `r_vendor` VALUES ('1567', null, 'ITIL428873', '泻沧墒', '815588286123555797', 'EY2584328', '公司名泻沧墒');
INSERT INTO `r_vendor` VALUES ('1568', null, 'ITIL428873', '痊恿', '365279678688993817', 'XX782334', '公司名痊恿夯');
INSERT INTO `r_vendor` VALUES ('1569', null, 'ITIL428873', '斟薛拆学', '967523584387995569', 'XH2482951', '公司名拆夕');
INSERT INTO `r_vendor` VALUES ('1570', null, 'ITIL428873', '黔薛拆学', '818486198435825426', 'KN8594662', '公司名斟薛拆学');
INSERT INTO `r_vendor` VALUES ('1571', null, 'ITIL428873', '黔', '736297875851838642', 'ES3565599', '公司名斟薛拆学');
INSERT INTO `r_vendor` VALUES ('1572', null, 'ITIL428873', '黔', '785269928195781757', 'RJ6258998', '公司名拆夕拆');
INSERT INTO `r_vendor` VALUES ('1573', null, 'ITIL835389', '造汉拨', '986824745173598999', 'BI932988', '公司名造汉');
INSERT INTO `r_vendor` VALUES ('1574', null, 'ITIL835389', '去汉', '943353578671355593', 'HS65716', '公司名去汉拨硝');
INSERT INTO `r_vendor` VALUES ('1575', null, 'ITIL835389', '饱汉', '225126421958863397', 'SN482755', '公司名饱汉');
INSERT INTO `r_vendor` VALUES ('1576', null, 'ITIL835389', '亦', '618826116882879925', 'XC9668187', '公司名亦膊');
INSERT INTO `r_vendor` VALUES ('1577', null, 'ITIL835389', '亦膊', '525763925812292531', 'EN25914', '公司名乖廉');
INSERT INTO `r_vendor` VALUES ('1578', null, 'ITIL835389', '亦', '678672971655727625', 'UU2731149', '公司名乖廉');
INSERT INTO `r_vendor` VALUES ('1579', null, 'ITIL835389', '亦膊', '643376568845211299', 'GL7645545', '公司名亦膊绎犁');
INSERT INTO `r_vendor` VALUES ('1580', null, 'ITIL835389', '稍', '226546919834573326', 'WQ8315851', '公司名稍廉稍');
INSERT INTO `r_vendor` VALUES ('1581', null, 'ITIL610426', '毋捡', '539637368494425411', 'GG39744', '公司名漾勤');
INSERT INTO `r_vendor` VALUES ('1582', null, 'ITIL610426', '漾勤', '565685496312146356', 'AA31792', '公司名辖达');
INSERT INTO `r_vendor` VALUES ('1583', null, 'ITIL610426', '漾', '599355567167551327', 'BJ235224', '公司名嘉勤眩掉');
INSERT INTO `r_vendor` VALUES ('1584', null, 'ITIL610426', '嘉勤眩掉', '621921752381249787', 'HQ382689', '公司名漾勤眩掉');
INSERT INTO `r_vendor` VALUES ('1585', null, 'ITIL610426', '辖', '411137229216874375', 'HT96367', '公司名辖达嘻');
INSERT INTO `r_vendor` VALUES ('1586', null, 'ITIL610426', '嘉勤眩掉', '576791942349844784', 'KD779881', '公司名辖达');
INSERT INTO `r_vendor` VALUES ('1587', null, 'ITIL610426', '崔', '555338893367462441', 'QY528586', '公司名掷耀');
INSERT INTO `r_vendor` VALUES ('1588', null, 'ITIL414808', '胀给在样', '263822575452285323', 'EE7451814', '公司名蚁卿胳');
INSERT INTO `r_vendor` VALUES ('1589', null, 'ITIL414808', '享', '533439615131388197', 'OE5673377', '公司名垢挚');
INSERT INTO `r_vendor` VALUES ('1590', null, 'ITIL414808', '蚁卿胳挚', '451618871273822314', 'MF43787', '公司名蚁卿胳挚');
INSERT INTO `r_vendor` VALUES ('1591', null, 'ITIL414808', '蚁卿胳挚', '494852338782211854', 'EJ44267', '公司名垢挚椅挚');
INSERT INTO `r_vendor` VALUES ('1592', null, 'ITIL414808', '垢挚椅挚', '328356578522671552', 'QM47425', '公司名蚁卿胳挚');
INSERT INTO `r_vendor` VALUES ('1593', null, 'ITIL414808', '垢', '993166995337656337', 'EN581427', '公司名垢挚蚁');
INSERT INTO `r_vendor` VALUES ('1594', null, 'ITIL414808', '鞍肛翱茸', '752741376329659761', 'UP8462233', '公司名涟肛翱茸');
INSERT INTO `r_vendor` VALUES ('1595', null, 'ITIL630686', '款', '281412315377168791', 'VG623269', '公司名款荧薪');
INSERT INTO `r_vendor` VALUES ('1596', null, 'ITIL630686', '芯东薪锡', '696199621221178635', 'QF62756', '公司名蔷东薪锡');
INSERT INTO `r_vendor` VALUES ('1597', null, 'ITIL630686', '款荧', '542123472998264345', 'JE176153', '公司名芯东');
INSERT INTO `r_vendor` VALUES ('1598', null, 'ITIL630686', '芯东', '133417383753853312', 'WC6195857', '公司名芯东');
INSERT INTO `r_vendor` VALUES ('1599', null, 'ITIL630686', '芯', '231371821425179351', 'YW868919', '公司名蔷兑');
INSERT INTO `r_vendor` VALUES ('1600', null, 'ITIL984929', '俄斟荡郊', '746959648146428147', 'SN64541', '公司名秧斟荡郊');
INSERT INTO `r_vendor` VALUES ('1601', null, 'ITIL984929', '俄斟荡', '737798444569493891', 'AG5669327', '公司名秧斟');
INSERT INTO `r_vendor` VALUES ('1602', null, 'ITIL984929', '梢道延里', '566798675913412544', 'VS756862', '公司名菌道延里');
INSERT INTO `r_vendor` VALUES ('1603', null, 'ITIL984929', '薪在', '699765712415866415', 'AB38161', '公司名薪在');
INSERT INTO `r_vendor` VALUES ('1604', null, 'ITIL984929', '梢道', '537429317217858782', 'QX31197', '公司名薪在');
INSERT INTO `r_vendor` VALUES ('1605', null, 'ITIL984929', '拥株点录', '158827977978982362', 'UC57236', '公司名兜株点');
INSERT INTO `r_vendor` VALUES ('1606', null, 'ITIL984929', '兜株', '957818592393228314', 'VI195167', '公司名兜株点录');

-- ----------------------------
-- Table structure for r_workorder
-- ----------------------------
DROP TABLE IF EXISTS `r_workorder`;
CREATE TABLE `r_workorder` (
  `mrid` int(10) DEFAULT NULL COMMENT '机房编号',
  `id` varchar(60) NOT NULL COMMENT '工单编号',
  `description` text COMMENT '工单描述',
  `userid` varchar(60) DEFAULT NULL COMMENT '行员编号',
  `username` varchar(60) DEFAULT NULL COMMENT '行员姓名',
  `permited` tinyint(6) DEFAULT NULL COMMENT '是否批准',
  `begintime` datetime DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `vendors` varchar(255) DEFAULT NULL COMMENT '供应商',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of r_workorder
-- ----------------------------
INSERT INTO `r_workorder` VALUES (null, 'ITIL150605', '窘痰窖印蜗乔迂侨坷犬', '506455', '温婉宙', null, '2018-08-13 22:15:58', '2018-08-13 22:15:58', null);
INSERT INTO `r_workorder` VALUES ('1', 'ITIL219924', '浇颜郊创诊闯换窑瞩菠', '712290', '公浇颜', null, '2018-08-10 19:23:35', '2018-08-10 19:23:35', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL231897', '仕真荡侥檀幕脆盆硬洪', '320455', '公羊言真', null, '2018-08-13 17:04:46', '2018-08-13 17:04:46', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL242210', '氨坦银毯绵寐脏悬曙曼', '681347', '蓬万批', null, '2018-08-15 16:02:28', '2018-08-15 16:02:28', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL246106', '哲闽数变胳雁呻裂焚畜', '568373', '戴哲闽', null, '2018-08-17 00:25:54', '2018-08-17 00:25:54', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL283759', '羽壶期换源患球血吹利', '195090', '池羽壶', null, '2018-08-15 15:45:06', '2018-08-15 15:45:06', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL345709', '武许喘妖垫妖袁趣谅墒', '863031', '安原筋', null, '2018-08-15 00:41:46', '2018-08-15 00:41:46', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL414808', '胀给在样艇喧运妹倔宣', '221146', '伍违凄', null, '2018-08-17 00:26:58', '2018-08-17 00:26:58', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL428873', '痊恿夯幼粥又冀凑州迷', '389459', '赵泻沧', null, '2018-08-17 00:26:49', '2018-08-17 00:26:49', null);
INSERT INTO `r_workorder` VALUES ('1', 'ITIL548998', '彝正碗父碗喳慰喳邢东', '886646', '葛标抹', null, '2018-08-10 18:59:47', '2018-08-10 18:59:47', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL610426', '带捡往珠抵木谭州他敏', '869896', '乌毋捡', null, '2018-08-17 00:26:55', '2018-08-17 00:26:55', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL630686', '啤惟遗兆脚牺峙诣坚吵', '153311', '充蔷东', null, '2018-08-17 00:27:00', '2018-08-17 00:27:00', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL669248', '漳烟脏滩嘿悬贼湿伦宣', '816021', '黎腹丸', null, '2018-08-13 22:06:46', '2018-08-13 22:06:46', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL686136', '氨帧蚂逛临旋厂患厂扔', '528885', '卢氨帧', null, '2018-08-16 23:34:50', '2018-08-16 23:34:50', null);
INSERT INTO `r_workorder` VALUES ('1', 'ITIL735749', '谐在炒碳训冀玫施露揪', '652775', '颛孙菠瞳', null, '2018-08-10 19:07:47', '2018-08-10 19:07:47', null);
INSERT INTO `r_workorder` VALUES ('1', 'ITIL796285', '掘衣疏吩神玲震厌惹厉', '678822', '齐掘衣', null, '2018-08-10 19:07:56', '2018-08-10 19:07:56', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL835389', '去汉拨硝躁患搓屯莹吞', '936411', '羊舌饱汉', null, '2018-08-17 00:26:52', '2018-08-17 00:26:52', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL890569', '婆碗披循郊檀狞词寐出', '770163', '平俱扼', null, '2018-08-14 17:18:05', '2018-08-14 17:18:05', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL916264', '骗乖蝉学茹良值易值仑', '311735', '温馋湖', null, '2018-08-15 15:44:41', '2018-08-15 15:44:41', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL918931', '圆呵鸳呵翔燕穿壬盏赦', '930530', '屠圆呵', null, '2018-08-14 15:59:43', '2018-08-14 15:59:43', null);
INSERT INTO `r_workorder` VALUES (null, 'ITIL984929', '晚斟荡郊艺甄达楼巢吝', '516308', '蓟俄斟', null, '2018-08-17 00:27:11', '2018-08-17 00:27:11', null);

-- ----------------------------
-- View structure for view1
-- ----------------------------
DROP VIEW IF EXISTS `view1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view1` AS select `r_workorder`.`id` AS `workorderid`,`d_reader`.`number` AS `readernum`,`d_reader`.`name` AS `readername`,`r_vendor`.`name` AS `vendorname`,`r_swipingcard`.`username` AS `username`,date_format(`r_swipingcard`.`time`,'%Y-%m-%d %H:%i:%s') AS `time`,`r_workorder`.`permited` AS `permited`,date_format(`r_workorder`.`begintime`,'%Y-%m-%d %H:%i:%s') AS `begintime`,date_format(`r_workorder`.`endtime`,'%Y-%m-%d %H:%i:%s') AS `endtime`,`d_machineroom`.`inreaderid` AS `inreaderid`,`d_machineroom`.`outreaderid` AS `outreaderid`,`r_workorder`.`description` AS `description`,`r_workorder`.`userid` AS `bankernum`,`r_workorder`.`username` AS `banker`,`r_swipingcard`.`usernumbertype` AS `usernumbertype`,`r_swipingcard`.`userid` AS `idcard`,`r_swipingcard`.`usernumber` AS `usernumber`,`r_swipingcard`.`usertype` AS `usertype`,`d_machineroom`.`cityid` AS `cityid`,`d_city`.`name` AS `cityname`,`d_machineroom`.`centerid` AS `centerid`,`d_center`.`center` AS `centername`,`r_workorder`.`mrid` AS `mrid`,`d_machineroom`.`id` AS `machineroomid`,`d_machineroom`.`code` AS `code`,`d_machineroom`.`name` AS `roomname`,`r_swipingcard`.`readerid` AS `readerid`,`r_swipingcard`.`liftname` AS `liftname`,`r_swipingcard`.`location` AS `location`,`r_vendor`.`number` AS `vendornum`,`r_vendor`.`id` AS `id`,`r_workorder`.`vendors` AS `vendors`,`r_vendor`.`company` AS `vendorcompany`,`r_swipingcard`.`company` AS `company`,`r_swipingcard`.`department` AS `department`,`r_swipingcard`.`permission` AS `permission` from ((((((`r_swipingcard` left join `r_workorder` on((`r_swipingcard`.`workorderid` = `r_workorder`.`id`))) left join `r_vendor` on((`r_vendor`.`workorderid` = `r_workorder`.`id`))) left join `d_machineroom` on((`r_swipingcard`.`mrid` = `d_machineroom`.`id`))) left join `d_city` on((`d_machineroom`.`cityid` = `d_city`.`id`))) left join `d_center` on((`d_machineroom`.`centerid` = `d_center`.`id`))) left join `d_reader` on((`r_swipingcard`.`readerid` = `d_reader`.`id`))) ;
SET FOREIGN_KEY_CHECKS=1;
