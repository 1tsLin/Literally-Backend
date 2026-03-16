ALTER TABLE products DROP COLUMN is_active;

ALTER TABLE product_localizations ADD COLUMN is_active BOOLEAN;