CREATE TABLE users(
   id UUID NOT NULL PRIMARY KEY,
   firstname VARCHAR(30),
   lastname VARCHAR(30),
   email VARCHAR(50) NOT NULL ,
   password VARCHAR(255),
   phone_number VARCHAR(255) NOT NULL,
   town_of_residence VARCHAR(255) NOT NULL,
   is_active BOOLEAN,
   roles VARCHAR(10),
   occupation VARCHAR(255),
   createdDate TIMESTAMP NOT NULL,
   UNIQUE(email, phone_number)
);

CREATE TABLE testimonies(
    id UUID NOT NULL PRIMARY KEY,
    testimony VARCHAR(255) NOT NULL,
    isactive BOOLEAN,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE criminalRecordExtract(
     id UUID NOT NULL PRIMARY KEY,
     isEstablished BOOLEAN,
     mother_name VARCHAR(30),
     birth_department VARCHAR(255) NOT NULL,
     date TIMESTAMP NOT NULL,
     file_url VARCHAR(255) NOT NULL,
     user_id UUID REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE legalization(
    id UUID NOT NULL PRIMARY KEY,
    motif VARCHAR(255) NOT NULL,
    receip_moment VARCHAR(255) NOT NULL,
    isLegalized BOOLEAN,
    quantity INT NOT NULL,
    file_url VARCHAR[],
    user_id UUID REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_users_email ON users (email);
CREATE INDEX idx_testimonies_user_id ON testimonies (user_id);
CREATE INDEX idx_criminalRecordExtract_user_id ON criminalRecordExtract (user_id);
CREATE INDEX idx_legalization_user_id ON legalization (user_id);

