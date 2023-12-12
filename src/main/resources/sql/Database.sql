
/*drop database hotBurger;*/
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

INSERT INTO user(id, username, password, type, emailAddress, phone) VALUES(not null , 'pereraHotBurger' , 'burger1234' , 'admin' , 'hotburger@gmail.com' , '0765937705');
INSERT INTO user(id, username, password, type, emailAddress, phone) VALUES(not null , 'orderAccount' , 'cashier1234' , 'cashier' , 'no mail' , '0757980223');

UPDATE user SET username = 'admin' where id = 1;
UPDATE user SET password = '123' where id = 1;
UPDATE user SET username = 'cashier' where id = 2;
UPDATE user SET password = '456' where id = 2;


CREATE TABLE itemCategory(
             id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
             name VARCHAR(50) NOT NULL,
             description VARCHAR(50) NOT NULL
);

INSERT INTO itemcategory (id , name , description) VALUES
            (not null , 'burger' , 'Different types of burgers'),
            (not null , 'combo pack' , 'There are 6 types of combo packs'),
            (not null , 'snacks' , 'In addition to snacks, there are chicken wings'),
            (not null , 'sauces' , 'There are five types of sauces'),
            (not null , 'drinks' , 'cool drinks'),
            (not null , 'offers' , '3 offer package');

CREATE TABLE item(
            id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
            itemCode VARCHAR(50) UNIQUE NOT NULL,
            name VARCHAR(50) NOT NULL,
            unitPrice DECIMAL(10,2) NOT NULL,
            unitCost DECIMAL(10,2) NOT NULL,
            categoryId INT NOT NULL,
            CONSTRAINT FOREIGN KEY (categoryId) REFERENCES itemCategory(id) ON DELETE CASCADE ON UPDATE CASCADE,
            image varchar(100)
);


INSERT INTO item (id , itemCode , name , unitPrice, unitCost , categoryId,image) VALUES
            (not null , 'I001' , 'Club House Chipsy Chicken ' , 680 , 400 , 1,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\clubHouseChipsyChiken.png'),
            (not null , 'I002' , 'Whopper ' , 540 , 320 , 1,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\Whopper.png'),
            (not null , 'I003' , 'Double Whopper ' , 660 , 420 , 1,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\DoubleWhopper.png'),
            (not null , 'I004' , 'Cheese Burger ' , 590 , 340 , 1,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\cheesesBurger.png'),
            (not null , 'I005' , 'Big King ' , 890 , 520 , 1,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\BigKing-removebg-preview.png'),
            (not null , 'I006' , 'Crispy Chicken ' , 630 , 400 , 1,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\crispyChicken.png'),
            (not null , 'I007' , 'Chicken Burger Meal ' , 950 , 560 , 2,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\chicken-burger-new-meal-1.png'),
            (not null , 'I008' , 'Chicken Royal Meal ' , 1300 , 650 , 2,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\chickenroyale-new-meal.png'),
            (not null , 'I009' , 'Crispy Chicken Wrap Meal ' , 880 , 390 , 2,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\crispy-wrap-meal-new.png'),
            (not null , 'I010' , 'Crunchy Chicken Meal ' , 1380 , 700 , 2,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\CrunchyChickenMeal.png'),
            (not null , 'I011' , 'Club House Crispy Chicken Meal ' , 1150 , 600 , 2,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\ClubHouseCrispyChickenMeal.png'),
            (not null , 'I012' , 'King Fries ' , 240 , 80 , 3,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\KingFries.png'),
            (not null , 'I013' , 'Snacks Plate ' , 270 , 100 , 3,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\snackesPlate.png'),
            (not null , 'I014' , 'Onion Wings ' , 280 , 110 , 3,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\onionWings.png'),
            (not null , 'I015' , 'Chicken Wings 4PCS ' , 320 , 180 , 3,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\ChickenWings4PCS.png'),
            (not null , 'I016' , 'Chicken Wings 6PCS ' , 420 , 230 , 3,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\ChickenWings.png'),
            (not null , 'I017' , 'Chicken Wings 9PCS ' , 650 , 380 , 3,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\ChickenWings.png'),
            (not null , 'I018' , 'Ketchup ' , 80 , 40 , 4,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\ketchup.png'),
            (not null , 'I019' , 'Mayonnaise ' , 100 , 60 , 4,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\Mayonnaise.png'),
            (not null , 'I020' , 'Sweet And Sour Sauce ' , 130 , 80 , 4,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\sweetSourSauce.png'),
            (not null , 'I021' , 'BBQ Sauce ' , 120 , 70 , 4,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\BBQSauce.png'),
            (not null , 'I022' , 'Tomato Sauce ' , 60 , 40 , 4,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\tomato.png'),
            (not null , 'I023' , 'Coca-cola ' , 200 , 120 , 5,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\Coca.png'),
            (not null , 'I024' , 'Fanta 300ml ' , 210 , 170 , 5,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\Fanta.png'),
            (not null , 'I025' , 'Sprite 300ml ' , 210 , 170 , 5,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\sprite.png'),
            (not null , 'I026' , 'Red Bull 300ml ' , 320 , 250 , 5,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\Redbull.png'),
            (not null , 'I027' , 'Orange juice ' , 130 , 80 , 5,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\OrangeJuice.png'),
            (not null , 'I028' , 'Strawberry juice ' , 160 , 90 , 5,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\StrawberyJuice.png'),
            (not null , 'I029' , 'King Deal ' , 2560 , 1800 , 6,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\kingDeal.png'),
            (not null , 'I030' , 'King Box ' , 3490 , 2800 , 6,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\kingBox.jpg'),
            (not null , 'I031' , 'Family Bundle ' , 6200 , 4500 , 6,'C:\\Users\\Ramesh Layan\\HotBurgersShop\\src\\main\\resources\\image\\familyBundle.jpg');

CREATE TABLE customer(
            id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
            firstName VARCHAR(50) NOT NULL,
            lastName VARCHAR(50) NOT NULL,
            primaryAddress VARCHAR(50) NOT NULL,
            mobile VARCHAR(10) NOT NULL
);

INSERT INTO customer VALUES
            (not null , 'ramesh' , 'layan' , 'beruwala' , '0765937705'),
            (not null , 'kasun' , 'gayan' , 'maggona' , '0756985623'),
            (not null , 'keshan' , 'senura' , 'kaluthara' , '0784562561');


CREATE TABLE deliveryDetail(
            id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
            address VARCHAR(100) NOT NULL,
            additionalMobile VARCHAR(10) NOT NULL,
            customerId INT NOT NULL,
            CONSTRAINT FOREIGN KEY (customerId) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE orders(
            id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
            type VARCHAR(50)NOT NULL,
            date VARCHAR(20) NOT NULL,
            subTotal DECIMAL(10,2) NOT NULL,
            discount DECIMAL(10,2) NOT NULL,
            deliveryCharge DECIMAL(10,2) NOT NULL,
            total DECIMAL(10,2) NOT NULL,
            customerId INT NOT NULL,
            CONSTRAINT FOREIGN KEY (customerId) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE toppings(
            id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
            name VARCHAR(50) NOT NULL,
            price DECIMAL(10,2) NOT NULL
);

INSERT INTO toppings (id, name, price) VALUES
            (not null , 'Cheese' , 120),
            (not null , 'Crispy Onions' , 250),
            (not null , 'Patty' , 180);

CREATE TABLE orderDetail(
             id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
             size VARCHAR(20) NOT NULL,
             quantity INT NOT NULL,
             price DECIMAL(10,2) NOT NULL,
             orderId INT NOT NULL,
             cost DECIMAL(10,2) NOT NULL,
             itemCode VARCHAR(50) NOT NULL,
             itemName VARCHAR(50) NOT NULL,
             CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders(id) ON DELETE CASCADE ON UPDATE CASCADE,
             CONSTRAINT FOREIGN KEY (itemCode) REFERENCES item(itemCode) ON DELETE CASCADE ON UPDATE CASCADE
);

/*
  toppingsId INT NOT NULL,
   CONSTRAINT FOREIGN KEY (toppingsId) REFERENCES toppings(id) ON DELETE CASCADE ON UPDATE CASCADE,
 */

CREATE TABLE toppingsSalesDetail(
            qty INT NOT NULL,
            orderId INT NOT NULL,
            toppingsId INT(20) NOT NULL,
            CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders (id) ON DELETE CASCADE ON UPDATE CASCADE,
            CONSTRAINT FOREIGN KEY (toppingsId) REFERENCES toppings (id) ON DELETE CASCADE ON UPDATE CASCADE
);

