CREATE TABLE orders(
               id UUID NOT NULL PRIMARY KEY,
               orderType VARCHAR(30) NOT NULL,
               order_date TIMESTAMP NOT NULL,
               order_status VARCHAR(30) NOT NULL,
               order_amount INT NOT NULL,
               order_number VARCHAR(30) NOT NULL,
               user_id UUID REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_orders_user_id ON orders (user_id);
