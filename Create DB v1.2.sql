-- Used Mav's Create DB v1.1
-- inserted Mav's Inserts v1.1
-- added Sam's Promo Image Path


DROP DATABASE IF EXISTS `Uniqlo_Kiosk_System`;
CREATE DATABASE `Uniqlo_Kiosk_System`;
USE `Uniqlo_Kiosk_System`;

CREATE TABLE IF NOT EXISTS CUSTOMERS (
  ID INT AUTO_INCREMENT,
  PRIMARY KEY (ID)
);


CREATE TABLE IF NOT EXISTS EMPLOYEES (

  ID INT AUTO_INCREMENT,
  `First Name` VARCHAR(255),
  `Last Name` VARCHAR(255),
  `Image` BLOB,

  PRIMARY KEY (ID)
);


CREATE TABLE IF NOT EXISTS ITEMS(
  ID INT AUTO_INCREMENT,
  `Name` VARCHAR(255),
  `Image` BLOB,
  `Description` VARCHAR(2000),
  Category VARCHAR(255),						-- clothing type (e.x shirt, short, shoe etc)
  Gender VARCHAR(255),
  Price DECIMAL(10,2),
  Size VARCHAR(255),
  Colour VARCHAR(255),
  `Stock Status` VARCHAR(255),
  Quantity INT(10),
  `New Arrival ID` INT(10),
  PRIMARY KEY (ID)
)

INSERT INTO `ITEMS` 
  (`Item_ID`, `Item_Name`, `Item_Img`, `Item_Category`, `Item_Price`, `Item_Size`, `Item_Color`, `Item_StockStatus`, `Item_Quantity`) VALUES
  (1, 'Crew Neck T-Shirt', NULL, 'Clothing', 19.99, 'M', 'Blue', 'In Stock', 100),
  (2, 'Slim Fit Jeans', NULL, 'Clothing', 49.99, '32', 'Dark Blue', 'In Stock', 60),
  (3, 'Hooded Sweatshirt', NULL, 'Clothing', 39.95, 'L', 'Gray', 'Low Stock', 15),
  (4, 'Windproof Stand Blouson', NULL, 'Clothing', 59.50, 'M', 'Black', 'In Stock', 30),
  5, 'Combination Sleeveless Dress', NULL, 'Clothing', 29.99, 'S', 'Red', 'Out of Stock', 0);

CREATE TABLE IF NOT EXISTS PROMOTIONS (

  ID INT AUTO_INCREMENT,
  `Item ID` INT,
  `Description` VARCHAR(2000),
  `Start Date` DATE,
  `End Date` DATE,
  
  PRIMARY KEY (ID),
  
  FOREIGN KEY (`Item ID`) REFERENCES ITEMS(ID)

);


CREATE TABLE IF NOT EXISTS ONSALES (

  ID INT AUTO_INCREMENT,
  `Item ID` INT,
  `Start Date` DATE,
  `End Date` DATE,
  promoImgPath VARCHAR(500),
  
  PRIMARY KEY (ID),
  
  FOREIGN KEY (`Item ID`) REFERENCES ITEMS(ID)

);