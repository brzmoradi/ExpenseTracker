CREATE TABLE budget
(
    id               BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id          INT    NOT NULL,
    created_date     TIMESTAMP,
    category_id      BIGINT NOT NULL,
    amount           BIGINT NOT NULL,
    threshold_amount BIGINT NOT NULL,
    start_date       DATE   NOT NULL,
    end_date         DATE   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_ (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);


CREATE TABLE budget_aud
(
    id               BIGINT,
    user_id          INT,
    created_date     TIMESTAMP,
    category_id      INT,
    amount           BIGINT,
    threshold_amount BIGINT,
    start_date       DATE,
    end_date         DATE,
    rev              BIGINT NOT NULL,
    revtype          TINYINT,
    PRIMARY KEY (id, rev)
);


CREATE TABLE revinfo
(
    rev      BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    revtstmp BIGINT
);

ALTER TABLE budget_aud
    ADD CONSTRAINT fk_cal_rev FOREIGN KEY (rev) REFERENCES revinfo (rev);