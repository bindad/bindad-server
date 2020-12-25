create table tbl_user (
	id bigint not null auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    authorities varchar(255) null,
    is_deleted boolean not null,
    updated_by bigint not null,
    updated_time datetime not null,
    unique(username),
    primary key (id)
);