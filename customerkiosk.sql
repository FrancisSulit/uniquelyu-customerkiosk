-- CREATE DATABASE IF NOT EXISTS customerkiosk;
-- USE customerkiosk;

-- SET FOREIGN_KEY_CHECKS = 0;

-- -- Table structure for table `customer`
-- -- DROP TABLE IF EXISTS "customer";
-- CREATE TABLE customer (
--   customer_ID INT NOT NULL,
--   Customer_Name VARCHAR(45) DEFAULT NULL,
--   Customer_Phone VARCHAR(45) DEFAULT NULL,
--   Customer_Email VARCHAR(45) DEFAULT NULL,
--   PRIMARY KEY (customer_ID)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 

-- -- ENGINE=InnoDB: Purpose: Specifies the storage engine for the table.
-- -- DEFAULT CHARSET=utf8mb4: Purpose: Defines the character set for storing text data in the table.
-- -- COLLATE=utf8mb4_0900_ai_ci: Purpose: Specifies the collation, which determines how string comparisons and sorting are performed.

-- -- Table structure for table `employee`
-- DROP TABLE IF EXISTS employee;
-- CREATE TABLE employee (
--   employee_ID INT NOT NULL,
--   Employee_FirstName VARCHAR(45) DEFAULT NULL,
--   Employee_LastName VARCHAR(45) DEFAULT NULL,
--   PRIMARY KEY (employee_ID)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -- Table structure for table `item`
-- -- DROP TABLE IF EXISTS `item`;
-- CREATE TABLE `item` (
--   `Item_ID` INT NOT NULL,
--   `Item_Name` VARCHAR(45) DEFAULT NULL,
--   `Item_Img` BLOB,
--   `Item_Category` VARCHAR(45) DEFAULT NULL,
--   `Item_Price` DECIMAL(10,2) NOT NULL,
--   `Item_Size` VARCHAR(45) DEFAULT NULL,
--   `Item_Color` VARCHAR(45) DEFAULT NULL,
--   `Item_StockStatus` VARCHAR(45) DEFAULT NULL,
--   `Item_Quantity` INT DEFAULT NULL,
--   `Customer_ID` INT NOT NULL,
--   `Employee_ID` INT NOT NULL,
--   PRIMARY KEY (`Item_ID`),
--   KEY `Customer_ID` (`Customer_ID`),
--   KEY `Employee_ID` (`Employee_ID`),
--   CONSTRAINT `item_ibfk_1` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`customer_ID`),
--   CONSTRAINT `item_ibfk_2` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`employee_ID`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -- Table structure for table `promotion`
-- DROP TABLE IF EXISTS `promotion`;
-- CREATE TABLE `promotion` (
--   `Promo_ID` INT NOT NULL,
--   `Promo_Description` VARCHAR(45) DEFAULT NULL,
--   `Promo_StartDate` DATE DEFAULT NULL,
--   `Promo_EndDate` DATE DEFAULT NULL,
--   `Promo_DiscountedPercentage` DECIMAL(10,2) DEFAULT NULL,
--   `Customer_ID` INT NOT NULL,
--   `Employee_ID` INT NOT NULL,
--   PRIMARY KEY (`Promo_ID`),
--   KEY `Customer_ID` (`Customer_ID`),
--   KEY `Employee_ID` (`Employee_ID`),
--   CONSTRAINT `promotion_ibfk_1` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`customer_ID`),
--   CONSTRAINT `promotion_ibfk_2` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`employee_ID`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -- Table structure for table `item_promotion`
-- DROP TABLE IF EXISTS `item_promotion`;
-- CREATE TABLE `item_promotion` (
--   `ItemPromotion_ID` INT NOT NULL,
--   `Item_ID` INT NOT NULL,
--   `Promo_ID` INT NOT NULL,
--   PRIMARY KEY (`ItemPromotion_ID`),
--   KEY `Item_ID` (`Item_ID`),
--   KEY `Promo_ID` (`Promo_ID`),
--   CONSTRAINT `item_promotion_ibfk_1` FOREIGN KEY (`Item_ID`) REFERENCES `item` (`Item_ID`),
--   CONSTRAINT `item_promotion_ibfk_2` FOREIGN KEY (`Promo_ID`) REFERENCES `promotion` (`Promo_ID`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for table `sale`
-- DROP TABLE IF EXISTS `sale`;
-- CREATE TABLE `sale` (
--   `Sale_ID` INT NOT NULL,
--   `Sale_StartDate` DATE DEFAULT NULL,
--   `Sale_EndDate` DATE DEFAULT NULL,
--   `Customer_ID` INT NOT NULL,
--   `Employee_ID` INT NOT NULL,
--   `Item_ID` INT NOT NULL,
--   PRIMARY KEY (`Sale_ID`),
--   KEY `Customer_ID` (`Customer_ID`),
--   KEY `Employee_ID` (`Employee_ID`),
--   KEY `Item_ID` (`Item_ID`),
--   CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`customer_ID`),
--   CONSTRAINT `sale_ibfk_2` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`employee_ID`),
--   CONSTRAINT `sale_ibfk_3` FOREIGN KEY (`Item_ID`) REFERENCES `item` (`Item_ID`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- SET FOREIGN_KEY_CHECKS = 1;

-- testing for github presentation