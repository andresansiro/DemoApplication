DROP TABLE IF EXISTS subject;

CREATE TABLE subject
(
    id                 BIGSERIAL PRIMARY KEY,
    name               VARCHAR(255) NOT NULL,
    lecturer_full_name VARCHAR(255) NOT NULL
);