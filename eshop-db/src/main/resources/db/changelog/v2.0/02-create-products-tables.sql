CREATE SEQUENCE brand_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE SEQUENCE category_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE SEQUENCE product_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE categories
(
    id integer NOT NULL DEFAULT nextval('category_id_seq'::regclass),
	parent_id integer,
	name character varying(255) NOT NULL,
    CONSTRAINT categories_pk PRIMARY KEY (id),
	CONSTRAINT fk_categories_categories FOREIGN KEY (parent_id)
        REFERENCES categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE brands
(
    id integer NOT NULL DEFAULT nextval('brand_id_seq'::regclass),
	name character varying(255) NOT NULL,
    CONSTRAINT brands_pk PRIMARY KEY (id)
);

CREATE TABLE products
(
    id integer NOT NULL DEFAULT nextval('product_id_seq'::regclass),
	category_id integer NOT NULL,
	brand_id integer NOT NULL,
	code character varying(20) NOT NULL,
	name character varying(255) NOT NULL,
	description character varying(4000),
	price decimal(15,2) NOT NULL,
	old_price decimal(15,2),
	active boolean NOT NULL,
    CONSTRAINT products_pk PRIMARY KEY (id),
	CONSTRAINT fk_products_categories FOREIGN KEY (category_id)
        REFERENCES categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_products_brands FOREIGN KEY (brand_id)
        REFERENCES brands (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);