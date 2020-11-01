CREATE SEQUENCE image_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE images
(
    id integer NOT NULL DEFAULT nextval('image_id_seq'::regclass),
	product_id integer NOT NULL,
	name character varying(255) NOT NULL,
	original_name character varying(255),
	description character varying(512),
	size integer NOT NULL,
	content_type character varying(255),
	selected boolean NOT NULL DEFAULT false,
    CONSTRAINT images_pk PRIMARY KEY (id),
	CONSTRAINT fk_images_products FOREIGN KEY (product_id)
        REFERENCES products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);