INSERT INTO products (id, version, description, price) VALUES ('product001', 0, 'Buy me', 12.34);
INSERT INTO products (id, version, description, price) VALUES ('product002', 0, 'Buy me first', 8.65);

INSERT INTO users (id, username, password_hash) VALUES ('user001', 'user', '$2a$10$GAjqyhXvGF0W5XmwQHNeUu7Hrd46OQlH/aCvxeJLEBhv2QPhJLcX.');

INSERT INTO orders (id, user_id, placed) VALUES ('order001', 'user001', '2020-01-01');

INSERT INTO order_lines (order_id, product_id, quantity, price) VALUES ('order001', 'product001', 2, 10.00);
