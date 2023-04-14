CREATE TABLE IF NOT EXISTS legalization_docs(
    id UUID NOT NULL PRIMARY KEY,
    quantity INT NOT NULL,
    file_url varchar(255),
    designation varchar(255),
    user_id UUID REFERENCES legalization(id) ON DELETE CASCADE
);

ALTER TABLE legalization
    ADD COLUMN  date TIMESTAMP   ;