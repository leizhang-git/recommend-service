create database volunteer_Administration;
use `volunteer_Administration`;

create table if not exists `major_info` (
    `major_id`       varchar(255) not null comment '专业Id',
    `major_name`     varchar(255) not null comment '专业名称',
    `major_code`     varchar(25)  not null comment '专业编号',
    `major_category` varchar(255) not null comment '专业类别',
    `enroll_person`  varchar(25)  not null comment '招生人数',
    primary key (major_id)
    )engine = InnoDB default charset = utf8;

select * from major_info;

create table if not exists `person` (
    id       varchar(255)               not null comment '用户登陆Id',
    name     varchar(255) default '无名氏' not null comment '用户姓名',
    score    varchar(25)                not null comment '高考估分成绩',
    password varchar(255)               not null comment '用户登陆密码',
    role     varchar(22)                not null comment '用户角色'
    )engine = Innodb default charset = utf8;

select * from person;

create table if not exists `university_info` (
    university_id   varchar(255) not null comment '高校编号',
    university_name varchar(255) not null comment '高校名称',
    university_code varchar(25)  not null comment '高校代码',
    primary key (university_id)
    )engine = Innodb default charset = utf8;

select * from university_info;


create table if not exists `university_major_info` (
    `university_id` varchar(255) not null comment '高校编号',
    `major_id` varchar(255) not null comment '专业编号'
    )engine = InnoDB default charset = utf8;

select * from university_major_info;