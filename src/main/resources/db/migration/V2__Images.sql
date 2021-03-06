CREATE SEQUENCE image_details_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE image_details (
  id int not null default nextval('image_details_id_seq'),
  name varchar(255) not null,
  user_id int not null,
  CONSTRAINT PK_IMAGE_DETAILS PRIMARY KEY (id)
);

CREATE SEQUENCE image_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE image (
  id int not null default nextval('image_id_seq'),
  image_details int not null,
  image_type varchar(255) not null,
  bytes bytea not null,
  CONSTRAINT PK_IMAGE PRIMARY KEY (id)
)
