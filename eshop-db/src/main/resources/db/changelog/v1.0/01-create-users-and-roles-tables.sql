CREATE SEQUENCE user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE SEQUENCE role_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE users
(
    id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    middle_name character varying(255),
    last_name character varying(255) NOT NULL,
    phone character varying(20) NOT NULL,
    email character varying(255),
    blocked boolean DEFAULT false,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id integer NOT NULL DEFAULT nextval('role_id_seq'::regclass),
    name character varying(255) NOT NULL,
    CONSTRAINT roles_pk PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT users_roles_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users_roles_users FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_users_roles_roles FOREIGN KEY (role_id)
        REFERENCES roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);