CREATE TABLE expense_transaction
(
    id                BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    category_id       BIGINT NOT NULL,
    description       VARCHAR(255),
    amount            BIGINT,
    date              TIMESTAMP,
    payment_method_id BIGINT,
    user_id           INT    NOT NULL,
    created_date      DATETIME,
    FOREIGN KEY (user_id) REFERENCES user_ (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE tag
(
    id           BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100),
    degree       SMALLINT,
    user_id      INT    NOT NULL,
    created_date DATETIME
);
CREATE TABLE expense_transaction_tag
(
    expense_transaction_id BIGINT,
    tag_id                 BIGINT
)