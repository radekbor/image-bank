CREATE SEQUENCE user_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE users (
  id int not null default nextval('user_id_seq'),
  username varchar(255) unique not null,
  password varchar(255) not null,
  email varchar(255) not null,
  active BOOLEAN,
  CONSTRAINT PK_USER PRIMARY KEY (id)
);

CREATE SEQUENCE roles_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE role (
  id int not null default  nextval('roles_id_seq'),
  name varchar(30) not null,
  CONSTRAINT PK_ROLE PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  user_id int not null,
  roles_id int not null,
  CONSTRAINT PK_USER_ROLES PRIMARY KEY (user_id, roles_id)
);

INSERT INTO role (name) VALUES ('user');