--liquibase formatted sql

--changeset burhan:1
create table roles (
  id int unsigned not null auto_increment comment 'Role id',
  name varchar(100) not null comment 'Available role name',
  primary key (id),
  unique (name)
);
--rollback drop table roles;

--changeset burhan:2
insert into roles (name) values ('ADMIN'), ('SUBSCRIBER'), ('ANONYMOUS');
--rollback truncate roles;

--changeset burhan:3
create table users (
  id int unsigned not null auto_increment comment 'User id',
  username varchar(100) not null comment 'Username unique to a user',
  password varchar(1000) not null comment 'User\s password',
  role varchar(100) not null comment 'User role',
  primary key (id),
  unique (username),
  foreign key (role) references roles (name)
);
--rollback drop table users;
