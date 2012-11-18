ALTER TABLE resource ADD COLUMN status character varying(255);
UPDATE resource SET status = 'GRABBED';
ALTER TABLE resource ALTER COLUMN status SET NOT NULL;
-- ALTER TABLE resource MODIFY status character varying(255) NOT NULL;