CREATE SCHEMA IF NOT EXISTS ecommerce_platform;

CREATE TABLE ecommerce_platform.tbl_prices (
    product_id INTEGER NOT NULL,
    brand_id INTEGER NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price_list INTEGER NOT NULL,
    priority TINYINT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    PRIMARY KEY (product_id, brand_id, start_date, end_date)
);

CREATE INDEX idx_product_id ON ecommerce_platform.tbl_prices (product_id);
CREATE INDEX idx_brand_id ON ecommerce_platform.tbl_prices (brand_id);
CREATE INDEX idx_start_date ON ecommerce_platform.tbl_prices (start_date);
CREATE INDEX idx_end_date ON ecommerce_platform.tbl_prices (end_date);