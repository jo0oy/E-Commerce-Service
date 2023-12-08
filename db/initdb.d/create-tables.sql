
create table member (
                        id bigint not null auto_increment,
                        member_token varchar(100) not null,
                        created_at datetime(6),
                        last_modified_at datetime(6),
                        deleted tinyint(1) not null,
                        deleted_at datetime(6),
                        email varchar(100) not null,
                        password varchar(100) not null,
                        phone_number varchar(60) not null,
                        role varchar(255),
                        username varchar(50) not null,
                        membership_id bigint not null,
                        primary key (id),
                        unique index idx_username(username),
                        unique index idx_member_token(member_token)
) engine=InnoDB;

create table membership (
                        id bigint not null auto_increment,
                        created_at datetime(6),
                        last_modified_at datetime(6),
                        total_spending integer,
                        grade varchar(255),
                        primary key (id)
) engine=InnoDB;

create table item (
                      id bigint not null auto_increment,
                      created_at datetime(6),
                      last_modified_at datetime(6),
                      item_name varchar(255) not null,
                      item_price integer not null,
                      status varchar(255),
                      stock_quantity integer not null,
                      primary key (id)
) engine=InnoDB;

create table item_option_group (
                                   id bigint not null auto_increment,
                                   created_at datetime(6),
                                   last_modified_at datetime(6),
                                   item_option_group_name varchar(255),
                                   ordering integer,
                                   item_id bigint,
                                   primary key (id)
) engine=InnoDB;

create table item_option (
                             id bigint not null auto_increment,
                             created_at datetime(6),
                             last_modified_at datetime(6),
                             item_option_name varchar(100) not null,
                             item_option_price bigint,
                             ordering integer,
                             item_option_group_id bigint not null,
                             primary key (id)
) engine=InnoDB;
