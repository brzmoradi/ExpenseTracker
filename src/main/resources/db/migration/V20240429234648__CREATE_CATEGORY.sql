create table category
(
    id           BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    description  VARCHAR(250),
    user_id      INT          NOT NULL,
    created_date DATETIME,
    FOREIGN KEY (user_id) REFERENCES user_ (id)
)