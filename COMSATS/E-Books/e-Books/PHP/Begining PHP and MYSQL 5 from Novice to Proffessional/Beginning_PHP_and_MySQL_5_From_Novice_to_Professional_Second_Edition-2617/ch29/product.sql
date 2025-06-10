CREATE TABLE product (
   rowID INT NOT NULL AUTO_INCREMENT,
   productid varchar(8) NOT NULL,
   name varchar(25) NOT NULL,
   price DECIMAL(5,2) NOT NULL,
   description MEDIUMTEXT NOT NULL,
   PRIMARY KEY(rowID)
)
