insert into user (username, password) values ('user1', 'user1');
insert into user (username, password) values ('admin', 'admin');
insert into user (username, password) values ('user', 'user');

insert into user_roles (user_username, role) VALUES ('admin', 'ADMIN');
insert into user_roles (user_username, role) VALUES ('user', 'USER');
insert into user_roles (user_username, role) VALUES ('user1', 'USER');

insert into products (product_description, product_name, product_price, product_producer) VALUES ('Expensive smartphone', 'IPhone', 1000, 'Apple');
insert into products (product_description, product_name, product_price, product_producer) VALUES ('Korean smartphone', 'Galaxy', 700, 'Samsung');
insert into products (product_description, product_name, product_price, product_producer) VALUES ('Smartphone with great camera', 'Xperia', 600, 'Sony');

