drop table if exists relationship;
CREATE TABLE "relationship" (
  pk_relationship integer primary key autoincrement,
  fk_activity integer ,
  fk_user1 integer,
  fk_user2 integer
);


DROP TABLE IF EXISTS user;
CREATE TABLE "user"(
  pk_user integer primary key autoincrement,
  user_id text,
  user_name text,
  user_type text
);

DROP TABLE IF EXISTS activity;
CREATE TABLE "activity" (
  pk_activity integer primary key autoincrement,
  activity_date text,
  activity_name text,
  fk_company integer,
  activity_industry text,
  ts DATETIME DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime'))
);

drop table if exists company;
create table company (
  pk_company integer primary key autoincrement,
  company_name text,
  address text,
  tel text
);

drop table if exists individual;
create table individual (
  pk_individual integer primary key autoincrement
)

