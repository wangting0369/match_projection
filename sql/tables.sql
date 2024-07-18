create table tag
(
    id         bigint auto_increment comment '主键'
        primary key,
    tagName    varchar(256)                       not null comment '标签名',
    userId     bigint                             null comment '上传用户id',
    parentId   bigint                             null comment '父标签',
    isParent   tinyint                            null comment '是否是父标签',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 null comment '逻辑删除'
)
    comment '标签';

create table user
(
    id           bigint auto_increment comment 'id'
        primary key,
    userName     varchar(256)                       null comment '用户名',
    userAccount  varchar(256)                       null comment '账号',
    avaterUrl    varchar(1024)                      null comment '头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮件',
    userStatus   int      default 0                 null comment '账户状态 0正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 null comment '逻辑删除_0正常',
    userRole     int      default 0                 null comment '用户角色，等级'
)
    comment '用户表';

