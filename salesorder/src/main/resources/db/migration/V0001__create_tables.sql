CREATE TABLE IF NOT EXISTS manufacturer
(
    id SERIAL NOT NULL,
    name VARCHAR(50) NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS product
(
    id SERIAL NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    barcode VARCHAR(20),
    unit_price DOUBLE PRECISION NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    id_manufacturer INTEGER,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS consumer
(
    id SERIAL NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(255),
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS sales_order
(
    id SERIAL NOT NULL,
    status VARCHAR(50) NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    id_delivery INTEGER,
    id_consumer INTEGER,
    id_payment INTEGER NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS sales_order_product
(
    id SERIAL NOT NULL,
    product_name VARCHAR(50) NOT NULL,
    units DOUBLE PRECISION NOT NULL,
    unit_price DOUBLE PRECISION NOT NULL,
    amount_price DOUBLE PRECISION NOT NULL,
    id_sales_order BIGINT NOT NULL,
    id_product INTEGER NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS payment
(
    id SERIAL NOT NULL,
    mode VARCHAR(50) NOT NULL,
    installments INTEGER NOT NULL,
    amount_price DOUBLE PRECISION NOT NULL,
    installment_price DOUBLE PRECISION NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS delivery
(
    id SERIAL NOT NULL,
    mode VARCHAR(50) NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE product
    ADD CONSTRAINT fk_manufacturer
    FOREIGN KEY (id_manufacturer)
    REFERENCES manufacturer(id)
    MATCH SIMPLE
;
    
ALTER TABLE sales_order_product
    ADD CONSTRAINT fk_product
    FOREIGN KEY (id_product)
    REFERENCES product(id)
    MATCH SIMPLE
;
    
ALTER TABLE sales_order_product
    ADD CONSTRAINT fk_sales_order
    FOREIGN KEY (id_sales_order)
    REFERENCES sales_order(id)
    MATCH SIMPLE
;
    
ALTER TABLE sales_order
    ADD CONSTRAINT fk_consumer
    FOREIGN KEY (id_consumer)
    REFERENCES consumer(id)
    MATCH SIMPLE
;
    
ALTER TABLE sales_order
    ADD CONSTRAINT fk_payment
    FOREIGN KEY (id_payment)
    REFERENCES payment(id)
    MATCH SIMPLE
;
    
ALTER TABLE sales_order
    ADD CONSTRAINT fk_delivery
    FOREIGN KEY (id_delivery)
    REFERENCES delivery(id)
    MATCH SIMPLE
;