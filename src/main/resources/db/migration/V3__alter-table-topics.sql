ALTER TABLE topics
ADD last_updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADD creation_date DATETIME NOT NULL,
ADD status VARCHAR(100) NOT NULL;