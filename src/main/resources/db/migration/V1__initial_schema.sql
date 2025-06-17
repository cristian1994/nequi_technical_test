CREATE TABLE franchises (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE headquarters (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    franchise_id BIGINT NOT NULL,
    CONSTRAINT fk_headquarter_franchise FOREIGN KEY (franchise_id)
        REFERENCES franchises(id) ON DELETE CASCADE
);

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    headquarter_id BIGINT NOT NULL,
    CONSTRAINT fk_product_headquarter FOREIGN KEY (headquarter_id)
        REFERENCES headquarters(id) ON DELETE CASCADE
);
