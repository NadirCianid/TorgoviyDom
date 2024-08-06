

CREATE TABLE agreement
(
    id                   SERIAL PRIMARY KEY,
    agreement_number     VARCHAR(50) UNIQUE NOT NULL,
    company_name         VARCHAR(100) NOT NULL,
    company_address      VARCHAR(200),
    company_phone_number VARCHAR(20)
);

CREATE TABLE client
(
    id           SERIAL PRIMARY KEY,
    fio          VARCHAR(100) NOT NULL,
    agreement    INT REFERENCES agreement (id),
    phone_number VARCHAR(20) UNIQUE NOT NULL ,
    email        VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE category
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    category    INT REFERENCES category (id)
);

CREATE TABLE warehouse
(
    id       SERIAL PRIMARY KEY,
    address  VARCHAR(200) NOT NULL,
    title    VARCHAR(100),
    capacity INT
);

CREATE TABLE warehouse_product
(
    id           SERIAL PRIMARY KEY,
    warehouse_id INT REFERENCES warehouse (id),
    product_id   INT REFERENCES product (id),
    amount       INT NOT NULL
);

CREATE TABLE status
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE "order"
(
    id          SERIAL PRIMARY KEY,
    product     INT REFERENCES product (id),
    client      INT REFERENCES client (id),
    status_id   INT REFERENCES status (id),
    amount      INT NOT NULL,
    address     VARCHAR(200),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

