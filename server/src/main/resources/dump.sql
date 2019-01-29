-- we don't know how to generate database task-manager (class Database) :(
create table project
(
  id        varchar not null
    constraint project_pkey
    primary key,
  name      varchar,
  author_id varchar
);

create unique index project_id_uindex
  on project (id);

create table assignee
(
  id      varchar not null
    constraint assignee_pkey
    primary key,
  task_id varchar,
  user_id varchar
);

create table session
(
  id           varchar not null
    constraint session_pkey
    primary key,
  user_id      varchar,
  signature    varchar,
  created_date timestamp
);

create table "user"
(
  id            varchar not null
    constraint user_pkey
    primary key,
  login         varchar,
  password_hash varchar,
  name          varchar,
  surname       varchar,
  lastname      varchar,
  role          varchar
);

create unique index user_login_uindex
  on "user" (login);

create table task
(
  id           varchar(255) not null
    constraint task_pkey
    primary key,
  task_content varchar(255),
  priority     integer      not null,
  project_id   varchar(255),
  status       integer,
  term         timestamp
);


