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

CREATE TABLE `app_download` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `app_id` int(11) DEFAULT NULL COMMENT '应用id',
  `download_time` int(11) DEFAULT NULL COMMENT '下载时间',
  PRIMARY KEY (`id`),
  KEY `idx_app` (`app_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='应用下载表';


alter table Answer add index idx_user(user_id);
alter table Answer add index idx_problem(problem_id);

alter table Concern add index idx_user(user_id);
alter table Concern add index idx_problem(problem_id);


alter table `Comment`  add index idx_from_user (from_userid);
alter table `Comment`  add index idx_to_user (to_userid);
alter table `Comment`  add index idx_answer(answer_id);
alter table `Comment`  add index idx_comment (comment_id);
alter table `Comment`  add index idx_problem (problem_id);


alter table Agree add index idx_user(user_id);
alter table Agree add index idx_answer(answer_id);
