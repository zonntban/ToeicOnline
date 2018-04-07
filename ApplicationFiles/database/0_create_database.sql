CREATE  DATABASE toeic_online;
ALTER DATABASE toeic_online CHARACTER SET utf8;

use toeic_online;

create table user_account(
  id bigint not null PRIMARY KEY AUTO_INCREMENT,
  account varchar(255) not null,
  password VARCHAR(255) not null,
  fullname VARCHAR(255) NULL ,
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

