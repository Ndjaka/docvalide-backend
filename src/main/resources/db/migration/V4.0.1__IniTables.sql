CREATE TABLE fees_criminal_record (
       id UUID NOT NULL PRIMARY KEY,
       residence VARCHAR(255) NOT NULL,
       tribunal VARCHAR(255) NOT NULL,
       fees INT NOT NULL
);
