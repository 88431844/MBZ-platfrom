/*
SQLyog Ultimate v11.3 (64 bit)
MySQL - 5.7.16 : Database - fhadmin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fhadmin` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `fhadmin`;

/*Table structure for table `dao_face_attribute` */

DROP TABLE IF EXISTS `dao_face_attribute`;

CREATE TABLE `dao_face_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `age` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '年龄',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `head` int(11) DEFAULT NULL COMMENT '头部装饰',
  `face` int(11) DEFAULT NULL COMMENT '面部装饰',
  `hair` int(11) DEFAULT NULL COMMENT '头发装饰',
  `definition` int(11) DEFAULT NULL COMMENT '清晰度',
  `updateCount` int(11) DEFAULT NULL COMMENT '修改次数',
  `status` int(11) DEFAULT '0' COMMENT '状态(0:采纳,1:不采纳)',
  `user` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '操作员',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '写入时间',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66794 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `data_annotations_txt` */

DROP TABLE IF EXISTS `data_annotations_txt`;

CREATE TABLE `data_annotations_txt` (
  `txtAnnotations_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `txtId` int(11) NOT NULL COMMENT '文本ID',
  `txtDate` date DEFAULT NULL COMMENT '文本时间',
  `txtTitle` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文本标题',
  `txtContent` text COLLATE utf8_bin COMMENT '文本内容',
  `txtFilePath` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文本路径',
  `relevance` double DEFAULT NULL COMMENT '文本相关性',
  `relevanceContent` text COLLATE utf8_bin COMMENT '对比相关性文本',
  `resultTitle` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标注结果标题',
  `resultContent` text COLLATE utf8_bin COMMENT '标注结果',
  `resultFilePath` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标注结果文件路径',
  `type` int(11) DEFAULT NULL COMMENT '项目类型（0：墨迹天气）',
  `isLock` int(11) DEFAULT NULL COMMENT '修改锁（0：未标注，1：正在标注，2.标注完成，3.正在审核，4.审核完成）',
  `status` int(11) DEFAULT NULL COMMENT '状态（0：无效，1：有效）',
  `checkStatus` int(11) DEFAULT '0' COMMENT '审核状态（0：未通过，1.已通过）',
  `userId` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户ID',
  `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `checkUserId` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '审核人员ID',
  `checkUserName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '审核人员名称',
  `editTime` varchar(255) COLLATE utf8_bin DEFAULT '0' COMMENT '耗时',
  `creatTime` timestamp NULL DEFAULT NULL COMMENT '写入时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`txtAnnotations_id`,`txtId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `data_annotations_txt_copy` */

DROP TABLE IF EXISTS `data_annotations_txt_copy`;

CREATE TABLE `data_annotations_txt_copy` (
  `txtAnnotations_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `txtId` int(11) DEFAULT NULL COMMENT '文本ID',
  `txtDate` date DEFAULT NULL COMMENT '文本时间',
  `txtTitle` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文本标题',
  `txtContent` text COLLATE utf8_bin COMMENT '文本内容',
  `txtFilePath` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文本路径',
  `relevance` double DEFAULT NULL COMMENT '文本相关性',
  `relevanceContent` text COLLATE utf8_bin COMMENT '对比相关性文本',
  `resultTitle` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标注结果标题',
  `resultContent` text COLLATE utf8_bin COMMENT '标注结果',
  `resultFilePath` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标注结果文件路径',
  `type` int(11) DEFAULT NULL COMMENT '项目类型（0：墨迹天气）',
  `isLock` int(11) DEFAULT NULL COMMENT '修改锁（0：未标注，1：正在标注，2.标注完成，3.审核完成）',
  `status` int(11) DEFAULT NULL COMMENT '状态（0：无效，1：有效）',
  `checkStatus` int(11) DEFAULT '0' COMMENT '审核状态（0：未通过，1.已通过）',
  `userId` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户ID',
  `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `creatTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '写入时间',
  `updateTime` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`txtAnnotations_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `data_annotations_txt_copy_1015_19` */

DROP TABLE IF EXISTS `data_annotations_txt_copy_1015_19`;

CREATE TABLE `data_annotations_txt_copy_1015_19` (
  `txtAnnotations_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `txtId` int(11) NOT NULL COMMENT '文本ID',
  `txtDate` date DEFAULT NULL COMMENT '文本时间',
  `txtTitle` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文本标题',
  `txtContent` text COLLATE utf8_bin COMMENT '文本内容',
  `txtFilePath` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文本路径',
  `relevance` double DEFAULT NULL COMMENT '文本相关性',
  `relevanceContent` text COLLATE utf8_bin COMMENT '对比相关性文本',
  `resultTitle` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标注结果标题',
  `resultContent` text COLLATE utf8_bin COMMENT '标注结果',
  `resultFilePath` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标注结果文件路径',
  `type` int(11) DEFAULT NULL COMMENT '项目类型（0：墨迹天气）',
  `isLock` int(11) DEFAULT NULL COMMENT '修改锁（0：未标注，1：正在标注，2.标注完成，3.正在审核，4.审核完成）',
  `status` int(11) DEFAULT NULL COMMENT '状态（0：无效，1：有效）',
  `checkStatus` int(11) DEFAULT '0' COMMENT '审核状态（0：未通过，1.已通过）',
  `userId` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户ID',
  `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `checkUserId` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '审核人员ID',
  `checkUserName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '审核人员名称',
  `editTime` varchar(255) COLLATE utf8_bin DEFAULT '0' COMMENT '耗时',
  `creatTime` timestamp NULL DEFAULT NULL COMMENT '写入时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`txtAnnotations_id`,`txtId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `data_tagging_log` */

DROP TABLE IF EXISTS `data_tagging_log`;

CREATE TABLE `data_tagging_log` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '日志ID',
  `projectId` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目ID',
  `projectName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目名称',
  `subjectId` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目对应题ID',
  `beforeUpdateContent` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '问题修改前',
  `afterUpdateContent` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '问题修改后',
  `updateSumTime` double DEFAULT NULL COMMENT '修改总时间',
  `updateStatus` int(11) DEFAULT '1' COMMENT '修改是否成功(0:未成功，1:成功)',
  `userType` int(11) DEFAULT NULL COMMENT '修改人类型（0:标注人员，1:审核人员）',
  `taggingType` int(11) DEFAULT NULL COMMENT '标注类型（0：状态标注，1：内容标注）',
  `updateUserId` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `updateUserName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `updateStartTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改开始时间',
  `updateEndTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `meituan_searcherrorcorrection` */

DROP TABLE IF EXISTS `meituan_searcherrorcorrection`;

CREATE TABLE `meituan_searcherrorcorrection` (
  `meituanErrorCorrection_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `cityId` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '城市ID',
  `cityName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '城市名称',
  `keyword` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '搜索词',
  `rewriteKeyword` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '改写词',
  `result` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '结果',
  `userId` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人id',
  `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人姓名',
  `isLock` int(11) DEFAULT NULL COMMENT '题锁（0：未标注，1：正在标注，2.标注完成，3.正在审核，4.审核完成）',
  `status` int(11) DEFAULT NULL COMMENT '状态（0:未回收，1：已回收）',
  `checkUserId` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '审核人ID',
  `checkUserName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '审核人姓名',
  `checkStatus` int(11) DEFAULT NULL COMMENT '审核状态（0：未通过，1.已通过）',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '写入时间',
  PRIMARY KEY (`meituanErrorCorrection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `sys_app_user` */

DROP TABLE IF EXISTS `sys_app_user`;

CREATE TABLE `sys_app_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `PHONE` varchar(100) DEFAULT NULL,
  `SFID` varchar(100) DEFAULT NULL,
  `START_TIME` varchar(100) DEFAULT NULL,
  `END_TIME` varchar(100) DEFAULT NULL,
  `YEARS` int(10) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_createcode` */

DROP TABLE IF EXISTS `sys_createcode`;

CREATE TABLE `sys_createcode` (
  `CREATECODE_ID` varchar(100) NOT NULL,
  `PACKAGENAME` varchar(50) DEFAULT NULL COMMENT '包名',
  `OBJECTNAME` varchar(50) DEFAULT NULL COMMENT '类名',
  `TABLENAME` varchar(50) DEFAULT NULL COMMENT '表名',
  `FIELDLIST` varchar(5000) DEFAULT NULL COMMENT '属性集',
  `CREATETIME` varchar(100) DEFAULT NULL COMMENT '创建时间',
  `TITLE` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`CREATECODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_department` */

DROP TABLE IF EXISTS `sys_department`;

CREATE TABLE `sys_department` (
  `DEPARTMENT_ID` varchar(100) NOT NULL,
  `NAME` varchar(30) DEFAULT NULL COMMENT '名称',
  `NAME_EN` varchar(50) DEFAULT NULL COMMENT '英文',
  `BIANMA` varchar(50) DEFAULT NULL COMMENT '编码',
  `PARENT_ID` varchar(100) DEFAULT NULL COMMENT '上级ID',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  `HEADMAN` varchar(30) DEFAULT NULL COMMENT '负责人',
  `TEL` varchar(50) DEFAULT NULL COMMENT '电话',
  `FUNCTIONS` varchar(255) DEFAULT NULL COMMENT '部门职能',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_dictionaries` */

DROP TABLE IF EXISTS `sys_dictionaries`;

CREATE TABLE `sys_dictionaries` (
  `DICTIONARIES_ID` varchar(100) NOT NULL,
  `NAME` varchar(30) DEFAULT NULL COMMENT '名称',
  `NAME_EN` varchar(50) DEFAULT NULL COMMENT '英文',
  `BIANMA` varchar(50) DEFAULT NULL COMMENT '编码',
  `ORDER_BY` int(11) NOT NULL COMMENT '排序',
  `PARENT_ID` varchar(100) DEFAULT NULL COMMENT '上级ID',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  `TBSNAME` varchar(100) DEFAULT NULL COMMENT '排查表',
  PRIMARY KEY (`DICTIONARIES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_fhbutton` */

DROP TABLE IF EXISTS `sys_fhbutton`;

CREATE TABLE `sys_fhbutton` (
  `FHBUTTON_ID` varchar(100) NOT NULL,
  `NAME` varchar(30) DEFAULT NULL COMMENT '名称',
  `QX_NAME` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`FHBUTTON_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_fhsms` */

DROP TABLE IF EXISTS `sys_fhsms`;

CREATE TABLE `sys_fhsms` (
  `FHSMS_ID` varchar(100) NOT NULL,
  `CONTENT` varchar(1000) DEFAULT NULL COMMENT '内容',
  `TYPE` varchar(5) DEFAULT NULL COMMENT '类型',
  `TO_USERNAME` varchar(255) DEFAULT NULL COMMENT '收信人',
  `FROM_USERNAME` varchar(255) DEFAULT NULL COMMENT '发信人',
  `SEND_TIME` varchar(100) DEFAULT NULL COMMENT '发信时间',
  `STATUS` varchar(5) DEFAULT NULL COMMENT '状态',
  `SANME_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`FHSMS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `MENU_ID` int(11) NOT NULL,
  `MENU_NAME` varchar(255) DEFAULT NULL,
  `MENU_URL` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `MENU_ORDER` varchar(100) DEFAULT NULL,
  `MENU_ICON` varchar(60) DEFAULT NULL,
  `MENU_TYPE` varchar(10) DEFAULT NULL,
  `MENU_STATE` int(1) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(100) NOT NULL,
  `ROLE_NAME` varchar(100) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `ADD_QX` varchar(255) DEFAULT NULL,
  `DEL_QX` varchar(255) DEFAULT NULL,
  `EDIT_QX` varchar(255) DEFAULT NULL,
  `CHA_QX` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_role_fhbutton` */

DROP TABLE IF EXISTS `sys_role_fhbutton`;

CREATE TABLE `sys_role_fhbutton` (
  `RB_ID` varchar(100) NOT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `BUTTON_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`RB_ID`),
  KEY `角色表外键` (`ROLE_ID`) USING BTREE,
  KEY `fbutton` (`BUTTON_ID`),
  CONSTRAINT `fbutton` FOREIGN KEY (`BUTTON_ID`) REFERENCES `sys_fhbutton` (`FHBUTTON_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `frole` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(15) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `SKIN` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_pictures` */

DROP TABLE IF EXISTS `tb_pictures`;

CREATE TABLE `tb_pictures` (
  `PICTURES_ID` varchar(100) NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL COMMENT '标题',
  `NAME` varchar(255) DEFAULT NULL COMMENT '文件名',
  `PATH` varchar(255) DEFAULT NULL COMMENT '路径',
  `CREATETIME` varchar(100) DEFAULT NULL COMMENT '创建时间',
  `MASTER_ID` varchar(255) DEFAULT NULL COMMENT '属于',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`PICTURES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `weixin_command` */

DROP TABLE IF EXISTS `weixin_command`;

CREATE TABLE `weixin_command` (
  `COMMAND_ID` varchar(100) NOT NULL,
  `KEYWORD` varchar(255) DEFAULT NULL COMMENT '关键词',
  `COMMANDCODE` varchar(255) DEFAULT NULL COMMENT '应用路径',
  `CREATETIME` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `STATUS` int(1) NOT NULL COMMENT '状态',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`COMMAND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `weixin_imgmsg` */

DROP TABLE IF EXISTS `weixin_imgmsg`;

CREATE TABLE `weixin_imgmsg` (
  `IMGMSG_ID` varchar(100) NOT NULL,
  `KEYWORD` varchar(255) DEFAULT NULL COMMENT '关键词',
  `CREATETIME` varchar(100) DEFAULT NULL COMMENT '创建时间',
  `STATUS` int(11) NOT NULL COMMENT '状态',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  `TITLE1` varchar(255) DEFAULT NULL COMMENT '标题1',
  `DESCRIPTION1` varchar(255) DEFAULT NULL COMMENT '描述1',
  `IMGURL1` varchar(255) DEFAULT NULL COMMENT '图片地址1',
  `TOURL1` varchar(255) DEFAULT NULL COMMENT '超链接1',
  `TITLE2` varchar(255) DEFAULT NULL COMMENT '标题2',
  `DESCRIPTION2` varchar(255) DEFAULT NULL COMMENT '描述2',
  `IMGURL2` varchar(255) DEFAULT NULL COMMENT '图片地址2',
  `TOURL2` varchar(255) DEFAULT NULL COMMENT '超链接2',
  `TITLE3` varchar(255) DEFAULT NULL COMMENT '标题3',
  `DESCRIPTION3` varchar(255) DEFAULT NULL COMMENT '描述3',
  `IMGURL3` varchar(255) DEFAULT NULL COMMENT '图片地址3',
  `TOURL3` varchar(255) DEFAULT NULL COMMENT '超链接3',
  `TITLE4` varchar(255) DEFAULT NULL COMMENT '标题4',
  `DESCRIPTION4` varchar(255) DEFAULT NULL COMMENT '描述4',
  `IMGURL4` varchar(255) DEFAULT NULL COMMENT '图片地址4',
  `TOURL4` varchar(255) DEFAULT NULL COMMENT '超链接4',
  `TITLE5` varchar(255) DEFAULT NULL COMMENT '标题5',
  `DESCRIPTION5` varchar(255) DEFAULT NULL COMMENT '描述5',
  `IMGURL5` varchar(255) DEFAULT NULL COMMENT '图片地址5',
  `TOURL5` varchar(255) DEFAULT NULL COMMENT '超链接5',
  `TITLE6` varchar(255) DEFAULT NULL COMMENT '标题6',
  `DESCRIPTION6` varchar(255) DEFAULT NULL COMMENT '描述6',
  `IMGURL6` varchar(255) DEFAULT NULL COMMENT '图片地址6',
  `TOURL6` varchar(255) DEFAULT NULL COMMENT '超链接6',
  `TITLE7` varchar(255) DEFAULT NULL COMMENT '标题7',
  `DESCRIPTION7` varchar(255) DEFAULT NULL COMMENT '描述7',
  `IMGURL7` varchar(255) DEFAULT NULL COMMENT '图片地址7',
  `TOURL7` varchar(255) DEFAULT NULL COMMENT '超链接7',
  `TITLE8` varchar(255) DEFAULT NULL COMMENT '标题8',
  `DESCRIPTION8` varchar(255) DEFAULT NULL COMMENT '描述8',
  `IMGURL8` varchar(255) DEFAULT NULL COMMENT '图片地址8',
  `TOURL8` varchar(255) DEFAULT NULL COMMENT '超链接8',
  PRIMARY KEY (`IMGMSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `weixin_textmsg` */

DROP TABLE IF EXISTS `weixin_textmsg`;

CREATE TABLE `weixin_textmsg` (
  `TEXTMSG_ID` varchar(100) NOT NULL,
  `KEYWORD` varchar(255) DEFAULT NULL COMMENT '关键词',
  `CONTENT` varchar(255) DEFAULT NULL COMMENT '内容',
  `CREATETIME` varchar(100) DEFAULT NULL COMMENT '创建时间',
  `STATUS` int(2) DEFAULT NULL COMMENT '状态',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`TEXTMSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
