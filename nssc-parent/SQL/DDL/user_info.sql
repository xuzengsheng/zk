CREATE DATABASE `nccs_user` /*!40100 DEFAULT CHARACTER SET utf8 */;


-- nccs_user.user_info definition

CREATE TABLE `user_info` (
  `id` bigint(20) unsigned NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `user_code` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `age` int(11) NOT NULL,
  `sex` int(11) NOT NULL COMMENT '性别  0：女 ，1： 男'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

