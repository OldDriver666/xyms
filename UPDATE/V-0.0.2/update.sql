CREATE TABLE `complaint_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(128) DEFAULT NULL COMMENT '投诉类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='投诉类型';

CREATE TABLE `complaints` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `plaintiff_id` varchar(32) DEFAULT NULL COMMENT '投诉方id',
  `plaintiff_name` varchar(32) DEFAULT NULL COMMENT '投诉方名称',
  `defendant_id` varchar(32) DEFAULT NULL COMMENT '被投诉方id',
  `defendant_name` varchar(32) DEFAULT NULL COMMENT '被投诉方名称',
  `defendant_type` varchar(32) DEFAULT NULL COMMENT '被投诉方类型(群,个人)',
  `complaint_type` varchar(128) DEFAULT NULL COMMENT '投诉类型',
  `content` varchar(255) DEFAULT NULL COMMENT '投诉内容',
  `picture` varchar(512) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='投诉表';
