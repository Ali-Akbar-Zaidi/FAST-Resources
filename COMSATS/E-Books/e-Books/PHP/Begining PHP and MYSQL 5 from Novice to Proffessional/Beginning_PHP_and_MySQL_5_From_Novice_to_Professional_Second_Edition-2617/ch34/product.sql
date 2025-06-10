CREATE TABLE product (
   rowID int not null auto_increment,
   productid varchar(8) not null,
   name varchar(25) not null,
   price decimal(5,2) not null,
   description mediumtext not null,
   primary key(rowID)
)