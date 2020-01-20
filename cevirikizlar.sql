create table tb_ceviri
(
    id          varchar(20)  not null comment 'ID'
        primary key,
    user_id     varchar(20)  null comment '用户ID',
    metin_id    varchar(20)  null comment '原文id',
    title       varchar(100) null comment '译文标题',
    content     mediumtext   null comment '译文正文',
    create_time datetime     null comment '创建日期',
    update_time datetime     null comment '修改日期',
    status      tinyint(2)   null comment '审核状态',
    url         varchar(100) null comment 'URL',
    delete_flag tinyint(1)   null,
    language    varchar(20)  null comment '语言'
)
    comment '原文';

create table tb_file
(
    id          varchar(20)  not null comment 'ID'
        primary key,
    file_name   varchar(120) null comment 'tag名称',
    state       tinyint(2)   null comment 'tag颜色',
    url         varchar(200) null comment 'tag颜色',
    path        varchar(200) null comment 'tag颜色',
    suffix      varchar(20)  null comment 'tag颜色',
    create_time datetime     null comment '创建日期',
    update_time datetime     null comment '修改日期',
    delete_flag tinyint(1)   null
)
    comment '文件';

create table tb_menu
(
    id           varchar(20)             not null comment '主键'
        primary key,
    description  mediumtext              null comment '菜单描述',
    name         varchar(255)            null comment '菜单名称',
    pid          varchar(64) default '0' null comment '主键',
    path         varchar(120)            null,
    url          varchar(255)            null comment '链接',
    icon         varchar(20)             null comment '权限代码',
    sort         int(20)                 null comment '排序',
    status       tinyint(2)              null comment '状态',
    code         varchar(20)             null,
    update_time  datetime                null,
    create_time  datetime                null,
    delete_flag  tinyint(2)              null,
    hide_In_menu tinyint(1)  default 0   not null,
    type         tinyint(2)  default 1   not null
);

INSERT INTO cevirikizlar.tb_menu (id, description, name, pid, path, url, icon, sort, status, code, update_time, create_time, delete_flag, hide_In_menu, type) VALUES ('1', 'admin', 'articlemange', '0', '/articlemange', null, 'smile', 1, null, 'articlemange', '2020-01-19 15:02:31', '2020-01-18 20:33:55', 0, 0, 1);
INSERT INTO cevirikizlar.tb_menu (id, description, name, pid, path, url, icon, sort, status, code, update_time, create_time, delete_flag, hide_In_menu, type) VALUES ('1218796447609196545', null, 'articleInfo', '1', '/articlemange/article/articleInfo', null, null, 12, null, 'articleInfo', '2020-01-19 08:21:41', '2020-01-19 07:24:52', 0, 0, 1);
INSERT INTO cevirikizlar.tb_menu (id, description, name, pid, path, url, icon, sort, status, code, update_time, create_time, delete_flag, hide_In_menu, type) VALUES ('1218799109645914113', null, 'article', '1', '/articlemange/article', null, null, 6, null, 'article', '2020-01-19 08:20:58', '2020-01-19 07:35:27', 0, 0, 1);
INSERT INTO cevirikizlar.tb_menu (id, description, name, pid, path, url, icon, sort, status, code, update_time, create_time, delete_flag, hide_In_menu, type) VALUES ('1218799238121639938', null, 'taglist', '1', '/articlemange/taglist', null, null, 4, null, 'taglist', null, '2020-01-19 07:35:57', 0, 0, 1);
INSERT INTO cevirikizlar.tb_menu (id, description, name, pid, path, url, icon, sort, status, code, update_time, create_time, delete_flag, hide_In_menu, type) VALUES ('1218800616831307778', null, 'menu', '0', '/menu', null, null, 21, null, 'menu', null, '2020-01-19 07:41:26', 0, 0, 1);
INSERT INTO cevirikizlar.tb_menu (id, description, name, pid, path, url, icon, sort, status, code, update_time, create_time, delete_flag, hide_In_menu, type) VALUES ('1218801372984627202', null, 'system', '0', '/system', null, null, 20, null, 'system', '2020-01-19 08:14:19', '2020-01-19 07:44:26', 0, 0, 1);
INSERT INTO cevirikizlar.tb_menu (id, description, name, pid, path, url, icon, sort, status, code, update_time, create_time, delete_flag, hide_In_menu, type) VALUES ('1218801806470139905', null, 'system set', '1218801372984627202', '/system/system', null, null, 56, null, 'system set', null, '2020-01-19 07:46:10', 0, 0, 1);
INSERT INTO cevirikizlar.tb_menu (id, description, name, pid, path, url, icon, sort, status, code, update_time, create_time, delete_flag, hide_In_menu, type) VALUES ('1218802801082224642', null, 'userList', '1218801372984627202', '/system/userList', null, null, 32, null, 'userList', null, '2020-01-19 07:50:07', 0, 0, 1);
INSERT INTO cevirikizlar.tb_menu (id, description, name, pid, path, url, icon, sort, status, code, update_time, create_time, delete_flag, hide_In_menu, type) VALUES ('1218805779236257793', null, 'profile', '1218801372984627202', '/system/profil', null, null, 32, null, 'profile', null, '2020-01-19 08:01:57', 0, 0, 1);
INSERT INTO cevirikizlar.tb_menu (id, description, name, pid, path, url, icon, sort, status, code, update_time, create_time, delete_flag, hide_In_menu, type) VALUES ('1218806367747440642', null, '权限', '0', '/permission', null, null, 32, null, '权限', null, '2020-01-19 08:04:17', 0, 0, 1);
INSERT INTO cevirikizlar.tb_menu (id, description, name, pid, path, url, icon, sort, status, code, update_time, create_time, delete_flag, hide_In_menu, type) VALUES ('1218806467391520769', null, '角色', '0', '/role', null, null, 212, null, '角色', null, '2020-01-19 08:04:41', 0, 0, 1);


create table tb_menu_user
(
    id      int auto_increment
        primary key,
    menu_id varchar(20) not null,
    user_id varchar(20) null
);

create table tb_metin
(
    id          varchar(20)  not null comment 'ID'
        primary key,
    user_id     varchar(20)  null comment '用户ID',
    title       varchar(100) null comment '标题',
    content     mediumtext   null comment '文章正文',
    create_time datetime     null comment '创建日期',
    update_time datetime     null comment '修改日期',
    status      tinyint(2)   null comment '审核状态',
    url         varchar(100) null comment 'URL',
    metin_type  varchar(64)  null comment '文章类型',
    delete_flag tinyint(1)   null,
    language    varchar(20)  null comment '原文语言',
    to_language varchar(200) null comment '要翻译的语言',
    tag_id      varchar(20)  null comment '标签id',
    mark        varchar(200) null
)
    comment '原文';

create table tb_metin_type
(
    id          varchar(20)  not null comment '类型编号'
        primary key,
    type_name   varchar(40)  null comment '问题ID',
    type_info   varchar(120) null comment '说明',
    pid         varchar(20)  not null comment '父id',
    create_time datetime     null comment '修改日期',
    update_time datetime     null comment '修改日期',
    delete_flag tinyint      null
)
    comment '回答';

INSERT INTO cevirikizlar.tb_metin_type (id, type_name, type_info, pid, create_time, update_time, delete_flag) VALUES ('1', 'dsada', 'asdasda', '0', '2020-01-13 20:44:36', '2020-01-13 20:44:38', 0);


create table tb_permission
(
    id          varchar(20)             not null comment '主键'
        primary key,
    description mediumtext              null comment '权限描述',
    name        varchar(255)            null comment '权限名称',
    type        tinyint                 null comment '权限类型 1为菜单 2为功能 3为API',
    pid         varchar(64) default '0' null comment '主键',
    level       varchar(2)              null comment '权限等级，1为通用接口权限，2为需校验接口权限',
    path        varchar(120)            null,
    method      varchar(255)            null comment '请求类型',
    url         varchar(255)            null comment '链接',
    icon        varchar(20)             null comment '权限代码',
    sort        int(20)                 null comment '排序',
    status      tinyint(2)              null comment '状态',
    code        varchar(20)             null,
    update_time datetime                null,
    create_time datetime                null,
    delete_flag tinyint(2)              null
);

INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218521578103455748', null, '字典查询', 1, '0', null, null, null, '/api/ceviri-kizlar/sozluk/', null, null, null, null, null, '2020-01-18 13:12:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218521578103455754', null, '标签管理', 1, '0', null, null, null, '/api/ceviri-kizlar/tag/', null, null, null, null, null, '2020-01-18 13:12:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218521578103455759', null, '文件管理', 1, '0', null, null, null, '/api/ceviri-kizlar/file/', null, null, null, null, null, '2020-01-18 13:12:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218521578103455764', null, '原文管理', 1, '0', null, null, null, '/api/ceviri-kizlar/metin/', null, null, null, null, null, '2020-01-18 13:12:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218521578103455766', null, '角色管理', 1, '0', null, null, null, '/api/ceviri-kizlar/role/', null, null, null, null, null, '2020-01-18 13:12:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218521578103455777', null, '用户管理', 1, '0', null, null, null, '/api/ceviri-kizlar/user/', null, null, null, null, null, '2020-01-18 13:12:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218521578103455785', null, '权限管理', 1, '0', null, null, null, '/api/ceviri-kizlar/permission/', null, null, null, null, null, '2020-01-18 13:12:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218521578103455787', null, '菜单管理', 1, '0', null, null, null, '/api/cevir-kizar/menu/', null, null, null, null, null, '2020-01-18 13:12:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218521578103455789', null, '验证码', 1, '0', null, null, null, '/api/ceviri-kizlar/code/', null, null, null, null, null, '2020-01-18 13:12:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218521578103455795', null, '译文管理', 1, '0', null, null, null, '/api/ceviri-kizlar/ceviri/', null, null, null, null, null, '2020-01-18 13:12:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218522107911180290', null, '关键字搜素', 2, '1218521578103455748', null, null, 'GET', '/sozluk/en/list/{keyword}', null, null, null, null, null, '2020-01-18 13:14:44', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218522107911180291', null, '查询单词', 2, '1218522107911180290', null, null, 'GET', '/sozluk/en/{keyword}', null, null, null, null, null, '2020-01-18 13:14:44', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218522107911180292', null, '获取单个tag', 2, '1218521578103455754', null, null, 'GET', '/tag/{id}', null, null, null, null, null, '2020-01-18 13:14:44', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218522107911180293', null, '分页获取tag', 2, '1218521578103455754', null, null, 'GET', '/tag/{page}/{size}', null, null, null, null, null, '2020-01-18 13:14:44', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218522589413060610', null, '新增', 2, '1218521578103455754', null, null, 'POST', '/tag', null, null, null, null, null, '2020-01-18 13:16:39', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218522589413060611', null, '修改', 2, '1218521578103455754', null, null, 'PUT', '/tag', null, null, null, null, null, '2020-01-18 13:16:39', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218522589413060612', null, '删除', 2, '1218521578103455754', null, null, 'DELETE', '/tag/{id}', null, null, null, null, null, '2020-01-18 13:16:39', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218522589413060613', null, '获取文件', 2, '1218521578103455759', null, null, 'GET', '/file/{id}', null, null, null, null, null, '2020-01-18 13:16:39', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218522589413060614', null, '只读文件', 2, '1218521578103455759', null, null, 'GET', '/file/read-file/{id}', null, null, null, null, null, '2020-01-18 13:16:39', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218522589413060615', null, '读译文', 2, '1218521578103455759', null, null, 'GET', '/file/file-ceviri/{id}', null, null, null, null, null, '2020-01-18 13:16:39', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218522589413060616', null, '文件上传', 2, '1218521578103455759', null, null, 'POST', '/file/fileUpload', null, null, null, null, null, '2020-01-18 13:16:39', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523574692847617', null, '获取单个译文', 2, '1218521578103455764', null, null, 'GET', '/metin/{id}', null, null, null, null, null, '2020-01-18 13:20:34', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523574692847640', null, '测试', 2, '1218521578103455795', null, null, 'PUT', '/ceviri/sda', null, null, null, null, null, '2020-01-18 13:20:34', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523574692847641', null, '开始翻译译文', 2, '1218521578103455795', null, null, 'GET', '/ceviri/{id}', null, null, null, null, null, '2020-01-18 13:20:34', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523574692847642', null, '分页获取译文', 2, '1218521578103455795', null, null, 'POST', '/ceviri', null, null, null, null, null, '2020-01-18 13:20:34', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523574692847643', null, '译文操作', 2, '1218521578103455795', null, null, 'GET', '/ceviri/{id}/{active}', null, null, null, null, null, '2020-01-18 13:20:34', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523574692847644', null, '译文保存', 2, '1218521578103455795', null, null, 'PUT', '/ceviri', null, null, null, null, null, '2020-01-18 13:20:34', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002113', null, '修改', 2, '1218521578103455764', null, null, 'PUT', '/metin', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002114', null, '分页查看原文', 2, '1218521578103455764', null, null, 'GET', '/metin', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002115', null, '添加原文', 2, '1218521578103455764', null, null, 'POST', '/metin', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002116', null, '修改或添加文章类型', 2, '1218521578103455764', null, null, 'PUT', '/metin/type', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002117', null, '获取单个文章类型', 2, '1218521578103455764', null, null, 'GET', '/metin/type/{id}', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002118', null, '获取文章类型树形结构', 2, '1218521578103455764', null, null, 'GET', '/metin/Tree', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002119', null, '新增角色', 2, '1218521578103455766', null, null, 'POST', '/role', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002120', null, '新增用户', 2, '1218521578103455777', null, null, 'POST', '/user', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002121', null, '注册', 2, '1218521578103455777', null, null, 'POST', '/user/register/{code}', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002122', null, '修改', 2, '1218521578103455777', null, null, 'PUT', '/user/{id}', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002123', null, '删除', 2, '1218521578103455777', null, null, 'DELETE', '/user/{id}', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002124', null, '退出', 2, '1218521578103455777', null, null, 'GET', '/user/logout', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002125', null, '登录', 2, '1218521578103455777', null, null, 'POST', '/user/login', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002126', null, '获取菜用户单', 2, '1218521578103455787', null, null, 'GET', '/user/menu', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002127', null, '获取个人信息', 2, '1218521578103455777', null, null, 'GET', '/user/{id}', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002128', null, '获取用户列表', 2, '1218521578103455777', null, null, 'POST', '/user/userlist', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002129', null, '获取用户信息', 2, '1218521578103455777', null, null, 'GET', '/user/info', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002130', null, '根据ID获取权限', 2, '1218521578103455785', null, null, 'GET', '/permission/{id}', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002131', null, '获取所以权限列表', 2, '1218521578103455785', null, null, 'GET', '/permission/all', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218523731111002132', null, '获取一级菜单', 2, '1218521578103455785', null, null, 'GET', '/permission/mainmenu', null, null, null, null, null, '2020-01-18 13:21:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218524095382126593', null, '获取菜单树形结构', 2, '1218521578103455787', null, null, 'GET', '/menu', null, null, null, null, null, '2020-01-18 13:22:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218524095382126594', null, '获取验证码', 2, '1218521578103455789', null, null, 'GET', '/code', null, null, null, null, null, '2020-01-18 13:22:38', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218547389906018305', null, '获取菜单列表', 2, '1218524095382126593', null, null, 'GET', '/menuList', null, null, null, null, null, '2020-01-18 14:55:12', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218738396386627586', null, '权限树', 2, '1218521578103455785', null, null, 'GET', '/permission/tree', null, null, null, null, null, '2020-01-19 03:34:11', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218795033730289665', null, '添加或修改菜单', 2, '1218521578103455787', null, null, 'PUT', '/menu', null, null, null, null, null, '2020-01-19 07:19:15', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218798009937096706', null, '删除菜单', 2, '1218521578103455787', null, null, 'DELETE', '/menu/{id}', null, null, null, null, null, '2020-01-19 07:31:04', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218832554912710657', null, '角色分页', 2, '1218521578103455766', null, null, 'GET', '/role', null, null, null, null, null, '2020-01-19 09:48:21', 0);
INSERT INTO cevirikizlar.tb_permission (id, description, name, type, pid, level, path, method, url, icon, sort, status, code, update_time, create_time, delete_flag) VALUES ('1218863320916893698', null, '角色分页', 2, '1218521578103455766', null, null, 'GET', '/role/{id}', null, null, null, null, null, '2020-01-19 11:50:36', 0);




create table tb_role
(
    id          varchar(20)  not null comment '主键ID'
        primary key,
    role_name   varchar(40)  null comment '权限名称',
    description varchar(255) null comment '说明',
    update_time datetime     null,
    create_time datetime     null,
    delete_flag tinyint(2)   null,
    name_zh     varchar(64)  null comment '角色名称'
);

INSERT INTO cevirikizlar.tb_role (id, role_name, description, update_time, create_time, delete_flag, name_zh) VALUES ('1', 'ROLE_admin', 'admin', '2020-01-11 19:22:19', '2020-01-11 19:22:22', 0, null);
INSERT INTO cevirikizlar.tb_role (id, role_name, description, update_time, create_time, delete_flag, name_zh) VALUES ('1215244358282989569', 'ROLE_user', '基础用户', null, '2020-01-09 12:10:08', 0, null);


create table tb_role_menu
(
    id      int auto_increment
        primary key,
    role_id varchar(20) not null,
    menu_id varchar(20) null
);

INSERT INTO cevirikizlar.tb_role_menu (id, role_id, menu_id) VALUES (1, '1', '1');
INSERT INTO cevirikizlar.tb_role_menu (id, role_id, menu_id) VALUES (2, '1', '1218796447609196545');
INSERT INTO cevirikizlar.tb_role_menu (id, role_id, menu_id) VALUES (3, '1', '1218799109645914113');
INSERT INTO cevirikizlar.tb_role_menu (id, role_id, menu_id) VALUES (4, '1', '1218799238121639938');
INSERT INTO cevirikizlar.tb_role_menu (id, role_id, menu_id) VALUES (5, '1', '1218800616831307778');
INSERT INTO cevirikizlar.tb_role_menu (id, role_id, menu_id) VALUES (6, '1', '1218801372984627202');

create table tb_role_permission
(
    id            int auto_increment,
    role_id       varchar(20) null,
    permission_id varchar(20) null,
    constraint tb_role_permission_id_uindex
        unique (id)
);

alter table tb_role_permission
    add primary key (id);

INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (1, '1', '1218521578103455748');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (2, '1', '1218521578103455754');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (3, '1', '1218521578103455759');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (4, '1', '1218521578103455764');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (5, '1', '1218521578103455766');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (6, '1', '1218521578103455777');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (7, '1', '1218521578103455785');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (8, '1', '1218521578103455787');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (9, '1', '1218521578103455789');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (10, '1', '1218521578103455795');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (11, '1', '1218522107911180290');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (12, '1', '1218522107911180291');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (13, '1', '1218522107911180292');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (14, '1', '1218522107911180293');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (15, '1', '1218522589413060610');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (16, '1', '1218522589413060611');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (17, '1', '1218522589413060612');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (18, '1', '1218522589413060613');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (19, '1', '1218522589413060614');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (20, '1', '1218522589413060615');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (21, '1', '1218522589413060616');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (22, '1', '1218523574692847617');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (23, '1', '1218523574692847640');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (24, '1', '1218523574692847641');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (25, '1', '1218523574692847642');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (26, '1', '1218523574692847643');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (27, '1', '1218523574692847644');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (28, '1', '1218523731111002113');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (29, '1', '1218523731111002114');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (30, '1', '1218523731111002115');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (31, '1', '1218523731111002116');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (32, '1', '1218523731111002117');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (33, '1', '1218523731111002118');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (34, '1', '1218523731111002119');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (35, '1', '1218523731111002120');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (36, '1', '1218523731111002121');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (37, '1', '1218523731111002122');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (38, '1', '1218523731111002123');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (39, '1', '1218523731111002124');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (40, '1', '1218523731111002125');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (41, '1', '1218523731111002126');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (42, '1', '1218523731111002127');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (43, '1', '1218523731111002128');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (44, '1', '1218523731111002129');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (45, '1', '1218523731111002130');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (46, '1', '1218523731111002131');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (47, '1', '1218523731111002132');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (48, '1', '1218524095382126593');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (49, '1', '1218524095382126594');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (50, '1', '1218547389906018305');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (51, '1', '1218738396386627586');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (52, '1', '1218795033730289665');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (53, '1', '1218798009937096706');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (54, '1', '1218832554912710657');
INSERT INTO cevirikizlar.tb_role_permission (id, role_id, permission_id) VALUES (55, '1', '1218863320916893698');



create table tb_tag
(
    id          varchar(20) not null comment 'ID'
        primary key,
    tag_name    varchar(40) null comment 'tag名称',
    tag_color   varchar(20) null comment 'tag颜色',
    create_time datetime    null comment '创建日期',
    update_time datetime    null comment '修改日期',
    delete_flag tinyint(1)  null
)
    comment '标签';

create table tb_user
(
    id              varchar(20)          not null comment 'ID'
        primary key,
    mobile          varchar(100)         null comment '手机号码',
    password        varchar(100)         null comment '密码',
    user_name       varchar(40)          not null comment '用户姓名',
    nick_name       varchar(100)         null comment '昵称',
    sex             varchar(2)           null comment '性别',
    first_name      varchar(20)          null comment '姓',
    last_name       varchar(40)          null comment '名',
    birthday        datetime             null comment '出生年月日',
    last_login_time datetime             null,
    avatar          varchar(100)         null comment '头像',
    email           varchar(100)         null comment 'E-Mail',
    create_time     datetime             null comment '创建时间',
    update_time     datetime             null comment '修改日期',
    delete_flag     tinyint(2) default 0 not null,
    user_status     tinyint(2)           not null,
    regdate         datetime             null comment '注册日期',
    online          bigint               null comment '在线时长（分钟）',
    interest        varchar(100)         null comment '兴趣',
    login_count     bigint               null comment '登录次数',
    personality     varchar(100)         null comment '个性',
    fanscount       int(20)              null comment '粉丝数',
    followcount     int(20)              null comment '关注数'
)
    comment '用户';

INSERT INTO cevirikizlar.tb_user (id, mobile, password, user_name, nick_name, sex, first_name, last_name, birthday, last_login_time, avatar, email, create_time, update_time, delete_flag, user_status, regdate, online, interest, login_count, personality, fanscount, followcount) VALUES ('1215244605885337601', null, '$2a$10$w/cvC0yDyLrC0MOlYJqSnu6CcNEgPfxi.qQ2tNGPUK1rovVwQY47.', 'yefan', null, null, null, null, null, null, null, null, '2020-01-09 12:11:07', null, 0, 1, null, null, null, null, null, null, null);


create table tb_user_role
(
    role_id     varchar(20) not null comment '角色ID',
    user_id     varchar(20) not null comment '权限ID',
    create_time datetime    null comment '修改日期',
    update_time datetime    null comment '修改日期',
    delete_flag tinyint(1)  null,
    id          varchar(20) not null,
    constraint role_id
        unique (role_id, user_id),
    constraint tb_user_role_id_uindex
        unique (id)
);

alter table tb_user_role
    add primary key (id);

INSERT INTO cevirikizlar.tb_user_role (role_id, user_id, create_time, update_time, delete_flag, id) VALUES ('1', '1215244605885337601', '2020-01-11 19:23:16', null, 9, '1');
INSERT INTO cevirikizlar.tb_user_role (role_id, user_id, create_time, update_time, delete_flag, id) VALUES ('1215244358282989569', '1215244605885337601', '2020-01-09 12:11:09', null, 0, '1215244616190742529');


