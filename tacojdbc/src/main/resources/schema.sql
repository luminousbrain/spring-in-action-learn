-- файл служит подсказкой для Spring Boot, какая схема должна быть у БД (H2 in memory db) при старте
CREATE TABLE IF NOT EXISTS "ingredient"
(
    id   VARCHAR(4)  NOT NULL,
    name VARCHAR(25) NOT NULL,
    type VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS "taco"
(
    id         BIGINT IDENTITY,
    name       VARCHAR(50) NOT NULL,
    created_at TIMESTAMP   NOT NULL
);

-- ManyToMany для хранения конкретного taco и его ингредиентов
CREATE TABLE IF NOT EXISTS "taco_ingredients"
(
    taco_id       BIGINT     NOT NULL,
    ingredient_id VARCHAR(4) NOT NULL
);

ALTER TABLE "taco_ingredients"
    ADD FOREIGN KEY (taco_id) REFERENCES "taco" (id);
ALTER TABLE "taco_ingredients"
    ADD FOREIGN KEY (ingredient_id) REFERENCES "ingredient" (id);

-- "" потому что ORDER keyword
CREATE TABLE IF NOT EXISTS "order_table"
(
    id              BIGINT IDENTITY,
    delivery_name   VARCHAR(50) NOT NULL,
    delivery_street VARCHAR(50) NOT NULL,
    delivery_city   VARCHAR(50) NOT NULL,
    delivery_state  VARCHAR(2)  NOT NULL,
    delivery_zip    VARCHAR(10) NOT NULL,
    cc_number       VARCHAR(16) NOT NULL,
    cc_expiration   VARCHAR(5)  NOT NULL,
    cc_CVV          VARCHAR(3)  NOT NULL,
    placed_at       TIMESTAMP   NOT NULL
);

-- ManyToMany какой тако в каком заказе
CREATE TABLE IF NOT EXISTS "taco_order"
(
    order_id BIGINT NOT NULL,
    taco_id  BIGINT NOT NULL
);

ALTER TABLE "taco_order"
    ADD FOREIGN KEY (order_id) REFERENCES "order_table" (id);
ALTER TABLE "taco_order"
    ADD FOREIGN KEY (taco_id) REFERENCES "taco" (id);