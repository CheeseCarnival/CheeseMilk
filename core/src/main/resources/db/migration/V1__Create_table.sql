create table tbl_course (
                            id bigint not null,
                            created_at datetime(6),
                            remark varchar(255),
                            updated_at datetime(6),
                            class_address varchar(255),
                            class_name varchar(255),
                            length integer not null,
                            semester varchar(255),
                            start integer not null,
                            teacher_name varchar(255),
                            week_num varchar(255),
                            user_id bigint,
                            primary key (id)
) engine=InnoDB;

create table tbl_role (
                          id bigint not null,
                          created_at datetime(6),
                          remark varchar(255),
                          updated_at datetime(6),
                          name varchar(255),
                          permissions integer not null,
                          primary key (id)
) engine=InnoDB;

create table tbl_user (
                          id bigint not null,
                          created_at datetime(6),
                          remark varchar(255),
                          updated_at datetime(6),
                          avatar_url varchar(255),
                          bio varchar(255),
                          birth datetime(6),
                          email varchar(255),
                          gender integer,
                          location varchar(255),
                          nickname varchar(255),
                          status integer,
                          username varchar(255),
                          primary key (id)
) engine=InnoDB;

create table tbl_user_auth (
                               id bigint not null,
                               created_at datetime(6),
                               remark varchar(255),
                               updated_at datetime(6),
                               bind_flag bit,
                               credential varchar(255),
                               identifier varchar(255),
                               identify_type varchar(255),
                               user_id bigint,
                               primary key (id)
) engine=InnoDB;

create table tbl_user_role (
                               user_id bigint not null,
                               role_id bigint not null,
                               primary key (user_id, role_id)
) engine=InnoDB;

alter table tbl_user_role
    add constraint UK_pbcxgcpq15mloohm7bojr9x5n unique (role_id);

alter table tbl_course
    add constraint FK3x5hrlih7dd16fsvj7a1arq8d
        foreign key (user_id)
            references tbl_user (id);

alter table tbl_user_auth
    add constraint FKd712jmt82f3w4mf8v7cnbtpyy
        foreign key (user_id)
            references tbl_user (id);

alter table tbl_user_role
    add constraint FK6phlytlf1w3h9vutsu019xor5
        foreign key (role_id)
            references tbl_role (id);

alter table tbl_user_role
    add constraint FKggc6wjqokl2vlw89y22a1j2oh
        foreign key (user_id)
            references tbl_user (id);