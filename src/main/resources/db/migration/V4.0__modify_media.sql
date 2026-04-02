ALTER TABLE medias ADD COLUMN data_temporary bytea;

UPDATE medias
SET data_temporary = lo_get(data::oid)
WHERE data IS NOT NULL;

ALTER TABLE medias DROP COLUMN data;

ALTER TABLE medias RENAME COLUMN data_temporary TO data;