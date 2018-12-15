CREATE SEQUENCE USER_ID_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE USER (
  id int not null default user_id_seq.nextval,
  username varchar(255) not null,
  password varchar(255) not null,
  email varchar(255) not null,
  active int(2),
  CONSTRAINT PK_USER PRIMARY KEY (id)
);

CREATE SEQUENCE ROLES_ID_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE ROLE (
  id int not null default roles_id_seq.nextval,
  name varchar(30) not null,
  CONSTRAINT PK_ROLE PRIMARY KEY (id)
);

CREATE TABLE USER_ROLES (
  user_id int not null,
  roles_id int not null,
  CONSTRAINT PK_USER_ROLES PRIMARY KEY (user_id, roles_id)
);

INSERT INTO ROLE (id, name) VALUES (roles_id_seq.nextval, 'user');