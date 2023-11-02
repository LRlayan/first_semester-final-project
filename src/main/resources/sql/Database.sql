CREATE DATABASE hotBurger;

USE hotBurger;

CREATE TABLE user(
                     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                     username VARCHAR(50) UNIQUE NOT NULL,
                     password VARCHAR(50) NOT NULL,
                     type VARCHAR(20) NOT NULL,
                     emailAddress VARCHAR(30) NOT NULL,
                     phone VARCHAR(20) NOT NULL
);

drop table user;
INSERT INTO user(id, username, password, type, emailAddress, phone) VALUES(not null , 'pereraHotBurger' , 'burger1234' , 'admin' , 'hotburger@gmail.com' , '0765937705');
INSERT INTO user(id, username, password, type, emailAddress, phone) VALUES(not null , 'orderAccount' , 'cashier1234' , 'cashier' , 'no mail' , '0757980223');

select type from user where username = 'pereraHotBurger';
select type from user where username = 'orderAccount';

CREATE TABLE itemCategory(
                             id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                             name VARCHAR(50) NOT NULL,
                             description VARCHAR(50) NOT NULL
);

CREATE TABLE item(
                     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                     itemCode VARCHAR(50) UNIQUE NOT NULL,
                     name VARCHAR(50) NOT NULL,
                     unitPrice DECIMAL(10,2) NOT NULL,
                     unitCost DECIMAL(10,2) NOT NULL,
                     categoryId INT NOT NULL,
                     FOREIGN KEY (categoryId) REFERENCES itemCategory(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE customer(
                         id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         customerId VARCHAR(20) UNIQUE NOT NULL,
                         firstName VARCHAR(50) NOT NULL,
                         lastName VARCHAR(50) NOT NULL,
                         address VARCHAR(50) NOT NULL,
                         phone VARCHAR(10) NOT NULL
);



CREATE TABLE deliveryDetail(
                               id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                               addressLane1 VARCHAR(100) NOT NULL,
                               addressLane2 VARCHAR(100) NOT NULL,
                               addressLane3 VARCHAR(100) NOT NULL,
                               phone VARCHAR(10) NOT NULL,
                               customerId VARCHAR(20) NOT NULL,
                               FOREIGN KEY (customerId) REFERENCES customer(customerId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE orders(
                       id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       type VARCHAR(50 )NOT NULL,
                       date date NOT NULL,
                       description VARCHAR(50) NOT NULL,
                       subTotal DECIMAL(10,2) NOT NULL,
                       discount DECIMAL(10,2) NOT NULL,
                       deliveryCharge DECIMAL(10,2) NOT NULL,
                       total DECIMAL(10,2) NOT NULL,
                       customerId VARCHAR(20) NOT NULL,
                       FOREIGN KEY (customerId) REFERENCES customer(customerId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE toppings(
                         id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         name VARCHAR(50) NOT NULL,
                         price DECIMAL(10,2) NOT NULL
);

CREATE TABLE orderDetail(
                            id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                            size VARCHAR(20) NOT NULL,
                            quantity INT NOT NULL,
                            price DECIMAL(10,2) NOT NULL,
                            toppingsId INT NOT NULL,
                            orderId INT NOT NULL,
                            itemCode VARCHAR(50) NOT NULL,
                            FOREIGN KEY (toppingsId) REFERENCES toppings(id) ON DELETE CASCADE ON UPDATE CASCADE,
                            FOREIGN KEY (orderId) REFERENCES orders(id) ON DELETE CASCADE ON UPDATE CASCADE,
                            FOREIGN KEY (itemCode) REFERENCES item(itemCode) ON DELETE CASCADE ON UPDATE CASCADE
);
