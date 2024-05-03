CREATE TABLE payment_method
(
    id           BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100),
    description  VARCHAR(255),
    provider     VARCHAR(100),
    created_date TIMESTAMP,
    user_id      INT    NOT NULL
);

ALTER TABLE expense_transaction
    ADD CONSTRAINT fk__payment_method_id
        FOREIGN KEY (payment_method_id)
            REFERENCES payment_method (id);