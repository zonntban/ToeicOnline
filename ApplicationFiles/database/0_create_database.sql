CREATE  DATABASE toeic_online;
ALTER DATABASE toeic_online CHARACTER SET utf8;

use toeic_online;

create table user_account(
  id bigint not null PRIMARY KEY AUTO_INCREMENT,
  account varchar(255) not null,
  password VARCHAR(255) not null,
  fullName VARCHAR(255) NULL ,
  create_date TIMESTAMP NULL
);

CREATE TABLE role(
  id bigint not null PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NULL
);

ALTER TABLE user_account
ADD COLUMN role_id BIGINT NOT NULL
;

ALTER TABLE user_account ADD CONSTRAINT fk_user_role FOREIGN KEY (role_id)
REFERENCES role(id)
;

CREATE TABLE thread(
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(500) NULL ,
  image VARCHAR(500) NULL,
  content VARCHAR(2000) NULL,
  create_date TIMESTAMP NULL ,
  modified_date TIMESTAMP NULL ,
  user_id BIGINT NOT NULL
);

ALTER TABLE thread ADD CONSTRAINT fk_thread_creator FOREIGN KEY (user_id)
REFERENCES user_account(id)
;

CREATE TABLE comment(
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  content VARCHAR(2000) NULL,
  create_date TIMESTAMP NULL ,
  modified_date TIMESTAMP NULL ,
  user_id BIGINT NOT NULL,
  thread_id BIGINT NOT NULL
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_creator FOREIGN KEY (user_id)
REFERENCES user_account(id)
;

ALTER TABLE comment ADD CONSTRAINT fk_comment_thread FOREIGN KEY (thread_id)
REFERENCES thread(id)
;

