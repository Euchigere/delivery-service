CREATE TABLE IF NOT EXISTS delivery(
    id INT AUTO_INCREMENT PRIMARY KEY,
    delivery_id INT UNIQUE NOT NULL,
    product VARCHAR(32) NOT NULL,
    supplier VARCHAR(64) NOT NULL,
    quantity INT NOT NULL,
    expected_warehouse VARCHAR(64) NOT NULL,
    expected_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS delivery_status(
    id INT AUTO_INCREMENT PRIMARY KEY,
    delivery_id INT NOT NULL,
    is_delivered BOOLEAN NOT NULL,
    delivery_date TIMESTAMP
);

ALTER TABLE delivery_status ADD FOREIGN KEY (delivery_id) REFERENCES delivery(delivery_id);