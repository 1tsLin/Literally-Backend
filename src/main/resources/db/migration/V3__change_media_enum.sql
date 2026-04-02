ALTER TYPE entity_type_enum RENAME TO media_category_enum;

ALTER TYPE media_category_enum ADD VALUE 'PRODUCT_BACK';
ALTER TYPE media_category_enum RENAME VALUE 'PRODUCT' TO 'PRODUCT_COVER';