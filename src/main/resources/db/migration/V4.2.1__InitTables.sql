CREATE TABLE tribunal(
    id         uuid PRIMARY KEY,
    region     varchar(255) NOT NULL,
    department varchar(255) NOT NULL
);

CREATE TABLE city(
    id      uuid PRIMARY KEY,
    name    varchar(255) NOT NULL,
    isForLegalization boolean NOT NULL
);

DELETE FROM fees_criminal_record WHERE region IS NOT NULL;

ALTER TABLE fees_criminal_record
    DROP COLUMN IF EXISTS residence,
    DROP COLUMN IF EXISTS tribunal,
    DROP COLUMN IF EXISTS region;

ALTER TABLE fees_criminal_record
    ADD COLUMN city_id uuid REFERENCES city(id),
    ADD COLUMN tribunal_id uuid REFERENCES tribunal(id);

CREATE INDEX idx_idTribunal ON fees_criminal_record(tribunal_id);
CREATE INDEX idx_idCity ON fees_criminal_record(city_id);