ALTER TABLE users_roles DROP CONSTRAINT fk_users_roles_users;
ALTER TABLE users_roles DROP CONSTRAINT fk_users_roles_roles;

DROP TABLE users_roles;
DROP TABLE users CASCADE;
DROP TABLE roles CASCADE;

DROP SEQUENCE user_id_seq;
DROP SEQUENCE role_id_seq;