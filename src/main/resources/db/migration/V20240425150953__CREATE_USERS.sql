CREATE TABLE user_
(
    id              int                 not null auto_increment primary key,
    password        varchar(255),
    title           varchar(255),
    username        varchar(255) unique not null,
    active          bit default 1,
    deleted         bit default 0,
    last_login_date datetime,
    created_user    int,
    created_date    datetime,
    modified_user   int,
    modified_date   datetime
);

CREATE UNIQUE INDEX usernameIndex
    ON user_ (username);

create table role
(
    id           int          not null auto_increment primary key,
    title        nvarchar(255),
    name         varchar(255) not null,
    created_date datetime default CURRENT_TIMESTAMP
);

create table user_role
(
    user_id int,
    role_id int,
    constraint unique UK__USER_PER_AUTHORITY (user_id, role_id)
);
