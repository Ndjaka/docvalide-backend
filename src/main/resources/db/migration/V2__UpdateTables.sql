ALTER TABLE users
    ADD CONSTRAINT unique_email_phone_number UNIQUE(email, phone_number);

ALTER TABLE criminalRecordExtract
    ALTER COLUMN file_url TYPE TEXT;

