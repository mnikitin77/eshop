ALTER TABLE images DROP CONSTRAINT fk_images_products;

DROP TABLE images CASCADE;

DROP SEQUENCE image_id_seq;