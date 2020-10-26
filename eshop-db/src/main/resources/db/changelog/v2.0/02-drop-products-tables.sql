ALTER TABLE categories DROP CONSTRAINT fk_categories_categories;
ALTER TABLE products DROP CONSTRAINT fk_products_categories;
ALTER TABLE products DROP CONSTRAINT fk_products_brands;

DROP TABLE products CASCADE;
DROP TABLE categories CASCADE;
DROP TABLE brands CASCADE;

DROP SEQUENCE brand_id_seq;
DROP SEQUENCE category_id_seq;
DROP SEQUENCE product_id_seq;