drop table if exists relationship;
CREATE TABLE "relationship" (
  pk_relationship integer primary key autoincrement,
  fk_activity integer ,
  fk_user1 integer,
  fk_user2 integer
);


DROP TABLE IF EXISTS user;
CREATE TABLE "user"(
  pk_user integer,
  user_id text,
  user_name text,
  user_type text
);

DROP TABLE IF EXISTS activity;
CREATE TABLE "activity" (
  pk_activity integer,
  activity_organizer text,
  activity_industry text,
  ts DATETIME DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime')),

);


