DROP DATABASE IF EXISTS `Uniqlo_Kiosk_System`;
CREATE DATABASE `Uniqlo_Kiosk_System`;
USE `Uniqlo_Kiosk_System`;


CREATE TABLE IF NOT EXISTS CUSTOMERS (

ID INT AUTO_INCREMENT,
    
PRIMARY KEY (ID)
    
);


CREATE TABLE IF NOT EXISTS ITEMS (

ID INT AUTO_INCREMENT,
`Name` VARCHAR(255),
`ImagePath` VARCHAR(1000),            -- file path string for items
`Description` VARCHAR(2000),
Category VARCHAR(255),						    -- clothing type (e.x shirt, short, shoe etc)
Gender VARCHAR(255),
Price DECIMAL(10,2),
Size VARCHAR(255),
Colour VARCHAR(255),
`Stock Status` VARCHAR(255),
Quantity INT(10),
`New Arrival ID` INT(10),

PRIMARY KEY (ID)

);


CREATE TABLE IF NOT EXISTS EMPLOYEES (

ID INT AUTO_INCREMENT,
`First Name` VARCHAR(255),
`Last Name` VARCHAR(255),
    
PRIMARY KEY (ID)
    
);


CREATE TABLE IF NOT EXISTS ONSALES (
  
ID INT AUTO_INCREMENT,
`Item ID` INT,
`Description` VARCHAR(2000),         
`Start Date` DATE,
`End Date` DATE,
`ImagePath` VARCHAR(1000),            -- file path string for sale image
  
PRIMARY KEY (ID),
  
FOREIGN KEY (`Item ID`) REFERENCES ITEMS(ID)
  
);


CREATE TABLE IF NOT EXISTS PROMOTIONS (

ID INT AUTO_INCREMENT,
`Item ID` INT,
`Description` VARCHAR(2000),
`Start Date` DATE,
`End Date` DATE,
`ImagePath` VARCHAR(1000),            -- file path string for promo image
  
PRIMARY KEY (ID),
  
FOREIGN KEY (`Item ID`) REFERENCES ITEMS(ID)
  
);

