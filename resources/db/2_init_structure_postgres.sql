------------------------- Postgress specific update steps------------------------------
ALTER TABLE Person ADD column FILE_DATA  bytea;


ALTER TABLE Media ADD COLUMN SOURCE bytea;