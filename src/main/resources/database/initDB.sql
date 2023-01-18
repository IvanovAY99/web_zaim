DROP TABLE IF EXISTS contract;
CREATE TABLE IF NOT EXISTS contract
(
    id    BIGSERIAL PRIMARY KEY ,
    payments  VARCHAR(200) NOT NULL ,
    first_payments_date date NOT NULL
    );