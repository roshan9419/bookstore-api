CREATE TABLE products (
    id BIGINT NOT NULL AUTO_INCREMENT,
    code TEXT NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    image_url TEXT,
    price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_code (code(255))
);
