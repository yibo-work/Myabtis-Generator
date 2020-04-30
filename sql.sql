create database lost;
use lost;
ALTER DATABASE lost CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
drop table base_user;
create table base_user
(
    id       bigint primary key auto_increment comment '标识列',
    nickname varchar(20) comment '用户姓名',
    account  varchar(20) not null comment '登陆账号',
    password varchar(20) not null comment '密码',
    role_id  int         not null comment '角色id 1.学生 2.超级管理员',
    phone    varchar(13) comment '手机号码',
    is_del   tinyint default 0 comment '0未删除 1.删除'
) comment '用户表';

drop table lost;

create table lost
(
    id          bigint primary key auto_increment comment '标识列',
    lost_no     varchar(255) not null comment '失物编号',
    lost_name   varchar(20)  not null comment '失物名字',
    address     varchar(20)  not null comment '遗失地点',
    status      tinyint default 0 comment '借阅状态 o.未认领 1。已认领',
    create_time bigint       not null comment '创建时间',
    is_del      tinyint default 0 comment '0未删除 1.删除'
) comment '失物表';

drop table apply_record;

create table apply_record
(
    id          bigint primary key auto_increment comment '标识列',
    lost_no     varchar(255) not null comment '失物编号',
    lost_id     bigint       not null comment '失物id',
    user_id     bigint       not null comment '用户id',
    user_name   varchar(255) not null comment '申请人',
    user_phone  varchar(255) default '' comment '手机号码',
    lost_name   varchar(255) default '' comment '失物名',
    description varchar(255) not null comment '失物描述',
    status      tinyint      default 0 comment '申请状态 0.申请中 1同意 2.已拒绝',
    create_time bigint       not null comment '创建时间',
    is_del      tinyint      default 0 comment '0未删除 1.删除'
) comment '认领记录';

create table comment_record
(
    id          bigint primary key auto_increment comment '标识列',
    user_id     bigint not null comment '用户Id',
    user_name   varchar(255)       not null comment '用户名',
    content   varchar(255) default '' comment '内容',
    create_time bigint       not null comment '创建时间',
    is_del      tinyint      default 0 comment '0未删除 1.删除'
) comment '评论记录';

